#include "order.h"

void ascending2(int* x, int* y) {

    int temp;

    if (*x > *y) {

	temp = *x;
	*x = *y;
	*y = temp;

    }

}

void ascending3(int* x, int* y, int* z) {

    ascending2(x, y);
    ascending2(x, z);
    ascending2(y, z);

}

void descending3(int* x, int* y, int* z) {

    ascending2(y, x);
    ascending2(z, x);
    ascending2(z, y);

}

orderPointer order(char* ch) {

    orderPointer ptr;

    switch(*ch) {

	case 'A': case 'a':
	    ptr = &ascending3;
	    break;
	case 'D': case 'd':
	    ptr = &descending3;
	    break;
	default:
	    ptr = NULL;

    }

    return ptr;

}
