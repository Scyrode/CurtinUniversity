/**
 * UCP 120/500 - Helper code for practical worksheet 5.
 * (You do NOT need to modify this file to complete the prac.)
 */

#include <stdlib.h>
#include <time.h>
#include "randomarray.h"

#define MIN -1.0
#define MAX 1.0

/**
 * Fills a malloc'd 2D array of doubles with random (but smoothed) values.
 *  
 * Parameters:
 * data      - a 2D array (a pointer to an array of pointers to arrays).
 * rows      - the number of rows in the data.
 * cols      - the number of columns in the data.
 * smoothing - a smoothing factor; higher values indicate smoother data, while 
 *             zero indicates no smoothing.
 */
void randomArray(double **data, int rows, int cols, int smoothing)
{
    int i;
    int j;
    double **rawdata;

    rawdata = (double**)malloc(rows * sizeof(double*));
    for(i = 0; i < rows; i++)
    {
        rawdata[i] = (double*)malloc(cols * sizeof(double));
    }

    /* Initialise the array to randomly chosen values */
    srand(time(NULL));
    for(i = 0; i < rows; i++)
    {
        for(j = 0; j < cols; j++)
        {
            rawdata[i][j] = ((double)rand() / RAND_MAX) * (MAX - MIN) + MIN;
        }
    }

    /* Perform smoothing. Each element is replaced by the mean of itself and
     * its surrounding elements. The smoothing factor determines the size of 
     * the surrounding area to average.
     */
    for(i = 0; i < rows; i++)
    {
        for(j = 0; j < cols; j++)
        {
            int u, v, count = 0;
            double sum = 0.0;
            
            /* Iterate over the surrounding area, cutting it off at the array 
             * boundaries. */
            for(u = i - smoothing; u <= i + smoothing; u++) 
            {
                if(0 <= u && u < rows) 
                {
                    for(v = j - smoothing; v <= j + smoothing; v++) 
                    {
                        if(0 <= v && v < cols)
                        {
                            sum += rawdata[u][v];
                            count++;
                        }
                    }
                }
            }

            data[i][j] = sum / (double)count;
        }
    }
    
    for(i = 0; i < rows; i++)
    {
        free(rawdata[i]);
    }
    free(rawdata);
}
