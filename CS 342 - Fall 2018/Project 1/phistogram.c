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


void readFile(const char* filename, int minvalue, int maxvalue, int bincount){
	int w = (maxvalue - minvalue) / bincount;
	
	//bins are created and set to 0
	int output[bincount];
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
        int num = strtol (line, NULL, 00);
        printf("readFile: Got the number %d from the file %s. \n", num, filename); 
        if(num == maxvalue){
        	output[bincount - 1]++;
        }
        else{
        	output[(num - minvalue) / w]++;
        }     	
    }
    fclose(fp);
    if (line)
        free(line);
        
	//creating the name string of the intermadiate output files and opening them.
    char outputfile[10]; 
	sprintf(outputfile, "out-%s", filename);
	fp = fopen(outputfile, "w+");
	
	//writing bin values to the intermadiate file respectively.
	for(int i = 0; i < bincount; i++){
		fprintf(fp, "%d\n", output[i]);
	}
    fclose(fp);  
}

void reconstructOutput(int bincount, int n, const char* files[], FILE* outfile){
	//bins are created and set to 0
	int output[bincount];
	for(int i = 0; i < bincount; i++)
		output[i] = 0;
	
	//for each intermediate ouput file, the bin values are read and stored in the output array.
	for(int i = 0; i < n; i++){
		char filename[10]; 
		sprintf(filename, "out-%s", files[i]);
		FILE * fp;
		char * line = NULL;
		ssize_t read;
		size_t len = 0;
		fp = fopen(filename, "r");
		int j = 0; //j+1 represents bin numbers in a file.
		while ((read = getline(&line, &len, fp)) != -1) {
		    int num = strtol (line, NULL, 00);
		    printf("reconstructOutput: Got the number %d from the file. \n", num); 
		    output[j] += num;
  			j++;
		}
		fclose(fp);
		if (line)
		    free(line);
    }
    
	//bins are recorded to the output file.
	for(int i = 0; i < bincount; i++){
		printf("bin %d = %d\n", i + 1, output[i]);
		fprintf(outfile, "bin%d: %d\n", (i + 1), output[i]);
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
			return;
		}
	}
	// after all child processes terminate, an ultimate output file is created using intermediates.
	if(pid > 0){
		for(int i = 0; i < n; i++)
			wait(NULL);
		reconstructOutput(bincount, n, files, outfile);
	}
}
int main(int argc, char *argv[])
{
	if (argc < 6) {
		fprintf(stderr, "usage: ./thistogram <minval, maxval, bincount, n, file1 ... fileN output>\n"); 
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
    phistogram(minvalue, maxvalue, bincount, n, files, outfile);
}
