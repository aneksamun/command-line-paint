package com.springernature.io.paint.domain.model

case class Flood(startingPoint: Point, ch: Char) extends Shape {

  override def render(canvas: Canvas) {
    fill(canvas, startingPoint, canvas(startingPoint.x, startingPoint.y))
  }

  private def fill(canvas: Canvas, point: Point, oldChar: Char) {
    if (isInside(point, canvas) && isEligibleArea(point, canvas, oldChar)) {
      canvas(point.x, point.y) = ch

      fill(canvas, point.copy(x = point.x + 1), oldChar)
      fill(canvas, point.copy(x = point.x - 1), oldChar)
      fill(canvas, point.copy(y = point.y + 1), oldChar)
      fill(canvas, point.copy(y = point.y - 1), oldChar)
    }
  }

  override def isInside(canvas: Canvas): Boolean =
    isInside(startingPoint, canvas)

  private def isInside(point: Point, canvas: Canvas): Boolean = {
    (point.x > 0 && point.x < canvas.width - 1) &&
      (point.y > 0 && point.y < canvas.height - 1)
  }

  private def isEligibleArea(point: Point, canvas: Canvas, oldChar: Char): Boolean =
    canvas(point.x, point.y) == oldChar
}
