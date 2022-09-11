package co.uk.redpixel.paint.domain.model

final case class Point(x: Int, y: Int) {

  def ==(that: Point): Boolean = this.equals(that)
  def =!(that: Point): Boolean = !this.equals(that)

  def +(that: Point) = Point(this.x + that.x, this.y + that.y)
  def -(that: Point) = Point(this.x - that.y, this.y - that.y)
  def *(that: Point) = Point(this.x * that.x, this.y * that.y)
  def /(that: Point) = Point(this.x / that.x, this.y / that.y)

  def +(tuple: (Int, Int)) = Point(x + tuple._1, this.y + tuple._2)
  def -(tuple: (Int, Int)) = Point(x - tuple._1, this.y - tuple._2)
  def *(tuple: (Int, Int)) = Point(x * tuple._1, this.y * tuple._2)
  def /(tuple: (Int, Int)) = Point(x / tuple._1, this.y / tuple._2)

  def isInside(canvas: Canvas): Boolean =
    (x > 0 && x < canvas.width - 1) &&
      (y > 0 && y < canvas.height - 1)
}

object Point {
  implicit def toTuple(point: Point): (Int, Int) = (point.x, point.y)
  implicit def toPoint(tuple: (Int, Int)): Point =
    Point(tuple._1, tuple._2)
}
