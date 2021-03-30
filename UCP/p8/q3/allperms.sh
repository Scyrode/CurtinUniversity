#!/bin/bash

for file in $(find -type f); do

    if [ -w $file  ]; then
	echo you have permission to write to $file
    fi

    if [ -r $file  ]; then
	echo you have permission to read from $file
    fi

    if [ -x $file  ]; then
	echo you have permission to execute $file
    fi

    if ! [ -e $file ]; then
	echo $file does not exist
    fi
    
done
