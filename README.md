# Problem Statement

## Scala Calculator
This project implements a calculator that can perform various operations on operands. The following operators are supported:

    +: add two operands
    -: subtract second operand from the first operand
    *: multiply two operands
    /: divide the first operand by the second operand
    ^: raise the first operand to the power of the second operand
    sqrt: find the square root of the operand
    !: find the factorial of the number
    sum: add all the operands
    gcd: find the greatest common divisor of two operands
    odd: find all the odd operands
    even: find all the even operands
    fibonacci: find the fibonacci series till operand. Let's say if operand is 5 then the result should have first 5 fibonacci numbers
## Additional Methods
The project also includes the following additional methods:
   SquareOfExpression  
    This method checks if (firstOperand + secondOperand) ^ 2 == (firstOperand ^ 2)   
    + (secondOperand ^ 2) + (2 * firstOperand * secondOperand).  
     If true, it returns "Equal", else it returns "Not Equal".  
  
   find  
    This method finds the numbers from the provided sequence whose factorial is   
    greater than 6 ^ number.  

   findAverageAfterChainingOperations  
    This method finds the fibonacci of each number in the input sequence,   
    finds all the odd numbers from the resulting sequence, and returns their sum.   
    After performing this operation on each number in the input sequence, it finds   
    the average of the results.  
  
# Prerequisites
    To run this project, you need to have Scala and SBT installed on your machine.

# How to run the project
To run the project, follow the below steps:

### Clone the project from the repository.

    https://github.com/Satvik-knolx/SessionFive.git
### Navigate to the project directory.

    cd Calculator
### Run the following command to build the project.

    sbt clean compile
### Run the following command to run the project.

    sbt run
