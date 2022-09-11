package co.uk.redpixel.paint.domain.model

import co.uk.redpixel.paint.domain.common.Error
import Error.BadCoordinates

final case class Rectangle private(x1: Int, y1: Int, x2: Int, y2: Int) extends Shape {

  private val top = Line(Point(x1, y1), Point(x2, y1))
  private val left = Line(Point(x1, y1), Point(x1, y2))
  private val right = Line(Point(x2, y1), Point(x2, y2))
  private val bottom = Line(Point(x1, y2), Point(x2, y2))

  def isInside(canvas: Canvas): Boolean =
    top.isInside(canvas) &&
    left.isInside(canvas) &&
    right.isInside(canvas) &&
    bottom.isInside(canvas)

  def render(canvas: Canvas): Either[Error, Unit] =
    for {
      _ <- canvas.add(top)
      _ <- canvas.add(left)
      _ <- canvas.add(right)
      _ <- canvas.add(bottom)
    } yield ()
}

object Rectangle {

  def make(x1: Int, y1: Int, x2: Int, y2: Int): Either[Error, Rectangle] =
    Either.cond(x2 >= x1 && y2 >= y1, Rectangle(x1, y1, x2, y2), BadCoordinates)

}
