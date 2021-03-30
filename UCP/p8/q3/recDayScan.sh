#!/bin/bash

for file in $(find -L -type f); do

    if [ $(file) *$(date +%A)* ]; then
	echo $(file)
    fi

done
