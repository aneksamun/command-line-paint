package co.uk.redpixel.paint.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RectangleValidationSpec extends AnyFlatSpec with Matchers {

  "Rectangle create " should " return an error when x2 is less than x1" in {
    Rectangle
      .make(x1 = 10, y1 = 3, x2 = 1, y2 = 7)
      .isLeft should be(true)
  }

  it should " return an error return an error when y2 is less than y1" in {
    Rectangle
      .make(x1 = 1, y1 = 3, x2 = 5, y2 = 1)
      .isLeft should be(true)
  }

  it should " succeed when no errors are detected" in {
    Rectangle
      .make(x1 = 1, y1 = 3, x2 = 5, y2 = 9)
      .isRight should be (true)
  }
}
