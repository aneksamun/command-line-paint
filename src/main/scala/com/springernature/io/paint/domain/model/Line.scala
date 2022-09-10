package com.springernature.io.paint.domain.model

import com.springernature.io.paint.domain.common.Error
import com.springernature.io.paint.domain.common.Error.{BadCoordinates, IllegalLine}

final case class Line(point1: Point, point2: Point, fill: Char = 'X') extends Shape {

  def isHorizontal: Boolean = point1.x == point2.x

  def isVertical: Boolean = point1.y == point2.y

  def isInside(canvas: Canvas): Boolean =
    point1.isInside(canvas) && point2.isInside(canvas)

  def render(canvas: Canvas): Right[Error, Unit] = Right {
    for { y <- point1.y to point2.y; x <- point1.x to point2.x }
      canvas(x, y) = fill
  }
}

object Line {

  def make(point1: Point, point2: Point, fill: Char = 'X'): Either[Error, Line] = {
    val line = Line(point1, point2, fill)

    if (!line.isVertical && !line.isHorizontal)
      return Left(IllegalLine)

    if ((line.isVertical && line.point2.x < line.point1.x) ||
        (line.isHorizontal && line.point2.y < line.point1.y))
      return Left(BadCoordinates)

    Right(line)
  }
}
