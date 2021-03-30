/**
 * UCP 120/500 - Helper code for practical worksheet 5.
 * (You do NOT need to modify this file to complete the prac.)
 */


/* This file requires UNIX functions that are not part of the C language itself
 * (mkstemp and fdopen). The following switches these on in stdlib.h. */
#define _XOPEN_SOURCE 500

#include <stdio.h>
#include <stdlib.h>
#include "plot.h"


/**
 * Given open FILE pointers, writes a malloc'd 2D array of doubles to file, 
 * and invokes gnuplot to display the data.
 * 
 * Parameters
 * data           - a 2D array (a pointer to an array of pointers to arrays).
 * rows           - the number of rows in the data.
 * cols           - the number of columns in the data.
 * dataFilename   - the name of the file to which plot data will be written.
 * scriptFilename - the name of the file to which a gnuplot script will be 
 *                  written, to control gnuplot.
 * dataFile       - a file pointer to the data file.
 * scriptFile     - a file pointer to the script file.
 */
static int plotActual(double **data, int rows, int cols,
                      char* dataFilename, char* scriptFilename, 
                      FILE* dataFile, FILE* scriptFile)
{
    int i, j;
    char command[250];

    /* Write the 2D data to file. */
    for(i = 0; i < rows; i++)
    {
        for(j = 0; j < cols; j++)
        {
            fprintf(dataFile, "%f ", data[i][j]);
        }
        fputc('\n', dataFile);
    }

    /* Write the gnuplot script file. */
    fprintf(scriptFile, 
        "set pm3d\n"
        "splot \"%s\" matrix with pm3d notitle\n"
        "pause -1 \"Press Enter\"\n", 
        dataFilename);

    /* Ensure the data is actually written to disk prior to running gnuplot. */
    fflush(dataFile);
    fflush(scriptFile);

    /* Run gnuplot itself. */
    sprintf(command, "gnuplot %s </dev/tty", scriptFilename);
    return system(command);
}

/**
 * Plots a malloc'd 2D array using gnuplot. This function opens temporary 
 * files, performs error checking, and invokes plotActual().
 * 
 * (This function uses mkstemp() and fdopen(), which are standard UNIX 
 * functions but not standard C functions. Thus, this code may not compile/work
 * on non-UNIX systems.)
 */
void plot(double **data, int rows, int cols)
{
    char dataFilename[] = "/tmp/ucpplotdataXXXXXX";
    char scriptFilename[] = "/tmp/ucpplotscriptXXXXXX";
    int dataFd, scriptFd;
    FILE* dataFile;
    FILE* scriptFile;
    int ret;

    /* Open temporary files, based on template filenames. */
    dataFd = mkstemp(dataFilename);
    scriptFd = mkstemp(scriptFilename);
    if(dataFd == -1 || scriptFd == -1)
    {
        perror("Plotting failed - unable to create temporary files");
    }
    else
    {
        /* Convert the OS-level file descriptors to C-level FILE pointers. */
        dataFile = fdopen(dataFd, "w");
        scriptFile = fdopen(scriptFd, "w");
        if(dataFile == NULL || scriptFile == NULL)
        {
            perror("Plotting failed - unable to open temporary file stream");
        }
        else
        {
            /* Perform the actual plotting. */
            ret = plotActual(data, rows, cols, 
                             dataFilename, scriptFilename,
                             dataFile, scriptFile);
            if(ret % 256 != 0)
            {
                perror("Plotting failed - unable to run gnuplot");
            }
            fclose(dataFile);
            fclose(scriptFile);
        }
    }
}

