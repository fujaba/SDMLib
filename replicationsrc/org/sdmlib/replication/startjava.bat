@echo off

SET cmd=""

FOR %%A IN (%*) DO (
SET cmd=%cmd% %%A;
)

%cmd%