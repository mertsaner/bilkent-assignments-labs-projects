#include <stdio.h>
int main(void){
	int x = 5; // x is assigned to 5 first.
	void foo(){
		x = 6;
		printf("%d\n", x); // 1st print
			
	}
	void bar(){
		void cet(){
			x = 4;
			printf("%d\n", x); // 2nd print
		}
		x = 7; // x is assigned to 7 in bar. foo() and cet() are called respectively.
		foo(); 
		cet();		
		printf("%d\n", x); // 3rd print
		
	}
	bar(); 
	printf("%d\n", x); // last print
}
