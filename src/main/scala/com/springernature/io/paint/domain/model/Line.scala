package com.springernature.io.paint.domain.model

case class Line(point1: Point,
                point2: Point,
                character: Char = 'X') extends Shape {

  def isHorizontal: Boolean = point1.x == point2.x
  def isVertical: Boolean = point1.y == point2.y

  override def isInside(canvas: Canvas): Boolean =
    point1.isInside(canvas) && point2.isInside(canvas)

  override def render(canvas: Canvas) {
    for { y <- point1.y to point2.y; x <- point1.x to point2.x }
        canvas(x, y) = character
  }
}
