#!/bin/bash

echo enter the name of the first file
read filenameOne
echo enter the name of the second file
read filenameTwo

if [ $filenameOne -nt $filenameTwo ]; then
    echo $filenameOne is newer than $filenameTwo
elif [ $filenameTwo -nt $filenameOne ]; then
    echo $filenameTwo is newer than $filenameOne
fi

