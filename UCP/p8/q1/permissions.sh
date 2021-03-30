#!/bin/bash

if [ $# -ne 1 ]; then
    echo command-line parameters must be 2 \(including the name of the file\)
elif [ -e $1 ]; then

    if [ -w $1  ]; then
	echo you have permission to write to $1
    fi

    if [ -r $1  ]; then
	echo you have permission to read from $1
    fi

    if [ -x $1  ]; then
	echo you have permission to execute $1
    fi

else
    echo $1 does not exist
fi
