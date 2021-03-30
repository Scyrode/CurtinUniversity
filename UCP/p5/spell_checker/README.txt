IMPORTANT NOTE!
The Makefile: this is the file used to compile the program.
Note that there are two ways to compile!
1) The automatic 'make', which includes all extra features for punctuation
   and format. The executable is called 'check'.

*****************************************************************************
How it works:
1) Read the settings file, assigning its values to strings.
2) Read the dictionary file into a dynamically allocated array.
3) Read the user input file to find where new lines occur, and save this
   information in an array.
4) Read the input file into a dynamically allocated array.
5) Choose a call back function based on the auto correct value in the
    settings file.
6) Call the check function, writing the corrected (or not corrected) words
    back into the same array as the original input was stored.
7) Write the array of corrected (or not corrected) words back to the
    original input file, inserting new lines as recorded by newLineArray.
8) Free and clean up all used memory.

*****************************************************************************
