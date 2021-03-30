/**
 * Author: David Cooper
 * Modifications by Christian Brunette
 *
 * Spell checking code
 *
 * This is NOT example code. You are NOT expected to adapt/memorise/etc this
 * code, or use it to study for the exam.
 *
 * This file contains a check() function, which uses a trie (a type of tree) to
 * verify the spelling of an array of words, and to make suggestions/corrections
 * for misspelled words.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "check.h"

#define FIRST_ASCII_CH ' '
#define LAST_ASCII_CH '~'
#define N_TRIE_CHILDREN (LAST_ASCII_CH - FIRST_ASCII_CH + 1)

#define IS_ASCII(ch) (FIRST_ASCII_CH <= (ch) && (ch) <= LAST_ASCII_CH)
#define CH_INDEX(ch) ((int)ch - FIRST_ASCII_CH)

/**
 * This is a trie node. It has an array of N_TRIE_CHILDREN pointers to child
 * nodes (many/most of which are likely to be NULL).
 *
 * A structure built up from trie nodes can represent a set of strings (e.g.
 * dictionary words). Each node in the trie represents a common prefix in one
 * or more strings. Its child nodes indicate what letters follow on from this
 * prefix in any of the relevant strings. The root node represents the start
 * of all the strings.
 *
 * The isWord field is a boolean flag. If TRUE, it means that this node
 * represents a complete string, not just a prefix (though it may still be a
 * prefix for other strings).
 */
typedef struct TrieNode {
    int isWord;
    struct TrieNode* children[N_TRIE_CHILDREN];
} TrieNode;

/**
 * Constructs and initialises a new trie node, setting all the child links to
 * NULL.
 */
static TrieNode* newTrieNode()
{
    TrieNode* newNode = (TrieNode*)malloc(sizeof(TrieNode));
    int c;

    newNode->isWord = FALSE;
    for(c = 0; c < N_TRIE_CHILDREN; c++) {
        newNode->children[c] = NULL;
    }
    return newNode;
}

/**
 * Used by addTrieWord() to actually add a word to the trie, allocating any new
 * nodes required.
 */
static void addTrieWordRecurse(TrieNode* node, char* word)
{
    int index = *word - FIRST_ASCII_CH;

    if(node->children[index] == NULL) {
        node->children[index] = newTrieNode();
    }

    if(word[1] == '\0') {
        node->children[index]->isWord = TRUE;
    }
    else {
        addTrieWordRecurse(node->children[index], word + 1);
    }
}

/**
 * Adds a word to a trie. This function first verifies that the word contains
 * only 7-bit printable ASCII characters (everything between ' ' and '~' - the
 * first and last printable ASCII characters - inclusive).
 */
static void addTrieWord(TrieNode* root, char* word)
{
    int c = 0;
    while(IS_ASCII(word[c])) {
        c++;
    }

    if(word[c] != '\0') {
        printf("Error: your dictionary contains a word '%s' with non-ASCII character(s)!\n", word);
    }
    else if(*word == '\0') {
        printf("Error: your dictionary contains a zero-character word!\n");
    }
    else
    {
        addTrieWordRecurse(root, word);
    }
}

/**
 * Constructs and returns a trie given a list of words.
 */
static TrieNode* buildTrie(char* dict[], int dictLength)
{
    TrieNode* root = newTrieNode();

    int d;
    for(d = 0; d < dictLength; d++)
    {
        addTrieWord(root, dict[d]);
    }

    return root;
}

/**
 * Prints out the structure a trie structure; used for debugging purposes.
 */
#ifdef DEBUG
    static void printTrie(TrieNode* node)
    {
        int c;
        printf("[");
        for(c = 0; c < N_TRIE_CHILDREN; c++)
        {
            if(node->children[c] != NULL)
            {
                putchar((char)(c + FIRST_ASCII_CH));
                if(node->children[c]->isWord)
                {
                    putchar('*');
                }
                printTrie(node->children[c]);
            }
        }
        printf("]");
    }
#endif

/**
 * Deallocates (recursively) all memory used by a trie.
 */
static void freeTrie(TrieNode* node)
{
    int c;
    if(node != NULL) {
        for(c = 0; c < N_TRIE_CHILDREN; c++) {
            freeTrie(node->children[c]);
        }
        free(node);
    }
}

static int inTrie(TrieNode* root, char* word, char* suggestion, int maxEdits);

static int inTrieInsert(TrieNode* node, char* word, char* suggestion, int maxEdits)
{
    return inTrie(node, word + 1, suggestion, maxEdits);
}

static int inTrieTranspose(TrieNode* node, char* word, char* suggestion, int maxEdits)
{
    int result = FALSE;
    TrieNode* nextNode = node->children[CH_INDEX(word[1])];

    if(IS_ASCII((int)word[1]) && nextNode != NULL)
    {
        TrieNode* nextNextNode = nextNode->children[CH_INDEX(word[0])];
        if(nextNextNode != NULL)
        {
            suggestion[0] = word[1];
            suggestion[1] = word[0];
            result = inTrie(nextNextNode, word + 2, suggestion + 2, maxEdits);
        }
    }
    return result;
}

static int inTrieDeleteModify(TrieNode* node, char* word, char* suggestion, int maxEdits)
{
    int result = FALSE;
    char ch = FIRST_ASCII_CH;
    while(!result && ch <= LAST_ASCII_CH)
    {
        if(node->children[CH_INDEX(ch)] != NULL) {
            *suggestion = ch;

            /* Modification */
            result = inTrie(node->children[CH_INDEX(ch)], word + 1, suggestion + 1, maxEdits);

            if(!result) {
                /* Deletion */
                result = inTrie(node->children[CH_INDEX(ch)], word, suggestion + 1, maxEdits);
            }
        }
        ch++;
    }
    return result;
}


/**
 * Checks whether a trie contains a word, or a slightly modified version of a
 * word.
 *
 * Parameters:
 * 'root' points to the root of the trie.
 * 'word' is the word to find.
 * 'suggestion' is the word actually found. This may be exactly equal to
 *     'word', or a slightly edited version of it.
 * 'maxEdits' is the maximum difference between 'word' and 'suggestion', in
 *     edits, where an edit is a deletion, replacement, or insertion of a
 *     character, or the transposition of two adjacent characters.
 *
 * The function returns TRUE if the word, or a slightly edited version of it,
 * was found, and FALSE otherwise. If FALSE is returned, the contents of
 * 'suggestion' are undefined.
 */
static int inTrie(TrieNode* root, char* word, char* suggestion, int maxEdits)
{
    int result = FALSE;

    if(IS_ASCII(*word))
    {
        if(root->children[CH_INDEX(*word)] != NULL) {
            *suggestion = *word;

            /* The current character matches. Recurse to check the next character. */
            result = inTrie(root->children[CH_INDEX(*word)], word + 1, suggestion + 1, maxEdits);
        }

        /* We haven't found a match, so finding an edited version. */
        if(!result && maxEdits > 0)
        {
            maxEdits--;

            /* This relies on short-circuit evaluation, trying an insert, then
             * a transposition, then a deletion or replacement, stopping at the
             * first edit that works. */
            result =
                inTrieInsert(root, word, suggestion, maxEdits) ||
                inTrieTranspose(root, word, suggestion, maxEdits) ||
                inTrieDeleteModify(root, word, suggestion, maxEdits);
        }
    }
    else if(*word == '\0' && root->isWord)
    {
        /* Success! We've found a complete (though possibly edited) match. */
        *suggestion = '\0';
        result = TRUE;
    }

    return result;
}

/**
 * Checks the spelling of a list of words against a list of dictionary words.
 * Any misspellings are reported to a callback function, along with a suggested
 * correction. The suggested correction is chosen to minimise the "edit
 * difference" between it and the original word. If no dictionary word is
 * within the maximum allowable distance from the original word, the callback
 * receives NULL instead of a suggestion.
 *
 * The callback function returns either TRUE or FALSE, indicating whether the
 * suggested correction should be applied. If TRUE, the memory allocated to the
 * original word is realloc'd to make space for the corrected word, which is
 * then copied into it.
 *
 * Parameters:
 * text          - an array of words to spell check (each word must be
 *                 dynamically allocated);
 * textLength    - the number of words to spell check;
 * dict          - an array of words to use as the dictionary;
 * dictLength    - the number of dictionary words;
 * maxDifference - the maximum difference between misspelt words and their
 *                 suggested corrections;
 * action'       - a pointer to a function that will be called for each
 *                 misspelt word.
 */
void check(char* text[], int textLength, char* dict[], int dictLength, int maxDifference, ActionFunc action)
{
    /* Construct a trie to represent the set of all the dictionary words. */
    TrieNode* trie = buildTrie(dict, dictLength);

    char* suggestion = NULL;
    int maxLen = 0;
    int t;

    for(t = 0; t < textLength; t++)
    {
        int len = strlen(text[t]);
        if(maxLen < len) {
            /* Maintain a buffer to store, temporarily, the correction for each
             * word. Increase the size of the buffer as needed. */
            maxLen = len;
            suggestion = (char*)realloc(suggestion, sizeof(char) * (maxDifference + maxLen + 1));
        }

        /* Check whether the word, or an edited version thereof, is in the trie. */
        if(!inTrie(trie, text[t], suggestion, maxDifference)) {
            /* Misspelling, with no suggestion available. */
            (*action)(text[t], NULL);
        }
        else if(strcmp(text[t], suggestion) != 0)
        {
            /* Misspelling, with suggested correction */
            if((*action)(text[t], suggestion)) {
                text[t] = (char*)realloc(text[t], (strlen(suggestion) + 1) * sizeof(char));
                strcpy(text[t], suggestion);
            }
        }
    }

	printf(" Done\nEnding check function...");
	fflush(stdout);

    free(suggestion);
    freeTrie(trie);
}
