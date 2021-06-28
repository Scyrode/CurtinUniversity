/*************************************************
* Author: Ahmad Allahham                         *
* Filename: Task1.c                              *
* Date: 10/05/2021                               *
*************************************************/
#include "Functions.h"

int main(void)
{
    char fileName[14];
    char* programOutput;
    do {
        printf("PP simulation: ");
        scanf("%s", fileName);
        if (strcmp(fileName, "QUIT") != 0) {
            programOutput = runPPProgram(fileName);
            printf("%s", programOutput);
            printf("----------------------------------------------------------------\n");
        }
    } while (strcmp(fileName, "QUIT") != 0);
    return 0;
}