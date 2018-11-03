#include <stdio.h>

void foo(int a, int b, int c){
	printf("%d\n", a * b + c);
}

void passBy(int a, int* b){
	a = 3;
	*b = 3;
	printf("Inside passBy a = %d, b = %d\n", a, *b);
}


int main(void){
	foo(10, 9, 8); //Positional Parameters / a = 10, b = 9, c = 8 / prints 720
	
	int a = 5;
	int b = 4;
	printf("Initially a = %d, b = %d\n", a, b);
	passBy(a, &b);
	printf("After passBy a = %d, b = %d\n", a, b);
}

