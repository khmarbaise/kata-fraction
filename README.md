<!---
 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
# Code Kata for Fractions

[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)][license]
[![Build Status](https://cloud.drone.io/api/badges/khmarbaise/kata-fraction/status.svg)](https://cloud.drone.io/khmarbaise/kata-fraction)
[![Issues](https://img.shields.io/github/issues/khmarbaise/kata-fraction)](https://github.com/khmarbaise/kata-fraction/issues)
[![Issues Closed](https://img.shields.io/github/issues-closed/khmarbaise/kata-fraction)](https://github.com/khmarbaise/kata-fraction/issues?q=is%3Aissue+is%3Aclosed)
[![codecov](https://codecov.io/gh/khmarbaise/kata-fraction/branch/master/graph/badge.svg?token=RULU3ULC3O)](https://codecov.io/gh/khmarbaise/kata-fraction)

The idea of this code kata is to show separation of concerns in particular and other aspects
like how to use code coverage tools like JaCoCo as well as Mutation Testing to support the development
process.

This Kata defines the domain of working in a very simple domain which is basic school mathematics as
given with fractions.

# Requirement Definition I 

## Overview
We would like to calculate fraction operations which are defined in a file line by line.

## File Format

The file can contain comment lines which are identified
by `#` at the beginning of the line and have to be ignored.

## Fraction Format
A fraction starts by `(` and limited by `)`. The numerator separated by `;` from the denominator.
The numerator as well as the denominator are integer values which can be prefixed by a `-` to define
a negative fraction.

* `(9;12)` This would define the fraction `9/12`.
* `(-9;12)` This would define the fraction `-9/12`.
* `(9;-12)` This would define the fraction `9/-12`.

## Definition of the valid operations

We define the following operations as valid on fractions:

 * `+` addition
 * `-` subtraction
 * `*` multiplication
 * `/` divide
 * `^` power (need to reconsider.)

We need to be able to add, subtract, divide, multiply or create the power of a fraction.

## Definition of power

* The following gives an example how to define the operation `^`:
  `(1;3)^5`. The exponent can only be an integer. So this also valid: `(1;3)^-5`.
* The following gives an example how to add two fractions: `(3;2)+(4;4)`
* The following gives an example how to subtract two fractions: `(3;2)-(4;4)`
* The following gives an example how to multiply two fractions: `(3;2)*(4;4)`
* The following gives an example how to divide two fractions: `(3;2)/(4;4)`

## Example Lines
The following is an example of a line of operations on fractions:
```
(3/2)+(4/4)/(2/3)
``` 

and it should be handled to calculate the results of those
fraction operations.


# Requirement Definition II

The change vs I.

Introduce parenthesis like this:
```
( (3/2+(4/4) ) / (2/3)
``` 

# Requirement Definition III

The change vs I

Integer => Long => BigInteger ?

# Build

* JDK 17+
* Apache Maven 3.8.6

Code coverage via:
```bash
mvn clean verify org.jacoco:jacoco-maven-plugin:report
```
Create Mutation coverage via:
```bash
mvn clean verify org.pitest:pitest-maven:mutationCoverage
```


[license]: https://www.apache.org/licenses/LICENSE-2.0
