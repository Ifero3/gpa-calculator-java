# GPA Calculator (Java)

## What it does
A console tool that calculates both **Unweighted GPA** and **Weighted GPA** from user-entered courses.

Features:
- supports +/- grades (A-, B+, etc.)
- supports course levels: Regular / Honors / AP
- credit-weighted GPA calculation
- summary table output for all courses

## Modeling / How it works
I modeled GPA calculation by:
1) converting letter grades into numeric points (4.0 scale with +/-),
2) applying level-based bonus rules (Honors +0.5, AP +1.0, capped at 5.0),
3) computing a credit-weighted average across all courses.

## Sample Output
Course               Grade  Level  Credits  UnwPts  WtdPts
AP Calc AB           A      AP     5.0      4.0     5.0
English 4            B+     R      5.0      3.3     3.3
Physics              A-     H      5.0      3.7     4.2
Video Production     A      R      5.0      4.0     4.0

Unweighted GPA: 3.750
Weighted GPA:   4.125

## How to run
Compile:
`javac GPACalculator.java`

Run:
`java GPACalculator`

## Why I built this
I built this project to better understand how GPA rules used in real schools
can be modeled as clear, logical steps in code.

## What I learned
- input validation, loops, arrays
- modeling real-world school rules into code
- readable console UI and reporting
