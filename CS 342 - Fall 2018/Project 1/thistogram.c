#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int minval, maxval, bincount, n, output[20];

// writes the bins that is stored in array to the file
void constructOutput(FILE* outfile){
	for(int i = 0; i < bincount; i++){
		printf("bin %d = %d\n", i + 1, output[i]);
		fprintf(outfile, "bin%d: %d\n", (i + 1), output[i]);
	}
    fclose(outfile);
}
void *runner (void *param){
	const char* filename = (char *)param; // takes filename from parameter
	int w = (maxval - minval) / bincount;
	
	//reads the values from the file and classifies them.
	FILE * outfile = fopen(filename, "r"); 
	double num;
	double x = fscanf(outfile, "%lf", &num);
	while(x != EOF){	
		printf("Number %lf from %s\n", num, filename);
		if(num == maxval){
        	output[bincount - 1]++;
        }
        else{
        	output[(int)((num - minval) / w)]++;
        }    
		x = fscanf(outfile, "%lf", &num);
	}
	pthread_exit(0); 
}
int main(int argc, char *argv[]){
	//warning for too little number of arguments
	if (argc < 6) {
		fprintf(stderr, "usage: ./thistogram <minval, maxval, bincount, n, file1 ... fileN output>\n"); 
		return -1; 
	}
	minval = atoi(argv[1]);
	maxval = atoi(argv[2]);
	bincount = atoi(argv[3]);
	for(int i = 0; i < bincount; i++)
		output[i] = 0;
	n = atoi(argv[4]);
	char* files[n];
	pthread_t tid[n]; // id of the created thread
	pthread_attr_t attr[n];  // set of thread attributes
	
	//create threads for each file
	for(int i = 0; i < n; i++){
		files[i] = argv[i + 5];
		pthread_attr_init(attr + i); 	
		pthread_create(tid + i, attr + i, runner, files[i]);
	}
	
	// wait for threads
	for(int i = 0; i < n; i++){
		pthread_join(*(tid + i), NULL);
	}
	
	//write output to the file named "toutput"
	for(int i = 0; i < bincount; i++)
		printf("%d\n", output[i]);
	char* outfilename = argv[n + 5];
	FILE * outputfile = fopen(outfilename, "w+");
	constructOutput(outputfile);
}
