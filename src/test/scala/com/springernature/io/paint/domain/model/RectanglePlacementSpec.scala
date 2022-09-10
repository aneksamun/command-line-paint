package com.springernature.io.paint.domain.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.propspec.AnyPropSpec

class RectanglePlacementSpec extends AnyPropSpec with TableDrivenPropertyChecks with Matchers {

  val rectangles = Table(
    "rectangles",
    Rectangle(x1 = 1, y1 = 1, x2 = 2, y2 = 4),
    Rectangle(x1 = 1, y1 = 1, x2 = 4, y2 = 2),
    Rectangle(x1 = 3, y1 = 3, x2 = 5, y2 = 5)
  )

  property("is outside of canvas") {
    val canvas = new Canvas(width = 3, height = 3)
    forAll(rectangles) {
      _.isInside(canvas) should be(false)
    }
  }

  property("is inside canvas") {
    val canvas = new Canvas(width = 5, height = 5)
    forAll(rectangles) {
      _.isInside(canvas) should be(true)
    }
  }

  property("rectangle could not be placed at (0,0) position") {
    val canvas = new Canvas(width = 1, height = 1)
    Rectangle(x1 = 0, y1 = 0, x2 = 4, y2 = 3).isInside(canvas) should be(false)
  }
}
