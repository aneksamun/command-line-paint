package co.uk.redpixel.paint

import co.uk.redpixel.paint.console.Command
import co.uk.redpixel.paint.domain.common.Error._
import co.uk.redpixel.paint.domain.model.{Canvas, Shape}
import co.uk.redpixel.paint.syntax._

import scala.io.StdIn.readLine
import scala.util.control.Breaks.{break, breakable}

object PaintingApplication extends App {

  private var canvas: Option[Canvas] = None

  private lazy val usage = (
    Command.CreateCanvas.description ::
      Command.DrawLine.description ::
      Command.DrawRectangle.description ::
      Command.FillBucket.description ::
      Command.Quit.description ::
      Nil
    ).mkString(
    start = "\nUsage: command [args1, arg2, ... argN]\n",
    sep = "\n",
    end = ""
  )

  println("Welcome to command line paint!")

  breakable {
    while (true) {
      val input = readLine("\nEnter command: ")
      Command.from(input).collect {
        case createCanvas: Command.CreateCanvas =>
          createCanvas
            .execute
            .applying(toGlobalContext)
        case drawLine: Command.DrawLine =>
          drawLine
            .execute
            .applying(toCanvas)
        case drawRectangle: Command.DrawRectangle =>
          drawRectangle
            .execute
            .applying(toCanvas)
        case fillBucket: Command.FillBucket =>
          fillBucket
            .execute
            .applying(toCanvas)
        case _: Command.Quit =>
          break()
      }.getOrElse(println(usage))
    }
  }

  private def toGlobalContext(newCanvas: Canvas): Unit = {
    println(newCanvas)
    canvas = Some(newCanvas)
    ()
  }

  private def toCanvas(shape: Shape): Unit =
    println(
      canvas
        .toRight(CanvasNotPresent)
        .flatMap { paint =>
          paint
            .add(shape)
            .map(_ => paint)
        }
        .fold(_.message, _.toString)
    )
}
