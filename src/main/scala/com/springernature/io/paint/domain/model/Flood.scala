package com.springernature.io.paint.domain.model

import com.springernature.io.paint.domain.common.Error

final case class Flood(startingPoint: Point, ch: Char) extends Shape {

  def render(canvas: Canvas): Either[Error, Unit] = Right {
    fill(canvas, startingPoint, canvas.charAt(startingPoint))
  }

  private def fill(canvas: Canvas, point: Point, oldChar: Char): Unit =
    if (isInside(point, canvas) && isEligibleArea(point, canvas, oldChar)) {
      canvas(point.x, point.y) = ch

      fill(canvas, point.copy(x = point.x + 1), oldChar)
      fill(canvas, point.copy(x = point.x - 1), oldChar)
      fill(canvas, point.copy(y = point.y + 1), oldChar)
      fill(canvas, point.copy(y = point.y - 1), oldChar)
    }

  def isInside(canvas: Canvas): Boolean =
    isInside(startingPoint, canvas)

  private def isInside(point: Point, canvas: Canvas): Boolean =
    (point.x > 0 && point.x < canvas.width - 1) &&
      (point.y > 0 && point.y < canvas.height - 1)

  private def isEligibleArea(point: Point, canvas: Canvas, oldChar: Char): Boolean =
    canvas(point.x, point.y) == oldChar
}
