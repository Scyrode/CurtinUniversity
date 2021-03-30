#include "user_input.h"

void readInts(int* x, int* y, int* z, char* ch) {

    printf("write first number: ");
    scanf("%d", x);

    printf("write second number: ");
    scanf("%d", y);

    printf("write third number: ");
    scanf("%d", z);

    printf("Write 'A' for ascending or 'D' for descending: ");
    scanf(" %c", ch);

}
