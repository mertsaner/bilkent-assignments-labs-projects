/*
	Author: Yasin Balcancı
*/
#include <stdio.h>
#include <sys/time.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/syscall.h>
#include <errno.h>


int main(){
	struct timeval t1, t2;	
    	
	long timePast;
		
	int read;
	int open;
	int write;
	int mkdir;
	int getpid;
	int limit = 1000000;
	
	char string[limit];

	//open()
	//100, 1000, 10000, 100000 and 1000000 files will be used as targets for this system call.
	for(int i = 100; i <= limit; i*=10){
		gettimeofday(&t1, NULL);
		open = syscall(SYS_open, (i), O_RDONLY);
		gettimeofday(&t2, NULL);
		timePast = t2.tv_usec - t1.tv_usec;
		printf("MS of open() for %d bytes: %ld\n", i, timePast);
	}
	
	//read()
	//100, 1000, 10000, 100000 and 1000000 files will be used as targets for this system call.
	for(int i = 100; i <= limit; i*=10){
		gettimeofday(&t1, NULL);
		read = syscall(SYS_read, open, &string, i);
		gettimeofday(&t2, NULL);
		timePast = t2.tv_usec - t1.tv_usec;
		printf("MS of read() for %d bytes: %ld\n", i, timePast);
	}
	
	//write()
	//output file in the folder will be used as a target for this system call.
	int openOutput = syscall(SYS_open, ("output"), O_WRONLY);
	for(int i = 100; i <= limit; i*=10){
		gettimeofday(&t1, NULL);
		write = syscall(SYS_write, openOutput, &string, i);
		gettimeofday(&t2, NULL);
		timePast = t2.tv_usec - t1.tv_usec;
		printf("MS of write() for %d bytes: %ld\n", i, timePast);
	}
	
	//mkdir() 
	//this system call will fail
	gettimeofday(&t1, NULL);
	mkdir = syscall(SYS_mkdir, "/newFolder", 777);
	gettimeofday(&t2, NULL);
	timePast = t2.tv_usec - t1.tv_usec;
	printf("MS of mkdir(): %ld\n", timePast);

	//getpid()
	gettimeofday(&t1, NULL);
	getpid = syscall(SYS_getpid);
	gettimeofday(&t2, NULL);
	timePast = t2.tv_usec - t1.tv_usec;
	printf("MS of getpid(): %ld\n", timePast);
	
		
}
