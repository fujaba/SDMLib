#!/bin/bash
CMD=""

for ARG in "$@"
do
CMD+="$ARG "
done

$CMD
