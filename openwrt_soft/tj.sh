#!/bin/bash
dir=./ble/
for i in `find $dir -name *.c`;
    do echo -n $i  "	";
    cat $i | grep -v "^$" | wc -l;
    done

