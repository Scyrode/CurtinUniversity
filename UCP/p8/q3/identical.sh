#!/bin/bash

x=$(find -type f)

for file in $x; do

    for fileTwo in $x; do

	if [ $file != $fileTwo ]; then

	    if cmp -s $file $fileTwo; then
		echo $file and $fileTwo are identical
	    fi

	fi

    done

done
