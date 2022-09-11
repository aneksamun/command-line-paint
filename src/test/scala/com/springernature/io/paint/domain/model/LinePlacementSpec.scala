package com.springernature.io.paint.domain.model

import org.scalatest.EitherValues
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.propspec.AnyPropSpec

class LinePlacementSpec extends AnyPropSpec
  with TableDrivenPropertyChecks
  with EitherValues
  with Matchers {

  val lines = Table(
    "lines",
    Line((1, 1), (1, 4)),
    Line((1, 1), (4, 1))
  )

  property("is outside of canvas") {
    val canvas = Canvas.make(width = 3, height = 3).value
    forAll(lines) {
      _.isInside(canvas) should be(false)
    }
  }

  property("is inside canvas") {
    val canvas = Canvas.make(width = 4, height = 4).value
    forAll(lines) {
      _.isInside(canvas) should be(true)
    }
  }

  property("line could not be placed at (0,0) position") {
    val canvas = Canvas.make(width = 2, height = 2).value
    Rectangle(0, 0, 1, 1).isInside(canvas) should be(false)
  }
}
