/*************************************************
* Author: Ahmad Allahham                         *
* Filename: turtleGraphics.h                     *
* Purpose: to process commands meant to generate *
*	   ASCII diagrams and then execute those *
*	   commands                              *
* Date: 13/10/2018                               *
*************************************************/

#include "turtleGraphics.h"

int main(int argc, char* argv[]) {

    /* check if the number of arguments is correct */
    if (argc != 2) {
	printf("ERROR: incorrect number of parameters\n");
    } else {


	/* open both the input file and the logfile */
	FILE* in = fopen(argv[1], "r");
	FILE* out = fopen("graphics.log", "a");

	/* create a new partition in the logfile */
	fprintf(out, "---\n");

	if (in == NULL) {
	    perror("Error reading input file");
	} else {

	    Node* currentNode = (Node*) malloc(sizeof(Node));
	    Status* currentStatus = (Status*) malloc(sizeof(Status));
	    LinkedList* list = (LinkedList*) malloc(sizeof(LinkedList));

	    PlotFunc ptr = &drawFunction;

	    char readLine[100];
	    char* delim = " ";
	    char command[20];
	    char value[20];
	    char* testString = NULL;
	    char commandChar;
	    double* realValue = (double*) malloc(sizeof(double));
	    int* integerValue = (int*) malloc(sizeof(int));
	    char* patternChar = value;
	    double x2, y2, difference;

	    /* intialise Status and list with the default values */
	    initialiseStatus(currentStatus);
	    initialiseLinkedList(list);

	    /* check if the file is empty */
	    if (!(isFileEmpty(in))) {

		/* for every line in the input file, read that
		 * line into a string then tokenise it to 
		 * command, value and a testString (to ensure
		 * the correct number of parameters is present) */
		while (fgets(readLine, 100, in) != NULL) {

		    strncpy(command, strtok(readLine, delim), 20);
		    strncpy(value, strtok(NULL, delim), 20);
		    testString = strtok(NULL, delim);

		    if (testString == NULL) {

			/* for each command, check if its valid,
			 * then store its first character into
			 * commandChar. Depending on commandChar
			 * execute the appropriate checking 
			 * mechanisims */
			if (isCommandValid(command)) {

			    commandChar = whichCommand(command);

			    switch (commandChar) {
				
				case 'M': case 'D':

				    *realValue = atof(value);

				    if (realValue) {
					insertLast(commandChar, realValue, list);
				    }

				    break;

				case 'R':

				    *realValue = atof(value);

				    if (realValue) {
					insertLast(commandChar, realValue, list);
				    }

				    break;


				case 'F':

#ifndef TURTLEGRAPHICSSIMPLE
				    *integerValue = atoi(value);

				    if ((*integerValue >= 0) && (*integerValue <= 15)) {
					insertLast(commandChar, integerValue, list);
				    }

#endif

				    break;

				case 'B':

#ifndef TURTLEGRAPHICSSIMPLE
				    *integerValue = atoi(value);

				    if ((*integerValue >= 0) && (*integerValue <= 7)) {
					insertLast(commandChar, integerValue, list);
				    }

#endif

				    break;

				case 'P':

				    insertLast(commandChar, patternChar, list);

				    break;

				default:
				    /* should never be reached hypothetically speaking */
				    printf("invalid command on line %d\n", list->length);

			    }

			}

		    }
		    
		}

		/* point patternChar to the location of the currentStatus's pattern */
		patternChar = &currentStatus->pattern;

		/* clear the screen */
		clearScreen();

		/* for every command in the linkedList, pop it off 
		 * (obtain its information and free that node).
		 * then execute the instruction depending on the
		 * commandChar */
		while (!(isLinkedListEmpty(list))) {

		    popOff(list, currentNode);

		    commandChar = currentNode->command;

		    switch (commandChar) {

			case 'R':

			    currentStatus->angle += *(double*) currentNode->data;
			    break;

			case 'M':

			    /* for x and y coordinates, calculate the difference 
			     * using the distance and the current angle then add 
			     * it to the current x and y coordinates (found in
			     * struct current status) */
			    difference = calculateXMovement(*(double*) currentNode->data, currentStatus->angle);
			    x2 = currentStatus->x + difference;

			    difference = -1 * calculateYMovement(*(double*) currentNode->data, currentStatus->angle);
			    y2 = currentStatus->y + difference;

			    line(roundNum(currentStatus->x), roundNum(currentStatus->y), roundNum(x2), roundNum(y2), ptr, NULL);

			    /* log the movement */
			    logActivity(out, commandChar, currentStatus->x, currentStatus->y, x2, y2);

			    /* update the current x & y coordinates */
			    currentStatus->x = x2;
			    currentStatus->y = y2;

			    break;

			case 'D':

			    /* for x and y coordinates, calculate the difference 
			     * using the distance and the current angle then add 
			     * it to the current x and y coordinates (found in
			     * struct current status) */
			    difference = calculateXMovement(*(double*) currentNode->data, currentStatus->angle);
			    x2 = currentStatus->x + difference;

			    difference = -1 * calculateYMovement(*(double*) currentNode->data, currentStatus->angle);
			    y2 = currentStatus->y + difference;

			    /* to ensure proper execution, must draw 
			       to (x2 - 1) then move one across horizontally */
			    line(roundNum(currentStatus->x), roundNum(currentStatus->y), roundNum(x2) - 1, roundNum(y2), ptr, patternChar);

			    line(roundNum(currentStatus->x), roundNum(currentStatus->y), 1, 0, ptr, NULL);

			    /* log the movement */
			    logActivity(out, commandChar, currentStatus->x, currentStatus->y, x2, y2);

			    /* update the current x & y coordinates */
			    currentStatus->x = x2;
			    currentStatus->y = y2;

			    break;

			case 'F':

			    currentStatus->fg = *(int*) currentNode->data;

			    setFgColour(currentStatus->fg);

			    break;

			case 'B':

			    currentStatus->bg = *(int*) currentNode->data;

			    setBgColour(currentStatus->bg);

			    break;

			case 'P':

			    currentStatus->pattern = *(char*) currentNode->data;

			    break;

			default:
			    /* should never be reached hypothetically speaking */
			    printf("INCORRECT ALGORITHIM!!\n");

		    }

		    /* after the command is executed, free the data obtained from
		     * the freed node */
		    free(currentNode->data);

		}

	    }
	    	    
	    /* free all malloc'ed data */
	    free(integerValue);
	    free(realValue);
	    free(currentStatus);
	    free(list);
	    free(currentNode);

	    /* check if any errors occured during reading/logging */
	    if (ferror(in) || ferror(out)) {
		perror("Error reading/writing to file");
	    }

	    fclose(in);
	    fclose(out);

	    penDown();

	}

    }

    return 0;

}

/*************************************************
* Author: Ahmad Allahham                         *
* Filename: turtleGraphics.c                     *
* Function: drawFunction                         *
* Purpose: draws the character passed to it      *
* Date: 12/10/2018                               *
*************************************************/
void drawFunction(void* patternChar) {
    if (patternChar != NULL)
	printf("%c", *(char*) patternChar);
}
