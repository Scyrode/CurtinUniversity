#ifndef ORDER_H
#define ORDER_H

#include <stdio.h>

typedef void (*orderPointer) (int*,int*,int*);

void ascending2(int* x, int* y);
void ascending3(int* x, int* y, int* z);
void descending3(int * x, int* y, int* z);
orderPointer order(char* ch);

#endif
