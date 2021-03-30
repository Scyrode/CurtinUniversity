#!/bin/bash

echo we have $# parameters

for file in $*; do

    for fileTwo in $*; do

	if [ $file != $fileTwo  ]; then

	    if cmp -s $file $fileTwo; then
		echo $file and $fileTwo are identical
	    fi

	fi

    done

done
