package com.springernature.io.paint.domain.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.propspec.AnyPropSpec

class LinePlacementSpec extends AnyPropSpec with TableDrivenPropertyChecks with Matchers {

  val lines = Table(
    "lines",
    Line((1, 1), (1, 4)),
    Line((1, 1), (4, 1))
  )

  property("is outside of canvas") {
    val canvas: Canvas = new Canvas(width = 3, height = 3)
    forAll(lines) {
      _.isInside(canvas) should be(false)
    }
  }

  property("is inside canvas") {
    val canvas: Canvas = new Canvas(width = 4, height = 4)
    forAll(lines) {
      _.isInside(canvas) should be(true)
    }
  }

  property("line could not be placed at (0,0) position") {
    val canvas: Canvas = new Canvas(width = 2, height = 2)
    Rectangle(0, 0, 1, 1).isInside(canvas) should be(false)
  }
}
