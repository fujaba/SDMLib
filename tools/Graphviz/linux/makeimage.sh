#!/bin/sh
echo processing image $1
dot doc/$1.dot -Tsvg -o doc/$1.svg