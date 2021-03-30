/**
 * FILE: input.h
 * AUTHOR: Christian Brunette
 *
 * PURPOSE: Define function prototypes relating to input.c.
 */

#ifndef INPUT_H
#define INPUT_H

#include "spellChecker.h"
#include "check.h"

#define MAX_NUM_WORDS 4096

void readInputFile(FILE* file, char tempArray[MAX_NUM_WORDS][BUFFERLEN], int* arrayLen);

char** readDictFile(FILE* file, int* array_len);

void readSettings(FILE *settingsFile, int* maxCorrectionVal, char** dictName, int* autoVal);

int getNumWords(FILE* inputFile, int* arrayLen);

int* getNewLines(FILE *inputFile, int *arrayLen);

#endif
