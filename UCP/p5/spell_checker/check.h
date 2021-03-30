/**
 * Author: David Cooper
 * Modifications by Christian Brunette
 */

#ifndef CHECK_H
#define CHECK_H

#define FALSE 0
#define TRUE !FALSE

typedef int (*ActionFunc)(char* word, char* suggestion);

void check(char* text[], int textLength,
           char* dict[], int dictLength,
           int maxDifference, ActionFunc action);

#endif
