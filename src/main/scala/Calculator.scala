package com.knoldus

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Calculator {
  def calculate(operator: String, operands: Seq[Double]): Future[Seq[Double]] = operator match {
      case "+" => execute(Add, operands)
      case "-" => execute(Subtract, operands)
      case "*" => execute(Multiply, operands)
      case "/" => execute(Divide, operands)
      case "^" => execute(Power, operands)
      case "sqrt" => execute(SquareRoot, operands)
      case "!" => execute(Factorial, operands)
      case "sum" => execute(Sum, operands)
      case "gcd" => execute(Gcd, operands)
      case "odd" => execute(Odd, operands)
      case "even" => execute(Even, operands)
      case "fibonacci" => execute(Fibonacci, operands)
      case _ => Future(throw new IllegalArgumentException())
      // execute(operator,operands)
    }

  private def execute(operator: Operator, operands: Seq[Double]): Future[Seq[Double]] = {
    Future {
      operator.validateAndExecute(operands)
    }
  }
}

object OperandValidate {
  def singleOperandValidate(operands: Seq[Double]): Boolean = {
    if (operands.size == 1) true
    else false
  }

  def dualOperandValidate(operands: Seq[Double]): Boolean = {
    if (operands.size == 2) true
    else false
  }
}

object Add extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.dualOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    (operands.head + operands(1)) +: Seq()
  }
}

object Subtract extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.dualOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    (operands.head - operands(1)) +: Seq()
  }
}

object Multiply extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.dualOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    (operands.head * operands(1)) +: Seq()
  }
}

object Divide extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.dualOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    if (operands(1) == 0) throw new ArithmeticException()
    else
      (operands.head / operands(1)) +: Seq()
  }
}

object Power extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.dualOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    calculatePower(operands.head, operands(1).toInt) +: Seq()
  }

  def calculatePower(firstOperand: Double, secondOperand: Int): Double = {
    @tailrec
    def calculatePowerHelper(baseNumber: Double, power: Int, result: Double): Double = {
      power match {
        case 0 => 1.0
        case 1 => result
        case _ => calculatePowerHelper(baseNumber, power - 1, result * baseNumber)
      }
    }

    calculatePowerHelper(firstOperand, secondOperand, firstOperand)
  }
}

object SquareRoot extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.singleOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    if (operands.head < 0) throw new NumberFormatException()
    Math.sqrt(operands.head) +: Seq()
  }
}

object Factorial extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.singleOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    factorial(operands.head.toInt) +: Seq()
  }

  def factorial(number: Int): Int = {
    @tailrec
    def factorialHelper(result: Int, number: Int): Int = {
      number match {
        case 0 => result
        case _ => factorialHelper(result * number, number - 1)
      }
    }

    factorialHelper(1, number)
  }
}

object Sum extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.nonEmpty
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    operands.foldLeft(0.0)(_ + _) +: Seq()
  }
}

object Gcd extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.dualOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    val number1 = operands.head.toInt
    val number2 = operands(1).toInt
    BigInt(number1).gcd(BigInt(number2)).toDouble +: Seq()
  }
}

object Odd extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.nonEmpty
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    operands.filterNot(element => element % 2 == 0)
  }
}

object Even extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    operands.nonEmpty
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    operands.filterNot(element => element % 2 == 0)
  }
}

object Fibonacci extends Operator {
  override def validate(operands: Seq[Double]): Boolean = {
    OperandValidate.singleOperandValidate(operands)
  }

  override protected def execute(operands: Seq[Double]): Seq[Double] = {
    createFibonacciSeries(operands.head.toInt)
  }

  def createFibonacciSeries(number: Int): Seq[Double] = {
    @tailrec
    def createFibonacciSeriesHelper(number: Int, fibonacciSeries: Seq[Double], currentNumber: Double, previousNumber: Double): Seq[Double] = {
      number match {
        case 0 => fibonacciSeries
        case _ => createFibonacciSeriesHelper(number - 1, fibonacciSeries :+ previousNumber, currentNumber + previousNumber, currentNumber)
      }
    }

    createFibonacciSeriesHelper(number, Seq(), 1, 0)
  }
}
object ComplexOperations {
  def squareOfExpression(firstOperand: Double, secondOperand: Double): String = {
    val leftSide = Power.calculatePower(firstOperand + secondOperand, 2)
    val rightSide = Power.calculatePower(firstOperand, 2) + Power.calculatePower(secondOperand, 2) + (2 * firstOperand * secondOperand)
    if (leftSide == rightSide) {
      "Equal"
    } else {
      "Not Equal"
    }
  }
  def find(numbers: Seq[Double]): Future[Seq[Double]] = {
    Future {
      numbers.filter(numbers => Factorial.factorial(numbers.toInt) > Power.calculatePower(6, numbers.toInt))
    }
  }
  def findAverageAfterChainingOperations(numbers: Seq[Double]): Future[Double] = {
    val seqOfFibonacci = numbers.map(values => Fibonacci.createFibonacciSeries(values.toInt))
    val oddFibonacciValues = seqOfFibonacci.flatMap(_.filter(_ % 2 != 0))
    val oddFibonacciSum = oddFibonacciValues.sum
    val averageAfterChainingOperations = Future(oddFibonacciSum / oddFibonacciValues.size)
    averageAfterChainingOperations
  }
}