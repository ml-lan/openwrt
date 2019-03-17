#!/bin/bash
dir=./ble/
for i in `find $dir -name *.jsp`;
    do echo -n $i  "	";
    cat $i | grep -v "^$" | wc -l;
    done

