echo processing image %1 
..\SDMLib\tools\Graphviz\bin\dot.exe doc\%1.dot -Tsvg -o doc\%1.svg
