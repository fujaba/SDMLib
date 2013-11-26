@echo off
REM echo processing image %1 
cd doc
%2\tools\Graphviz\bin\dot.exe %1.dot -Tsvg -o %1.svg
