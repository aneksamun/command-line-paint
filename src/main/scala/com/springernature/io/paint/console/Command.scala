package com.springernature.io.paint.console

import com.springernature.io.paint.domain.model._
import com.springernature.io.paint.domain.validation.ModelValidation._
import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable.IndexedSeq
import scala.util.matching.Regex


sealed trait Command extends EnumEntry {
  private[console] val pattern: Regex
}

object Command extends Enum[Command] {
  val values: IndexedSeq[Command] = findValues

  case object CreateCanvas extends Command {
    override private[console] val pattern: Regex = "(?i)C (\\d+) (\\d+)".r
    override def toString: String = "C\tCreates a new canvas of width w and height h"
    def execute(width: Int, height: Int): Either[String, Canvas] =
      Canvas(width, height)
  }

  case object DrawLine extends Command {
    override private[console] val pattern: Regex = "(?i)L (\\d+) (\\d+) (\\d+) (\\d+)".r
    override def toString: String = "L\tCreates a new line from (x1,y1) to (x2,y2)"
    def execute(x1: Int, y1: Int, x2: Int, y2: Int): Either[String, Line] =
      Line(Point(x1, y1), Point(x2, y2)).validate
  }

  case object DrawRectangle extends Command {
    override private[console] val pattern: Regex = "(?i)R (\\d+) (\\d+) (\\d+) (\\d+)".r
    override def toString: String = "R\tCreate a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2)"
    def execute(x1: Int, y1: Int, x2: Int, y2: Int): Either[String, Rectangle] =
      Rectangle(x1, y1, x2, y2).validate
  }

  case object FillBucket extends Command {
    override private[console] val pattern: Regex = "(?i)B (\\d+) (\\d+) (.+)".r
    override def toString: String = "B\tFills the entire area connected to (x,y) with specified character"
    def execute(x: Int, y: Int, ch: Char): Either[String, Flood] =
      Right(Flood(Point(x, y), ch))
  }

  case object Quit extends Command {
    override private[console] val pattern: Regex = "(?i)Q".r
    override def toString: String = "Q\tQuits the program"
  }

  def of(line: String): Option[(Command, Either[String, _])] = {
    line match {
      case CreateCanvas.pattern(width, height) =>
        Some((CreateCanvas, CreateCanvas.execute(width.toInt, height.toInt)))
      case DrawLine.pattern(x1, y1, x2, y2) =>
        Some(DrawLine, DrawLine.execute(x1.toInt, y1.toInt, x2.toInt, y2.toInt))
      case DrawRectangle.pattern(x1, y1, x2, y2) =>
        Some(DrawRectangle, DrawRectangle.execute(x1.toInt, y1.toInt, x2.toInt, y2.toInt))
      case FillBucket.pattern(x, y, ch) =>
        Some(FillBucket, FillBucket.execute(x.toInt, y.toInt, ch(0)))
      case Quit.pattern() =>
        Some(Quit, Right(None))
      case _ => None
    }
  }
}
