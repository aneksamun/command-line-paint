package com.springernature.io.paint.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LineValidationSpec extends AnyFlatSpec with Matchers {

  "Line create " should " return an error when line is not vertical or horizontal" in {
    val invalidLine = Line.make((2, 1), (1, 2))
    invalidLine.isLeft should be(true)
  }

  it should " return an error when line end coordinates are behind starting point on x-axis" in {
    Line.make((3, 1), (1, 1)).isLeft should be(true)
  }

  it should " return an error when line end coordinates are behind starting point on y-axis" in {
    Line.make((1, 3), (1, 1)).isLeft should be(true)
  }

  it should " succeed when nor errors are detected" in {
    Line.make((1, 3), (1, 9)).isRight should be(true)
  }
}
