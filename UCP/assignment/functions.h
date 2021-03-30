/*************************************************
* Author: Ahmad Allahham                         *
* Filename: functions.h                          *
* Purpose: To store the function, struct and     *
*	   macro declerations used by            *
*	   functions.c                           *
* Date: 09/10/2018                               *
*************************************************/

#ifndef FUNCTIONS_H


#define FUNCTIONS_H
#define FALSE 0
#define TRUE !FALSE
#define PI 3.14159265359


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>


/*************************************************
* Author: Ahmad Allahham                         *
* structName: Status                             *
* Purpose: to store the status of the pen        *
*	   including x & y coordinates, angle,   *
*	   pattern, and forground and            *
*	   background colours                    *
* Date: 09/10/2018                               *
*************************************************/
typedef struct {

    double x;
    double y;
    char pattern;
    double angle;
    int fg;
    int bg;

} Status;

/*************************************************
* Author: Ahmad Allahham                         *
* structName: LinkedListNode                     *
* Purpose: to conveniently store the contents of *
*	   a linked list node                    *
* Date: 10/10/2018                               *
*************************************************/
typedef struct {

    char command;
    void* data;

} Node;

/*************************************************
* Author: Ahmad Allahham                         *
* structName: LinkedListNode                     *
* Purpose: to define a linkedListNode that       *
*	   contains a char that corresponds to   *
*	   the command, a void pointer to a data *
*	   and a pointer to the next             *
*	   LinkedListNode                        *
* Date: 09/10/2018                               *
*************************************************/
typedef struct LinkedListNode {

    char command;
    void* data;
    struct LinkedListNode* next;

} LinkedListNode;

/*************************************************
* Author: Ahmad Allahham                         *
* structName: LinkedList                         *
* Purpose: Keeps a reference to the head of the  *
*	   LinkedList                            *
* Date: 09/10/2018                               *
*************************************************/
typedef struct {
    int length;
    LinkedListNode* head;
    LinkedListNode* tail;
} LinkedList;


/* checks if a file is empty (returns boolean) */
int isFileEmpty(FILE* in);

/* intialises the linkedList */
void initialiseLinkedList(LinkedList* list);

/* inserts a node at the end of the linkedList */
void insertLast(char currentCommand, void* currentData, LinkedList* list);

/* checks if command is valid (returns boolean) */
int isCommandValid(char* command);

/* checks which command it is and returns a char that corresponds to that command */
char whichCommand(char* command);

/* Initialises the Status struct to its intended initial values */
void initialiseStatus(Status* intialStatus);

/* pops off the first value from the linkedList, storing the data and char in a outside node */
void popOff(LinkedList* list, Node* currentNode);

/* checks if a linkedList is empty (returns a boolean) */
int isLinkedListEmpty(LinkedList* list);

/* calculates the necessary steps to move the pen in the x direction (returns a real value) */
double calculateXMovement(double distance, double angle);

/* calculates the necessary steps to move the pen in the y direction (returns a real value) */
double calculateYMovement(double distance, double angle);

/* rounds the passed double (as the function round is not included in the math lib of c89) */
int roundNum(double x);

/* log MOVE & DRAW activity to a logfile called "graphics.log" */
void logActivity(FILE* out, char command, double x1, double y1, double x2, double y2);

#endif
