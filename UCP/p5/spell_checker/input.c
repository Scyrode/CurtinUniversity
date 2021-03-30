/**
 * FILE: input.c
 * AUTHOR: Christian Brunette
 *
 * PURPOSE: Define functions here that deal with
 *          reading and manipulating the user input.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "input.h"
#include "spellChecker.h"

/**
 * FUNCTION NAME: readInputFile
 * PURPOSE: Read the given file into an array, which is on the stack. (FIXED SIZE)
 * IMPORTS: Requires a FILE*, the settings file.
 * EXPORTS: the number of words in the file via the arrayLen pointer,
 * 			and the word array itself via a return.
 */

void readInputFile(FILE* file, char tempArray[MAX_NUM_WORDS][BUFFERLEN], int* arrayLen)
{
	int eof = FALSE;

	*arrayLen = 0;

	/**
	* Perform the reading and storing into the statcally-sized array.
	*/
	do
	{
		fscanf(file, " %s", tempArray[*arrayLen]);
		if(!feof(file)){
			(*arrayLen)++;
		}else{
			eof = TRUE;
		}
	}while(!eof);

	if(ferror(file))
	{
		perror("Error while reading file.");
	}
}


/**
 * FUNCTION NAME: readFileToArray
 * PURPOSE: Read the given file into an array, dynamically allocating it as we go.
 * IMPORTS: Requires a FILE*, the settings file.
 * EXPORTS: the number of words in the file via the arrayLen pointer,
 * 			and the word array itself via a return.
 *
 * NOTE:    The file is read twice in total. The first time is to obtain the
 *			number of words (in order to allocate the array of the correct size)
 *			and the second time actually reads the words and copies them into the
 *			array.
 */
char** readDictFile(FILE* file, int* arrayLen)
{
	char tempString[BUFFERLEN];
	char** wordArray = NULL;
	int ii;
	int eof = FALSE;

	*arrayLen = 0;

	/**
	 * First, count the number of words in the file in order to create an array
	 * of the correct size in which to store the data.
	 */

	do
	{
		fscanf(file, " %s", tempString);
		if(!feof(file)){
			(*arrayLen)++;
		}else{
			eof = TRUE;
		}
	}while(!eof);

	rewind(file);
	wordArray = (char**)malloc(sizeof(char*) * *arrayLen);

	/**
	 * Now perform the reading and storing into the array.
	 */

	for(ii = 0; ii < *arrayLen; ii++)
 	{
 		fscanf(file, " %s", tempString);
		wordArray[ii] = (char*)malloc(sizeof(char) * (strlen(tempString) + 1));
		strcpy(wordArray[ii], tempString);
		wordArray[ii][strlen(tempString)] = '\0';
 	}

	if(ferror(file))
	{
		perror("Error while reading file.");
		wordArray = NULL;
	}

	return wordArray;
}



/**
 * FUNCTION NAME: assignSettings
 * PURPOSE: Read the settings file "spellrc", check that
 *          it is in the correct format, and if it is,
 *          store the values read into a struct.
 * IMPORTS: Requires a FILE*, the settings file.
 * EXPORTS: the max correction value, dictionary name and autocorrect value (TRUE/FALSE)
 *			through the pointers which are imported.
 * ASSERTIONS:
 * 		PRE: The file must be in the correct format,
 *            and have been opened previously.
 *
 * VARIABLES EXPLANATION:
 *    inputType, dictName and autoVal are three temporary
 *    buffers (of size 200) used to store the latest values
 *    read from the file.
 *
 *    int correction: A temporary placeholder for the value
 *                    read called "autocorrect".
 */

void readSettings(FILE *settingsFile, int* maxCorrectionVal, char** dictName, int* autoCorrect)
{
	/**
	 * Buffers of large size (200) to hold other
	 * values if input format is unexpected.
	 */
	char inputType[BUFFERLEN] = "Empty";
	char tempDictName[BUFFERLEN] = "Empty";
	char autoVal[BUFFERLEN] = "Empty";

	int error = FALSE;

   	/**
	 * Checks that the input matches one of the expected strings,
     * then reads the and assigns the next value accordingly.
     * This will continue to run, overwriting old buffer values
     * until the end of file is reached, or an error occurs.
     */

	do
	{
		if(!feof(settingsFile) && error == FALSE)
		{
			fscanf(settingsFile, "%s", inputType);

			if(strcmp(inputType, "maxcorrection") == 0)
			{
				fscanf(settingsFile, " = %d", maxCorrectionVal);
			}
			else if(strcmp(inputType, "dictionary") == 0)
			{
				fscanf(settingsFile, " = %s", tempDictName);
			}
			else if(strcmp(inputType, "autocorrect") == 0)
			{
				fscanf(settingsFile, " = %s", autoVal);
				if(strcmp(autoVal, "yes") == 0)
				{
					*autoCorrect = TRUE;
				}
                else if(strcmp(autoVal, "no") == 0)
				{
					*autoCorrect = FALSE;
				}
				else
				{
					error = TRUE;
				}
			}
			else
			{
				error = TRUE;
			}
		}
	}while(!feof(settingsFile) && !ferror(settingsFile) && error == FALSE);


   	/* If no errors occured, place the dictionary name into a dynamic array. */
	if(error == FALSE && !ferror(settingsFile))
	{
		*dictName = (char*)(malloc(sizeof(char)*(strlen(tempDictName)+1)));
		strcpy(*dictName, tempDictName);

		fflush(stdout);
	}
	else if(ferror(settingsFile))
	{
		perror("\nError while reading settings file");
		*dictName = NULL;
		fflush(stdout);
	}
	else
	{
		printf("\nIncorrect format in settings file.\n");
		*dictName = NULL;
		fflush(stdout);
	}
}


/**
 * FUNCTION NAME: getNewLines
 * PURPOSE: To find the location of each new line
 *          in the input file, so that it can be
 *          used later to ensure the output file
 *          format is the same as the input.
 * IMPORTS: The input file and a pointer to
 *          the length of the new line array.
 * EXPORTS: The new line array, containing data
 *          that describes where each new line
 *          occurs.
 * METHOD:  Reads the input file twice, the first time is to determine
 *          the amount of new lines there are, to see how large the
 *          new line array must be.
 *          The second time:
 *          Reads the input file character by character, storing the
 *          value in 'c', until the end of file is reached, or an
 *          error occurs. Checks for a whitespace, which indicates
 *          the end of one word. Also checks for '\n', and if one
 *          is encountered, the number of words before the newline
 *          is stored in an Array.
 *
 * VARIABLES EXPLANATION:
 *        int numWords: A counter that keeps track of how many
 *                      words there are between each new line.
 *
 * COMMENTS:  Quite messy in my opinion, and there may be
 *            underlying errors that I am not aware of,
 *            however I did test it extensively and it
 *            behaves as I would like it to.
 *            Note: this function also makes corrections
 *            for double (or more) spacing, as well as
 *            paragraph breaks.
 */

int* getNewLines(FILE *inputFile, int *arrayLen)
{
	int *newLineArray;
	int numWords = 0;
	int numNewLines = 0;
	int ii = 0;
	char c;
    char cPrev = '\0';

    /**
     * Read through the file once counting the number of newlines.
     * This is used to determine the size of the newline Array.
     */
    do
    {
        c = fgetc(inputFile);

        if(c == '\n')
        {
        	numNewLines++;
        }
    }while(c != EOF && !ferror(inputFile));

  	rewind(inputFile);

    *arrayLen = numNewLines;
	newLineArray = (int*)(malloc(sizeof(int)*(*arrayLen)));

	do
	{
		c = fgetc(inputFile);

      	/*Double spacing correction, counting number of words.*/
		if(c == ' ' && cPrev != c && cPrev != '\n')
		{
			numWords++;
		}

      	/*Detects newline, saves number of words to Array.*/
		else if(c == '\n' && cPrev != c && cPrev != ' ')
		{
			numWords++;
			newLineArray[ii] = numWords;
			numWords = 0;
			ii++;
		}

      	/*Detects and accounts for paragraph line breaks here.*/
		else if(c == '\n' && cPrev == c)
		{
			newLineArray[ii] = 0;
			ii++;
		}
		cPrev = c;
	}while(c != EOF && !ferror(inputFile));

	if(ferror(inputFile))
	{
		perror("Error while checking for newlines\n");
		newLineArray = NULL;
	}

   	/*Rewind the input file so that it can later be read from the beginning.*/
	rewind(inputFile);

	return newLineArray;
}
