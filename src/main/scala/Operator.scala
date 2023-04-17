package com.knoldus
class CalculatorException extends Exception
trait Operator extends Validator
{
  //validate and execute - implement this function in trait
  //this function will validate the operands and execute it.
  //throw CalculatorException when validation fails.
  def validateAndExecute(operands : Seq[Double]) : Seq[Double]={
    if(validate(operands)) execute(operands)
    else throw new CalculatorException
  }


  protected def execute(operands : Seq[Double]) : Seq[Double]
}



