#include "logreader.h"

int main(int argc, char* argv[]) {

    if (argc != 2) {
	printf("ERROR: INCORRECT NUMBER OF PARAMETERS (MUST BE 2)\n");
    } else {

	FILE* in = fopen(argv[1], "r");
	if (in == NULL) {
	    perror("error opening input file");
	} else {

	    char line[INPUT_SIZE];
	    char* substring;
	    char* fail = "fail";
	    int endOfFile = FALSE;

	    char month[3];
	    int day;
	    char process[50];
	    char message[100];
	    int hours;
	    int mins;
	    int secs;
	    int totalSecs;

	    do {

		if ((fgets(line, INPUT_SIZE, in)) == NULL) {

		    if (ferror(in)) {
			perror("Error reading input file");
		    } else {
			endOfFile = TRUE;
		    }

		} else {

		    substring = strstr(line, fail);
		    if (substring != NULL) {

			sscanf(line, "%s %d %d:%d:%d %[^:] %[^\n]", month, &day, &hours, &mins, &secs, process, message);
			totalSecs = (hours * 60 * 60) + (mins * 60) + (secs);
			printf("total time: %d, Error Message: %s\n", totalSecs, message);

		    }

		}


	    } while (!endOfFile);


	}


    }

    return 0;

}
