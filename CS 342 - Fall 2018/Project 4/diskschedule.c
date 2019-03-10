/*
	Author: Yasin Balcancı
*/
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <math.h>

int requests[1000];
int copyreq[1000];
int headpos;

int nfcfs = 0, nsstf = 0, nscan = 0, ncscan = 0, nlook = 0, nclook = 0;

void copyArr(){
	for(int i = 0; i < 1000; i++){
		copyreq[i] = requests[i];
	}
}

int compare (const void * a, const void * b){
  return ( *(int*)a - *(int*)b );
}

int fcfs(){
	int pos = requests[0];
	int arr[1000];
	for(int i = 0; i < 1000; i++){
		arr[i] = 0;
	}
	int res = abs(requests[0] - headpos);
	for(int i = 1; i < 1000; i++){
		if(requests[i] == requests[0] || requests[i] == headpos)
			arr[i] = 1;
		if(arr[i] == 0){
			res += abs(requests[i] - pos);
			pos = requests[i];
		}
		for(int j = i; j < 1000; j++){
			if(requests[i] == requests[j])
				arr[j] = 1;
		}
	}
	return res;
}

int sstf(){
	int pos = headpos;
	int res = 0;
	copyArr(); // copyreq = requests
	//qsort (copyreq, 1000, sizeof(int), compare);
	for(int j = 0; j < 1000; j++){
		int min = 5000;
		int minind;
		//printf("Outer for, res = %d\n", res);
		for(int i = 0; i < 1000; i++){
			if(abs(pos -  copyreq[i]) < min){
				//printf("Inner for, %d < %d\n", abs(pos -  copyreq[i]), min);
				minind = i;
				min = abs(pos -  copyreq[i]);
			}
		}
		//printf("Outer for, shortest way = %d(=%d)\n", min, );
		res += min;
		pos = copyreq[minind];
		copyreq[minind] = 15000;
	}
	return res;
}

int scan(){
	int pos = headpos;
	copyArr();
	qsort (copyreq, 1000, sizeof(int), compare);
	int res = copyreq[999] - pos;
	if(pos > copyreq[0]){
		res += 4999 - copyreq[999];
		res += 4999 - copyreq[0];
	}
	return res;
}

int cscan(){
	int pos = headpos;
	copyArr();
	qsort (copyreq, 1000, sizeof(int), compare);
	if(pos <= copyreq[0]){
		return copyreq[999] - pos;
	}
	int ind = 0;
	while(pos > copyreq[ind]){
		ind++;
	}
	ind--;
	int res = copyreq[999] - pos;
	if(pos > copyreq[0]){
		res += 4999 - copyreq[999];
		res += 4999;
		res += copyreq[ind];
	}
	return res;
}
int look(){
	int pos = headpos;
	copyArr();
	qsort (copyreq, 1000, sizeof(int), compare);
	int res;
	if(pos >= copyreq[999]){
		res = pos - copyreq[0];
	}
	else if(pos <= copyreq[0]){
		res = copyreq[999] - pos;
	}
	else{
		res = copyreq[999] - pos;
		res += copyreq[999] - copyreq[0];
	}
	return res;
}
int clook(){
	int pos = headpos;
	copyArr();
	qsort (copyreq, 1000, sizeof(int), compare);
	if(pos <= copyreq[0]){
		return copyreq[999] - pos;
	}
	int ind = 0;
	while(pos > copyreq[ind] && ind < 1000){
		ind++;
	}
	ind--;		
	int res = abs(copyreq[999] - pos);
	res += copyreq[999] - copyreq[0];
	res += copyreq[ind] - copyreq[0];
	return res;
}
int main(int argc, char *argv[]){
	if (argc < 2 || argc > 3) {
		fprintf(stderr, "usage: ./diskschedule <headpos>\n"); 
		return -1; 
	}
	headpos = atoi(argv[1]);
	if(headpos < 0 || headpos > 4999){
		fprintf(stderr, "Wrong headpos. Required [0,4999]\n"); 
		return -1;
	}
	if(argc == 3){
		char* inputfilename = argv[2];	
		FILE * fp;
		char * line;
		ssize_t read;
		size_t len;
		fp = fopen(inputfilename, "r");
		for(int i = 0; i < 1000; i++){
		    int ind, cyl;
		    fscanf(fp, "%d %d", &ind, &cyl);
			requests[ind - 1] = cyl;
		}
	}
	else{
		srand(time(NULL));   // Initialization, should only be called once.
		for(int i = 0; i < 1000; i++){
			requests[i] = rand() % 5000;
		}
	}

	printf("FCFS: %d\n", fcfs());
	printf("SSTF: %d\n", sstf());
	printf("SCAN: %d\n", scan());
	printf("C-SCAN: %d\n", cscan());
	printf("LOOK: %d\n", look());
	printf("C-LOOK: %d\n", clook());
}
