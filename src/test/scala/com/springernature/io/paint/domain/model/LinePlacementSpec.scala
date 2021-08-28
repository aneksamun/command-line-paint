package com.springernature.io.paint.domain.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.propspec.AnyPropSpec

class LinePlacementSpec extends AnyPropSpec with TableDrivenPropertyChecks with Matchers {

  val lines =
    Table(
      "lines",
      Line((1, 1), (1, 4)),
      Line((1, 1), (4, 1))
    )

  property("is outside of canvas") {
    val canvas: Canvas = Canvas(3, 3).right.get
    forAll(lines) { _.isInside(canvas) should be (false) }
  }

  property("is inside canvas") {
    val canvas: Canvas = Canvas(4, 4).right.get
    forAll(lines) { _.isInside(canvas) should be (true) }
  }

  property("line could not be placed at (0,0) position") {
    val canvas: Canvas = Canvas(2, 2).right.get
    Rectangle(0, 0, 1, 1).isInside(canvas) should be (false)
  }
}
