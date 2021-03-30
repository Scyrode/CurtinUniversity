#include "Functions.h"

int sum(int array[], int arrayLength)
{

    int result = 0;
    int i;

    for (i = 0; i < arrayLength; i++)
    {
	result += array[i];
    }

    return result;

}

int max(int array[], int arrayLength)
{

    int result = array[0];
    int i;
    for (i = 0; i < arrayLength; i++)
    {
	
	if (array[i] > result)
	{
	    result = array[i];
	}

    }

    return result;

}

void reverse(int array[], int arrayLength)
{

    int* temp = (int*) malloc(arrayLength * sizeof(int));
    int i;

    for (i = 0; i < arrayLength; i++)
    {
	temp[i] = array[arrayLength - i - 1];
    }

    for (i = 0; i < arrayLength; i++)
    {
	array[i] = temp[i];
    }

    free(temp);

}

void StringtoInt(char* charArray[], int* intArray, int arrayLength)
{

    char* temp = charArray[0];
    int i;

    for (i = 0; i < arrayLength; i++)
    {
	temp = charArray[i];
	intArray[i] = atoi(temp);
    }

}

void arrayOutput(int array[], int arrayLength)
{
    int i;

    printf("{%d ", array[0]);
    for (i = 1; i < arrayLength; i++)
    {
	printf(", %d", array[i]);
    }
    printf("}\n");

}

void toUpperCase(char* choice)
{

    char temp;
    int i = 0;
    temp = (*choice);
    int difference;

    while (temp != '\0')
    {
	difference = temp - 'A';
	if (difference > 27)
	{
	    temp = temp - ('a' - 'A');
	}

	*(choice + i) = temp;
	i++;
	temp = *(choice + i);
    }

}
