/**
 * FILE: output.c
 * AUTHOR: Christian Brunette
 * UNIT: UNIX and C Programming (COMP1000)
 *
 * PURPOSE: Define functions here that deal with
 *          modifying and outputting values.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "output.h"
#include "spellChecker.h"
#include "check.h"


/**
 * FUNCTION NAME: chooseCallBack
 * PURPOSE: Choose a suitable callback function based on the
 *          autocorrect option given in the settings file.
 * IMPORTS: The autocorrect option, from the settings file. (yes/no)
 * EXPORTS: Returns a pointer to a function.
 */

ActionFunc chooseCallBack(int autocorrect)
{
	ActionFunc action = NULL;

	if(!autocorrect)
	{
		action = &askUser;
	}
	else
	{
		action = &dontAskUser;
	}
	return action;
}


/**
 * FUNCTION NAME: askUser
 * PURPOSE: Ask the user to approve each word changed by 'check'.
 * IMPORTS: The origional word and the suggestion.
 * EXPORTS: True or false based on used input.
 */

int askUser(char *word, char *suggestion)
{
	int userOption;

	if(suggestion != NULL)
	{
		printf("Your word: '%s', Suggestion: '%s'.\n", word, suggestion);
		printf("Would you like to accept these changes? (0 = no, 1 = yes).\n");
		fflush(stdout);
		scanf("%d", &userOption);
	}
	else
	{
		userOption = FALSE;
	}
	return userOption;
}



/**
 * FUNCTION NAME: dontAskUser
 * PURPOSE: Approve all suggested changes without asking for confirmation.
 * IMPORTS: The origional word and the suggestion.
 * EXPORTS: True or false based on used input.
 */

int dontAskUser(char *word, char *suggestion)
{
	int option;

	if(suggestion != NULL)
	{
		option = TRUE;
	}
	else
	{
		option = FALSE;
	}
	return option;
}




/**
 * FUNCTION NAME: writeArrayToFile
 * PURPOSE: Output the final array with modifications and
 *          punctuation, writing it back to the origional
 *          input file. This also includes the origional
 *          formatting, with newlines and paragraphs.
 * IMPORTS: The final array or words and its length.
 *          A pointer to an open input file, to which to write to.
 *          The newLineArr, and its length.
 *
 * ASSERTIONS:
 * 		PRE: inputFile must have been opened in "w" mode.
 *      POST: Will print all words in the array (which may or
 *            may not have been corrected) to the origional file,
 *            including punctuation and format.
 *
 * VARIABLES EXPLANATION:
 *    int numWordsPrinted: Counts the number of words printed,
 *                         this is used to determine when a new
 *                         line should be printed.
 *
 * COMMENTS:  The ifdef statement allows the user to choose
 *            wether or not to include the format corrections.
 *            Please refer to the Makefile and README.txt for
 *            further explanation.
 */

void writeArrayToFile(char **inputArr, int inputArrLen, FILE *inputFile,
      				     int *newLineArr, int newLineArrLen)
{
	int ii, jj = 0;
	int numWordsPrinted = 0;

	for(ii = 0; ii < inputArrLen; ii++)
	{
    	if(!ferror(inputFile))
      	{
       		#ifndef EXTRAS_OFF
       		/**
        	* If the number of words printed matches an element in
        	* newLineArray, print a new line to the file.
        	*/
	    	if(jj < newLineArrLen && numWordsPrinted == newLineArr[jj])
	    	{
				fprintf(inputFile, "\n");
		    	numWordsPrinted = 0;
        		/*Incriment jj, to check the next element of newLineArr.*/
		    	jj++;
        		/*Decriment ii, since the ii'th word was not printed.*/
		    	ii--;
	    	}
	    	else
	    	{
	 			fprintf(inputFile, "%s ", inputArr[ii]);
		    	numWordsPrinted++;
	    	}
        	#else

	        /*Prints all words with only one whitespace between each.*/
	     	fprintf(inputFile, "%s ", inputArr[ii]);
	  	 	numWordsPrinted++;
	    	#endif
      	}
	}
	fprintf(inputFile, "\n");
	fflush(inputFile);

   	if(ferror(inputFile))
   	{
    	perror("Error while writing to file");
   	}
   	else
   	{
      	printf("Completed Spellchecking Succesfully!\n");
   	}
}


/**
 * FUNCTION NAME: freeArray
 * PURPOSE: Free an entire array of strings.
 * IMPORTS: An array of strings. (The input array or dictionary array).
 */

void freeArray(char **inputArr, int arrayLen)
{
	int ii;

	for(ii = 0; ii < arrayLen; ii++)
	{
		free(inputArr[ii]);
		inputArr[ii] = NULL;
	}
	free(inputArr);
	inputArr = NULL;
}
