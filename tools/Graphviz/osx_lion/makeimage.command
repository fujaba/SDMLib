#!/bin/bash
echo processing image $1
/usr/local/bin/dot doc/$1.dot -Tsvg -o doc/$1.svg
