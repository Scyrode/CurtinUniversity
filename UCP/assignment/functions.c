#include "functions.h"

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: ifFileEmpty                          *
* Purpose: checks if a file is empty             *
*	   (returns boolean)                     *
* Date: 11/10/2018                               *
*************************************************/
int isFileEmpty(FILE* in) {

    int ch;
    int result;

    ch = fgetc(in);

    if (ch == EOF) {
	result = TRUE;
    } else {
	fseek(in, 0, SEEK_SET);
	result = FALSE;
    }

    return  result;

}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: intialiseLinkedList                  *
* Purpose: initialises the linkedList            *
* Date: 11/10/2018                               *
*************************************************/
void initialiseLinkedList(LinkedList* list) {

    list->head = NULL;
    list->tail = NULL;
    list->length = 0;

}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: insertLast                           *
* Purpose: inserts a new node at the end of the  *
*          linkedList                            *
* Date: 11/10/2018                               *
*************************************************/
void insertLast(char currentCommand, void* currentData, LinkedList* list) {

    /* construct the last node with the information 
     * provided */
    LinkedListNode* current = (LinkedListNode*) malloc(sizeof(LinkedListNode));
    current->command = currentCommand;
    current->next = NULL;

    switch (currentCommand) {

	case 'R': case 'M': case 'D':

	    current->data = (double*) malloc(sizeof(double));
	    *(double*) current->data = *(double*) currentData;
	    break;

	case 'F': case 'B':

	    current->data = (int*) malloc(sizeof(int));
	    *(int*) current->data = *(int*) currentData;
	    break;

	case 'P':

	    current->data = (char*) malloc(sizeof(char));
	    *(char*) current->data = *(char*) currentData;
	    break;

	default:
	    printf("invalid command on line %d\n", list->length);

    }

    if ((isLinkedListEmpty(list))) {

	list->head = current;
	list->tail = current;

    } else {

	/* make tail point to the new node */
	list->tail->next = current;
	list->tail = current;

    }

    list->length++;

}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: isCoammndValid                       *
* Purpose: checks if command is valid            *
*	   (returns boolean)                     *
* Date: 11/10/2018                               *
*************************************************/
int isCommandValid(char* command) {
    
    int result;
    
    /* inspired by: 
     * https://stackoverflow.com/questions/35181913/converting-char-to-uppercase-in-c
     * 11/10/2018
     * changing the command passed into its uppercase equivilant */
    char* temp = command;
    char commandChar;
    int i = 0;

    while (temp[i]) {
	temp[i] = toupper(temp[i]);
	i++;
    }

    commandChar = temp[0];

    switch (commandChar) {
	case 'R': case 'M': case 'D': case 'F': case 'B': case 'P':
		result = TRUE;
		break;
	default:
		result = FALSE;
    }

    return result;

}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: whichCommand                         *
* Purpose: returns a char that corresponds       *
*	   to that command                       *
* Date: 11/10/2018                               *
*************************************************/
char whichCommand(char* command) {
    char temp = command[0];
    return temp;
}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: intialiseStatus                      *
* Purpose: Initialises the Status struct to      *
*	   its intended initial values           *
* Date: 11/10/2018                               *
*************************************************/
void initialiseStatus(Status* initialStatus) {

    initialStatus->x = 0;
    initialStatus->y = 0;
    initialStatus->angle = 0.0;
    initialStatus->fg = 7;
#ifdef TURTLEGRAPHICSSIMPLE
    initialStatus->fg = 15;
#endif
    initialStatus->bg = 0;
    initialStatus->pattern = '+';

}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: popOff                               *
* Purpose: pops off the first value from the     *
*	   linkedList, storing the data and char *
*	   in an outside node                    *
* Date: 11/10/2018                               *
*************************************************/
void popOff(LinkedList* list, Node* currentNode) {

    /* create an outside node and intialise it with
     * the head's information */
    LinkedListNode* temp;
    temp = list->head;

    currentNode->command = list->head->command;
    currentNode->data = list->head->data;

    /* make head point to the next LinkedListNode
     * and free the first node */
    list->head = list->head->next;
    list->length--;
    free(temp);

}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: isLinkedListEmpty                    *
* Purpose: checks if a linkedList is empty       *
*	   (returns a boolean)                   *
* Date: 11/10/2018                               *
*************************************************/
int isLinkedListEmpty(LinkedList* list) {

    int result = FALSE;
    if (list->length == 0) {
	result = TRUE;
    }

    return result;
}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: calculateXMovement                   *
* Purpose: calculates the necessary steps to move*
*	   the pen in the x direction            *
*	   (returns a rounded int)               *
* Date: 11/10/2018                               *
*************************************************/
double calculateXMovement(double distance, double angle) {
    double result = distance * cos((angle * PI) / 180);
    return result;
}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: calculateYMovement                   *
* Purpose: calculates the necessary steps to move*
*	   the pen in the y direction            *
*	   (returns a rounded int)               *
* Date: 11/10/2018                               *
*************************************************/
double calculateYMovement(double distance, double angle) {
    double result = distance * sin((angle * PI) / 180);
    return result;
}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: roundNum                             *
* Purpose: rounds the passed double (as the      *
*	   function "round" is not included in   *
*	   the math lib of c89                   *
* Date: 11/10/2018                               *
*************************************************/
int roundNum(double x) {

    /* inspired by dan04's answer:
     * https://stackoverflow.com/questions/4572556/concise-way-to-implement-round-in-c */
    
    int result;
    if (x < 0.0)
	result = (int)(x - 0.5);
    else
	result = (int)(x + 0.5);
    return result;

}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Function: logActivity                          *
* Purpose: log MOVE & DRAW activity to a         *
*	   logfile called "graphics.log"         *
* Date: 11/10/2018                               *
*************************************************/
void logActivity(FILE* out, char command, double x1, double y1, double x2, double y2) {

    if (command == 'M') {
#ifdef TURTLEGRAPHICSDEBUG
	fprintf(stderr, "MOVE (%7.3f, %7.3f)-(%7.3f, %7.3f)\n", x1, y1, x2, y2);
#endif
	fprintf(out, "MOVE (%7.3f, %7.3f)-(%7.3f, %7.3f)\n", x1, y1, x2, y2);
    } else if (command == 'D') {
#ifdef TURTLEGRAPHICSDEBUG
	fprintf(stderr, "DRAW (%7.3f, %7.3f)-(%7.3f, %7.3f)\n", x1, y1, x2, y2);
#endif
	fprintf(out, "DRAW (%7.3f, %7.3f)-(%7.3f, %7.3f)\n", x1, y1, x2, y2);
    }

}

