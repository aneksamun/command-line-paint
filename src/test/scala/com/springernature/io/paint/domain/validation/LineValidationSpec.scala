package com.springernature.io.paint.domain.validation

import com.springernature.io.paint.domain.model.Line
import com.springernature.io.paint.domain.validation.ModelValidation._
import org.scalatest._

class LineValidationSpec extends FlatSpec with Matchers {

  "A Line Validator " should " return an error when line is not vertical or horizontal" in {
    val invalidLine = Line((2, 1), (1, 2))
    invalidLine.validate.isLeft shouldBe true
  }

  it should " return an error when line end coordinates are behind starting point on x-axis" in {
    val line = Line((3, 1), (1, 1))
    line.validate.isLeft shouldBe true
  }

  it should " return an error when line end coordinates are behind starting point on y-axis" in {
    val line = Line((1, 3), (1, 1))
    line.validate.isLeft shouldBe true
  }

  it should " succeed when nor errors are detected" in {
    val line = Line((1, 3), (1, 9))
    line.validate.isRight shouldBe true
  }
}
