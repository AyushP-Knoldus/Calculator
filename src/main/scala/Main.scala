package com.knoldus

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object Main extends App {
  val result = Calculator.calculate("fibonacci", Seq(12))
  Await.ready(result, 10.seconds)
  println(result)
  println(ComplexOperations.squareOfExpression(5, 6))
}