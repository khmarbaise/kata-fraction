# Code Kata for Fractions

The idea of this code kata is to show separation of concerns in particular and other aspects
like how to use code coverage tools like JaCoCo as well as Mutation Testing to support the development
process.

This Kata is doing it's requirements in a very simple domain area which is basic school mathematics as
given with fractions.

# Requirement Definition I 

## Overview
We would like to calculate fraction operations which are defined in a file line by line.

## File Format

The file can contain comment lines which are identified
by `#` at the beginning of the line and have to be ignored.

## Fraction Format
A fraction starts by `{` and limited by `}`. The numerator separated by `/` from the denominator.
The numerator as well as the denominator are integer values which can be prefixed by a `-` to define
a negative fraction.

* `{9/12}` This would define the fraction `9/12`.
* `{-9/12}` This would define the fraction `-9/12`.
* `{9/-12}` This would define the fraction `-9/12`.



## Definition of the valid operations

We define the following operations as valid on fractions:

 * `+` addition
 * `-` subtract
 * `*` multiplication
 * `/` divide
 * `^` power

We need to be able to add, subtract, divide, multiply or create the power of a fraction.
 



 and it should be handled to calculate the results of those
fraction operations.


# Requirement Definition II

The change vs I.

Integer => Long => BigInteger ?
 

Create Mutation coverage via:
```bash
mvn clean verify org.pitest:pitest-maven:mutationCoverage
```

Another Test.