#include "display.h"

int main(int argc, char* argv[]) {

    if (argc != 2) {
	printf("ERROR: incorrect number of paramters (must be 2)\n");
    } else {

	FILE* in = fopen(argv[1], "r");
	if (in == NULL) {
	    perror("Error opening input file");
	} else {

	    char line[LINE_LENGTH];
	    char* delim = " ";
	    int rows, columns;
	    int i, j;

	    fgets(line, LINE_LENGTH, in);
	    sscanf(line, "%d %d", &rows, &columns);

	    double** array = (double**) malloc(sizeof(double*) * rows);

	    for (j = 0; j < rows; j++) {

		fgets(line, LINE_LENGTH, in);
		array[j] = (double*) malloc(sizeof(double) * columns);

		array[j][0] = atof(strtok(line, delim));

		for (i = 1; i < columns; i++) {

		    array[j][i] = atof(strtok(NULL, delim));

		}

	    }

	    if (ferror(in)) {
		perror("error reading input file");
	    } else {
		plot(array, rows, columns);
	    }

	}

    }

    return 0;

}


