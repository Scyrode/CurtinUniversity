/**
 * FILE: output.h
 * AUTHOR: Christian Brunette
 *
 * PURPOSE: Define function prototypes relating to output.c.
 */


#ifndef OUTPUT_H
#define OUTPUT_H

#include "spellChecker.h"
#include "check.h"

ActionFunc chooseCallBack(int autocorrect);

int askUser(char *word, char *suggestion);

int dontAskUser(char *word, char *suggestion);

char** replacePunctuation(char **Array, char *punctArray,  int arrayLen);

void writeArrayToFile(char **inputArr, int inputArrLen, FILE *inputFile,
   							int *newLineArr, int newLineArrLen);

void freeArray(char **inputArr, int arrayLen);

#endif
