#include "ArrayFunction.h"

int main(int argc, char* argv[])
{

    if (argc < 3)
    {
	printf("ERROR: incorrect format\n");
    } else if (argc > 21) 
    {
	printf("ERROR: too many arguments\n");
    } else
    {

	int arrayLength = argc - 2;
	char** stringArray = (char**) malloc(arrayLength * sizeof(char*));
	int result;
	int i;

	for (i = 2; i < argc; i++)
	{
	    stringArray[i-2] = argv[i];
	}

	int* intArray = (int*) malloc(arrayLength * sizeof(int));

	StringtoInt(stringArray, intArray, arrayLength);

	char* choice = argv[1];

	toUpperCase(choice);

	if (strcmp(choice, "SUM") == 0)
	{
	    result = sum(intArray, arrayLength);
	    printf("The sum of the array is = %d\n", result);
	} else if (strcmp(choice, "MAX") == 0)
	{
	    result = max(intArray, arrayLength);
	    printf("The max of the array is = %d\n", result);
	} else if (strcmp(choice, "REVERSE") == 0)
	{
	    reverse(intArray, arrayLength);
	    arrayOutput(intArray, arrayLength);
	} else
	{
	    printf("ERROR: incorrect function argument\n");
	}

	free(stringArray);
	free(intArray);
    }

    return 0;

}
