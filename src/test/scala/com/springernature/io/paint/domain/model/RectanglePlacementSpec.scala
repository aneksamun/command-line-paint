package com.springernature.io.paint.domain.model

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, PropSpec}

class RectanglePlacementSpec extends PropSpec with TableDrivenPropertyChecks with Matchers {

  val rectangles =
    Table(
      "rectangles",
      Rectangle(1, 1, 2, 4),
      Rectangle(1, 1, 4, 2),
      Rectangle(3, 3, 5, 5)
    )

  property("is outside of canvas") {
    val canvas: Canvas = Canvas(3, 3).right.get
    forAll(rectangles) { _.isInside(canvas) should be (false) }
  }

  property("is inside canvas") {
    val canvas: Canvas = Canvas(5, 5).right.get
    forAll(rectangles) { _.isInside(canvas) should be (true) }
  }

  property("rectangle could not be placed at (0,0) position") {
    val canvas: Canvas = Canvas(1, 1).right.get
    Rectangle(0, 0, 4, 3).isInside(canvas) should be (false)
  }
}
