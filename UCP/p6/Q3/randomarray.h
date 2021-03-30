/**
 * UCP 120/500 - Helper code for practical worksheet 5.
 */

#include "plot.h"

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
void randomArray(double **data, int rows, int cols, int smoothing);
