#include <stdio.h>
void foo(int a = 5, int b = 4, int c = 3){
	printf("%d\n", a * b + c);
}
/*
template<typename T, typename... Args>
T bar(T first, Args... args){
	if(args.empty)
		return 0;
	return bar(first + bar(args...));
}
*/
void passBy(int x, int* y){
	x = 3; // Pass by value 
	*y = 3; // Pass by reference
	printf("Inside passBy x = %d, y = %d\n", x, *y); // Prints 3, 3
}

int main(){
	foo(10, 9, 8); //Positional Parameters / a = 10, b = 9, c = 8 / prints 720
	foo(); // a, b and c as Default Paramaters / a = 5, b = 4, c = 3 / prints 60
	foo(10); // b and c as Default Parameters / a = 10, b = 4, c = 3 / prints 120
	/*
	printf("%d\n", bar());
	printf("%d\n", bar(10));
	printf("%d\n", bar(10, 20, 30));
	*/
	int x = 5;
	int y = 4;
	passBy(x, &y);
	printf("After passBy x = %d, y = %d\n", x, y); // Prints 5, 3

}

