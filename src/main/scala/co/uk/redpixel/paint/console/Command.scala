package co.uk.redpixel.paint.console

import co.uk.redpixel.paint.domain.common.Error
import co.uk.redpixel.paint.domain.model._

sealed trait Command[A] {
  def execute: Either[Error, A]
}

object Command {

  final case class CreateCanvas(width: Int, height: Int) extends Command[Canvas] {
    def execute: Either[Error, Canvas] =
      Canvas.make(width, height)
  }

  object CreateCanvas {
    private[console] val pattern  = "(?i)C (\\d+) (\\d+)".r
    def description = "C\tCreates a new canvas of width w and height h"
  }

  final case class DrawLine(x1: Int, y1: Int, x2: Int, y2: Int) extends Command[Line] {
    def execute: Either[Error, Line] =
      Line.make(Point(x1, y1), Point(x2, y2))
  }

  object DrawLine {
    private[console] val pattern = "(?i)L (\\d+) (\\d+) (\\d+) (\\d+)".r
    def description = "L\tCreates a new line from (x1, y1) to (x2, y2)"
  }

  final case class DrawRectangle(x1: Int, y1: Int, x2: Int, y2: Int) extends Command[Rectangle] {
    def execute: Either[Error, Rectangle] =
      Rectangle.make(x1, y1, x2, y2)
  }

  object DrawRectangle {
    private[console] val pattern = "(?i)R (\\d+) (\\d+) (\\d+) (\\d+)".r
    def description =
      "R\tCreate a new rectangle, whose upper left corner is (x1, y1) and lower right corner is (x2, y2)"
  }

  final case class FillBucket(x: Int, y: Int, ch: Char) extends Command[Flood] {
    def execute: Either[Error, Flood] =
      Right(Flood(Point(x, y), ch))
  }

  object FillBucket {
    private[console] val pattern = "(?i)B (\\d+) (\\d+) (.+)".r
    def description = "B\tFills the entire area connected to (x, y) with specified character"
  }

  final case class Quit() extends Command[Unit] {
    def execute: Either[Error, Unit] = Right(())
  }

  object Quit {
    private[console] val pattern = "(?i)Q".r
    def description = "Q\tQuits the program"
  }

  def from(input: String): Option[Command[_]] = Option(input).collect {
    case Command.CreateCanvas.pattern(width, height) =>
      CreateCanvas(width.toInt, height.toInt)
    case Command.DrawLine.pattern(x1, y1, x2, y2) =>
      DrawLine(x1.toInt, y1.toInt, x2.toInt, y2.toInt)
    case Command.DrawRectangle.pattern(x1, y1, x2, y2) =>
      DrawRectangle(x1.toInt, y1.toInt, x2.toInt, y2.toInt)
    case Command.FillBucket.pattern(x, y, ch) =>
      FillBucket(x.toInt, y.toInt, ch(0))
    case Command.Quit.pattern() => Quit()
  }
}
