@echo off
REM echo processing image %1 
cd doc
..\..\SDMLib\tools\Graphviz\bin\dot.exe %1.dot -Tsvg -o %1.svg
