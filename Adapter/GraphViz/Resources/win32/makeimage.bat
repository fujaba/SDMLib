@echo off
REM echo processing image %1 
REM cd doc
REM %2\tools\Graphviz\bin\dot.exe %1.dot -Tsvg -o %1.svg
dot.exe %1.dot -Tsvg -o %1.svg
