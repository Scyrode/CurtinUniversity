#include "numbers.h"

int main(void) {

    orderPointer orderPtr;
    char* ch;
    int* ptr1;
    int* ptr2;
    int* ptr3;

    do {

	ch = (char*) malloc(sizeof(char));
	ptr1 = (int*) malloc(sizeof(int));
	ptr2 = (int*) malloc(sizeof(int));
	ptr3 = (int*) malloc(sizeof(int));

	readInts(ptr1, ptr2, ptr3, ch);

	
	if ((*ch == 'a') || (*ch == 'A') || (*ch == 'd') || (*ch == 'D')) {
	    
	    orderPtr = order(ch);

	    (*orderPtr) (ptr1, ptr2, ptr3);

	    printf("\n%d, %d, %d\n", *ptr1, *ptr2, *ptr3);

	} else {
	    printf("must choose ascending or dscending\n");
	}

	free(ptr1);
	free(ptr2);
	free(ptr3);
	free(ch);

    } while (orderPtr == NULL);

        return 0;

}
