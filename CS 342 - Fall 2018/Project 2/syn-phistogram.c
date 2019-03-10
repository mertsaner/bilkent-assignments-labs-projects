/*
	Author: Yasin Balcancı
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h> 
#include <sys/shm.h> 
#include <sys/stat.h> 
#include <sys/mman.h>
#include <semaphore.h>

sem_t *semRead; // semaphore object
sem_t *semWrite; // semaphore object
int shm_fd; // the shared memory object
double* shmPtr; // pointer to shared memory object 

void readFile(const char* filename, int minvalue, int maxvalue, int bincount){
	double w = (maxvalue - minvalue) / bincount;
	//bins are created and set to 0
	double output[bincount];
	for(int i = 0; i < bincount; i++)
		output[i] = 0;
	//Creating necessary instances for reading a file
	FILE * fp;
	char * line = NULL;
	ssize_t read;
	size_t len = 0;
	fp = fopen(filename, "r");
	
	//reading the file line by line and classifying the values into bins
    while ((read = getline(&line, &len, fp)) != -1) {
        double num;
        sscanf(line, "%lf", &num);
        if(num == maxvalue){
        	output[bincount - 1]++;
        }
        else{
        	output[(int)((num - minvalue) / w)]++;
        }     	
    }
    fclose(fp);
    if (line)
        free(line);
        
	//waiting for semaphore   
    sem_wait(semRead);
	
	//writing bin values to the shared memory.
	for(int i = 0; i < bincount; i++){
		printf("value at adress %p before writing is %lf.\n", shmPtr+i, *(shmPtr+i));
		//sprintf(shmPtr_t+i, "%d", output[i]); // write to the shared memory object
		*(shmPtr+i) = output[i];
		printf("Writing value %lf to adress %p, new value = %lf \n", output[i], shmPtr+i, *(shmPtr+i));
	} 
	//signal the semaphore
    sem_post(semWrite);
}

void reconstructOutput(int bincount, int n, FILE* outfile){

	//bins are created and set to 0
	int output[bincount];
	for(int i = 0; i < bincount; i++)
		output[i] = 0;
	
	int num;
	//for each update in shared memory, the bin values are read and stored in the output array.
	for(int i = 0; i < n; i++){
		
		printf("RECONSTRUCT STARTED WAITING!\n");
		printf("the pointer that reconst. waits: %p\n", &semWrite);
		sem_wait(semWrite); //waiting for semaphore
		printf("RECONSTRUCT FORTUNATELY PASSED THE WAITING STAGE!\n");

		for(int j = 0; j < bincount; j++){
			num = *(shmPtr+j);
		    printf("reconstructOutput: Got the number %d from the adress %p ?= %p. \n", num, (int*)shmPtr+j, &num); 
		    output[j] += num;
		}
		sem_post(semRead); //signal the semaphore
    }
    
	//bins are recorded to the output file.
	for(int i = 0; i < bincount; i++){
		printf("bin %d = %d\n", i + 1, output[i]);
		fprintf(outfile, "%d: %d\n", (i + 1), output[i]);
	}
    fclose(outfile);  
}
void phistogram(int minvalue, int maxvalue, int bincount, int n, const char* files[], FILE* outfile){
	pid_t pid;
	for(int i = 0; i < n; i++){
		// each input file is read by a child process and an intermediate output file is created for each.
		pid = fork();
		if(pid == 0){
			readFile(files[i], 2, 30, 4);
			exit(0);
		}
	}
	// in the main process, an ultimate output file is created.
	reconstructOutput(bincount, n, outfile);
}
int main(int argc, char *argv[])
{
	if (argc < 7) {
		fprintf(stderr, "usage: ./syn-phistogram <minval, maxval, bincount, n, file1 ... fileN output>\n"); 
		return -1; 
	}
	int minvalue, maxvalue, bincount, n;	
	minvalue = atoi(argv[1]);
	maxvalue = atoi(argv[2]);
	bincount = atoi(argv[3]);
	n = atoi(argv[4]);
	const char* files[n];
	for(int i = 0; i < n; i++){
		files[i] = argv[i + 5];
	}
	char* outfilename = argv[n + 5];
	FILE * outfile = fopen(outfilename, "w+");
	
	//Shared Memory
	const int SIZE = bincount * sizeof(int);
	shm_fd = shm_open("shdHist", O_CREAT | O_RDWR, 0777); // create the shared memory object	
	ftruncate(shm_fd, SIZE); // configure the size of the shared memory object
	shmPtr = mmap(0, SIZE, PROT_WRITE, MAP_SHARED, shm_fd, 0); // memory map the shared memory object
	
	//Semaphore
	semRead = sem_open("semRead", O_CREAT | O_RDWR, 0777, sizeof(int));
	semWrite = sem_open("semWrite", O_CREAT | O_RDWR, 0777, sizeof(int));
	sem_init(semRead, n, 1); // initialize semaphore: int sem_init(sem_t *sem, int pshared, unsigned int value);
	sem_init(semWrite, n, 0); // initialize semaphore: int sem_init(sem_t *sem, int pshared, unsigned int value);

    phistogram(minvalue, maxvalue, bincount, n, files, outfile);
    //shm_unlink("shdHist"); 
}
