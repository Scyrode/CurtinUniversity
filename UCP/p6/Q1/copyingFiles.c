#include "copyingFiles.h"

int main(int argc, char* argv[]) {

    if (argc != 3) {
	printf("ERROR: INCORRECT NUMBER OF PARAMETERS\n");
    } else {

	char* readFilename = argv[1];
	char* writeFilename = argv[2];
	char errorM[100] = "Error opening file ";
	    

	FILE* inFile = fopen(readFilename, "r");
	if (inFile == NULL) {

	    strncat(errorM, readFilename, strlen(readFilename) + 1);
	    perror(errorM);
	    strcpy(errorM, "Error opening file ");

	} else {

	    FILE* outFile = fopen(writeFilename, "w");
	    int ch;

	    do {
		ch = fgetc(inFile);
		if (ch != EOF) {
		    fputc((char) ch, outFile);
		}
	    } while (ch != EOF);

	    if (ferror(inFile)) {
		strncat(errorM, readFilename, strlen(readFilename) + 1);
		perror(errorM);

	    }

	    if (ferror(outFile)) {
		strncat(errorM, writeFilename, strlen(readFilename) + 1);
		perror(errorM);
	    }

	    fclose(inFile);
	    fclose(outFile);

	}


    }

    return 0;

}
