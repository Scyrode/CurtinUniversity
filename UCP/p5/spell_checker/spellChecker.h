/**
 * FILE: spellChecker.h
 * AUTHOR: Christian Brunette
 *
 * PURPOSE: Herein is defined the structs used throughout
 *          the program, a macro that is used
 *          to determine wether or not a character is
 *          'punctuation', and a pre-processor constant
 *          used to set the bufferlength of some
 *          temporary string variables.
 */


#ifndef SPELLCHECK_H
#define SPELLCHECK_H

/**
 * This macro is used within an 'if' statement, to check
 * if a character is punctuation. (Only valid for the
 * nine most common punctuation marks (according to
 * google)).
 */
#define IS_PUNCTUATION(ch) ((int)(ch) == '.' || (int)(ch) == ','  || \
                            (int)(ch) == '?' || (int)(ch) == '!'  || \
      						(int)(ch) == ';' || (int)(ch) == ':'  || \
                            (int)(ch) == '-' || (int)(ch) == '\'' || \
               				(int)(ch) == '"')

/*This defines a buffer length of 200, used for some temporary strings.*/
#define BUFFERLEN 200


/**
 * A struct to contain the values read from the settings file.
 *
 *  maxcorrectionVal: The maximum number of corrections that
 *                    can be made to a word by 'check'.
 *    dictionaryName: The designated name of the dictionary file
 *                    to be used to check the input against.
 * autoCorrectOption: A value used to determine wether
 *                    or not to ask the user for permission
 *                    to change the word.
 */

#endif
