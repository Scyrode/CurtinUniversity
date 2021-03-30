#include "generate.h"

int main(int argc, char* argv[]) {

    if (argc != 4) {
	printf("ERROR: incorrect number of paramerters (must be 3)\n");
    } else {

	FILE* out = fopen(argv[1], "w");
	if (out == NULL) {
	    perror("Error opening output file");
	} else {

	    int rows = atoi(argv[2]);
	    int columns = atoi(argv[3]);
	    char parameters[10];
	    char line[LINE_LENGTH];
	    char number[30];
	    double** array = (double**) malloc(sizeof(double*) * rows);
	    int i, j;

	    for (i = 0; i < rows; i++) {
		array[i] = (double*) malloc(sizeof(double) * columns);
	    }

	    randomArray(array, rows, columns, 2);

	    sprintf(parameters, "%d %d", rows, columns);
	    strcat(parameters, "\n");
	    fputs(parameters, out);

	    for (j = 0; j < rows; j++) {

		strcpy(line, "");

		for (i = 0; i < columns; i++) {

		    sprintf(number, "%f", array[j][i]);
		    strcat(number, " ");
		    strcat(line, number);

		}

		strcat(line, "\n");

		fputs(line, out);

	    }

	    if (ferror(out)) {
		perror("error writing output file");
	    }

	}

    }

    return 0;
}
