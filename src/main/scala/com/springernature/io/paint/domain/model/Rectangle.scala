package com.springernature.io.paint.domain.model

case class Rectangle(x1: Int, y1: Int, x2: Int, y2: Int) extends Shape {

  private val top = Line(Point(x1, y1), Point(x2, y1))
  private val left = Line(Point(x1, y1), Point(x1, y2))
  private val right = Line(Point(x2, y1), Point(x2, y2))
  private val bottom = Line(Point(x1, y2), Point(x2, y2))

  override def isInside(canvas: Canvas): Boolean =
    top.isInside(canvas) &&
    left.isInside(canvas) &&
    right.isInside(canvas) &&
    bottom.isInside(canvas)

  override def render(canvas: Canvas) {
    canvas.add(top)
    canvas.add(left)
    canvas.add(right)
    canvas.add(bottom)
  }
}
