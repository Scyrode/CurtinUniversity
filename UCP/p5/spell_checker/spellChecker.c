/**
 * FILE: spellChecker.c
 * AUTHOR: Christian Brunette
 *
 * PURPOSE: Spell check a text file given by the user.
 *
 * COMMENTS: There are some extra functionalities
 *           which can be implemented as described
 *           in the README.txt file.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "spellChecker.h"
#include "input.h"
#include "output.h"
#include "check.h"


/**
 * NUMARGS is the number of arguments needed for the program to
 * run properly. The first being the ./run command, the second
 * being the name of the user input file, and the third being
 * the name of the output file.
 */
static const int NUM_ARGS = 3;


/**
 * FUNCTION NAME: main
 * PURPOSE: To call multiple functions that read input files,
 * 			place the contents into arrays, then spell check
 *          the contents before writing back to the output file.
 * IMPORTS: Requires two string inputs, a user file to be checked,
 *          and an output file name.
 * EXPORTS: Returns 0, indicating a normal exit or a non-zero
 * 			value if the program exited abnormally.
 *
 * VARIABLES EXPLANATION:
 * int *newLineArr: An array that contains information about the
 *                  location of new lines in the origional input
 *                  file. Each entry is a number representing
 *                  how many words there are between new lines.
 */

int main(int argc, char *argv[])
{
    /*Variable Declarations*/

    int settings_maxCorrection = -1;
    char* settings_dictionaryName = NULL;
    int settings_autocorrect = -1;

    FILE *inputFile, *outputFile, *dictFile, *settingsFile;

    char **dictArr;
    char tempArr[MAX_NUM_WORDS][BUFFERLEN];

    char **mallocedArray;
    int mallocedLen = 0;

    char settingsName[8] = "spellrc";
    int *newLineArr;

    int dictArrLen = 0;
    int inputArrLen = 0;
    int newLineArrLen = 0;

    int ii;

    ActionFunc action;

    char* format = "./check \"fileName.extension\"";

	if(argc != NUM_ARGS)
	{
        printf("Error, invalid number of input arguments!\nFormat: \n%s \n", format);
	}
	else
	{
		settingsFile = NULL;
		settingsFile = fopen(settingsName, "r");
		if(settingsFile == NULL)
		{
			perror("Could not open the Settings file");
		}
		else
		{
			printf("Reading settings file (\"%s\")...", settingsName);
			fflush(stdout);
			readSettings(settingsFile, &settings_maxCorrection,\
                         &settings_dictionaryName, &settings_autocorrect);
            printf(" Done\n");
			fclose(settingsFile);
			settingsFile = NULL;

            inputFile = NULL;
            dictFile = NULL;

			if(settings_dictionaryName != NULL)
			{
				inputFile = fopen(argv[1], "r");
				if(inputFile == NULL)
				{
					perror("Error opening the user file");
				}
				else
				{
					dictFile = fopen(settings_dictionaryName, "r");
					if(dictFile == NULL)
					{
						perror("Could not open dictionary file");
					}
					else
					{
						printf("Reading \"%s\"...", settings_dictionaryName);
						fflush(stdout);
						dictArr = readDictFile(dictFile, &dictArrLen);
                        printf(" Done\n");
                        fclose(dictFile);
						dictFile = NULL;

						newLineArr = NULL;
						newLineArr = getNewLines(inputFile, &newLineArrLen);

						printf("Reading input from: \"%s\"...", argv[1]);
						fflush(stdout);
						readInputFile(inputFile, tempArr, &inputArrLen);
                        printf(" Done\n");
						fclose(inputFile);
						inputFile = NULL;

                        /* Print out the array for debugging perposes */
                        /* Each word in the input file will be printed to a new line. */
                        for(ii = 0; ii < inputArrLen; ii++){
                            printf("%d. %s\n", ii, tempArr[ii]);
                        }

                        printf("\nDone printing.\n");

                        /*
                         * Now copy the input contents into a dynamically
                         * allocated array to save memory.
                         */
                        mallocedArray = (char**)malloc(sizeof(char*) * inputArrLen);
                        for(ii = 0; ii < inputArrLen; ii++){
                            mallocedArray[ii] = (char*)malloc(sizeof(char) * (strlen(tempArr[ii]) + 1));
                            strcpy(mallocedArray[ii], tempArr[ii]);
                            mallocedArray[ii][strlen(tempArr[ii])] = '\0';
                            mallocedLen++;
                        }

						action = chooseCallBack(settings_autocorrect);

						printf("Running check function...");
						fflush(stdout);
						check(mallocedArray, mallocedLen,
							  dictArr, dictArrLen,
							  settings_maxCorrection, action);
						printf(" Done\n");
						fflush(stdout);

						outputFile = fopen(argv[2], "w");
                        if(outputFile == NULL)
                        {
                            perror("Could not open output file");
                        }
                        else
                        {
                            writeArrayToFile(mallocedArray, mallocedLen, outputFile,
                                             newLineArr, newLineArrLen);

                            fclose(outputFile);
                            outputFile = NULL;
                        }

                        /*Freeing and setting pointers to NULL.*/
						freeArray(dictArr, dictArrLen);
                        dictArr = NULL;

						freeArray(mallocedArray, mallocedLen);
                        mallocedArray = NULL;

						free(newLineArr);
						newLineArr = NULL;

                        free(settings_dictionaryName);
                        settings_dictionaryName = NULL;
					}
				}
			}
		}
	}

	return 0;
}
