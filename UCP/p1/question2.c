#include <stdio.h>

int main(void) {
    int x,y; 
    printf("Insert two numbers: ");
    scanf("%d %d", &x, &y);
    if (x % y == 0) {
	    printf("divisble!\n");
    } else {
	    printf("not divisible!\n");
    }
    return 0;
}
