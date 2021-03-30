#include "randomNumber.h"

int main(void) {

    int x = 1;
    int y = 2;
    int z = 3;
    int w = 4;
    int m = 5;
    void* ptr1 = &x;
    void* ptr2 = &y;
    void* ptr3 = &z;
    void* ptr4 = &w;
    void* ptr5 = &m;
    void* array[] = {ptr1, ptr2, ptr3, ptr4, ptr5};
    void* result = (void*) randFunction(array, ARRAYLENGTH);
    printf("%d\n",*(int*) result);

}

void* randFunction(void* array[], int length) {

    srand(time(NULL));
    int i = rand() % length;
    return array[i];

}
