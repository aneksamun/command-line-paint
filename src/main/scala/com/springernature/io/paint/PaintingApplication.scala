package com.springernature.io.paint

import com.springernature.io.paint.console.Command
import com.springernature.io.paint.console.Command.{CreateCanvas, Quit}
import com.springernature.io.paint.domain.common.Error.CanvasNotPresent
import com.springernature.io.paint.domain.model._

import scala.io.StdIn.readLine
import scala.util.control.Breaks.{break, breakable}

object PaintingApplication extends App {
  println("Welcome to command line paint!")
  private var canvas: Option[Canvas] = None

  breakable {
    while (true) {
      val line = readLine("\nEnter command: ")
      Command.of(line) match {
        case Some((Quit, _)) => break
        case Some((CreateCanvas, result)) => proceed(result, assignCanvas())
        case Some((_, result)) => proceed(result, addShape())
        case None => printUsage()
      }
    }
  }

  def printUsage() {
    println("\nUsage: command [args1, arg2, ... argN]\n")
    Command.values.foreach { println }
  }

  private def assignCanvas() = {
    (other: Any) => {
      canvas = Some(other.asInstanceOf[Canvas])
      println(canvas.get)
    }
  }

  private def addShape() = {
    (shape: Any) => {
      println(canvas.map(_.add(shape.asInstanceOf[Shape]).fold(error => error, _.toString))
        .getOrElse(CanvasNotPresent.toString))
    }
  }

  private def proceed(value: Either[String, _], action: Any => Unit) {
    value.fold(error => println(error), unit => action(unit))
  }
}
