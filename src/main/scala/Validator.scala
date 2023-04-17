package com.knoldus

trait Validator
{
  //validate the operands for the specific operator
  def validate(operands : Seq[Double]) : Boolean
}

