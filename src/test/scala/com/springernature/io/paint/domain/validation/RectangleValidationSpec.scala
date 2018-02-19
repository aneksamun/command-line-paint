package com.springernature.io.paint.domain.validation

import com.springernature.io.paint.domain.model.{Line, Rectangle}
import com.springernature.io.paint.domain.validation.ModelValidation._
import org.scalatest.{FlatSpec, Matchers}

class RectangleValidationSpec extends FlatSpec with Matchers {

  "A Rectangle Validator " should " return an error when x2 is less than x1" in {
    val rectangle = Rectangle(10, 3, 1, 7)
    rectangle.validate.isLeft shouldBe true
  }

  it should " return an error return an error when y2 is less than y1" in {
    val rectangle = Rectangle(1, 3, 5, 1)
    rectangle.validate.isLeft shouldBe true
  }

  it should " succeed when no errors are detected" in {
    val line = Rectangle(1, 3, 5, 9)
    line.validate.isRight shouldBe true
  }
}
