/*************************************************
* Author: Ahmad Allahham                         *
* Filename: Task2.c                              *
* Date: 10/05/2021                               *
*************************************************/
#include "Functions.h"

int main(void)
{
    char fileName[14];
    char* programOutput;
    do {
        printf("SRTF simulation: ");
        scanf("%s", fileName);
        if (strcmp(fileName, "QUIT") != 0) {
            programOutput = runSRTFProgram(fileName);
            printf("%s", programOutput);
            printf("----------------------------------------------------------------\n");
        }
    } while (strcmp(fileName, "QUIT") != 0);
    return 0;
}