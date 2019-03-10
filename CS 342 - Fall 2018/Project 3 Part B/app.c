#include <stdio.h>
#include <stdlib.h>
int factorial(int n);
int recursion_control;
int recursion_control_max;
int main(void){
	int choice;
	int size = 0;
	void* a;
	factorial(1000);
	while(1){
		recursion_control = 1;
		printf("1.Allocate 1.000.000 bytes\n2.Deallocate all\n3.Recursive call\n0.Quit\nEnter the number corresponding to your choice:");
		scanf("%d", &choice);
		if(!choice)
			return 0;
		if(choice == 1){
			if(size == 0)
				for(int i = 0; i < 10; i++)
					a = malloc(1000000);
			else
				a = realloc(a, size);
		 	size += 1000000;
		}
		else if(choice == 2){
			if(size)
				free(a);
			size = 0;
		}
		else if(choice == 3){
			printf("Enter call number: ");
			int num;
			scanf("%d", &num);
			recursion_control_max = num - 2;
			factorial(num);
			return;
			size = 0;
		}
	}
}

int factorial(int n){
	recursion_control++;
	char* s;
	if(recursion_control == recursion_control_max){
		printf("Check stack. Then type something and press enter to finish.");
		scanf("%s", s);
	}
	if(n < 1)
		return 1;
	return n * factorial(n-1);
}
