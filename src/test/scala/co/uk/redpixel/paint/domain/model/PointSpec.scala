package co.uk.redpixel.paint.domain.model

import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PointSpec extends AnyFlatSpec with EitherValues with Matchers {

  "Two Same Points " should " be equal" in {
    assert(Point(x = 1, y = 1) == Point(x = 1, y = 1))
  }

  "Two Different Points " should " not be equal" in {
    assert(Point(1, 1) != Point(1, 2))
  }

  "Sum " should " add point coordinates" in {
    assert(Point(1, 1) + Point(1, 2) == Point(2, 3))
    assert(Point(1, 1) + (1, 2) == Point(2, 3))
  }

  "Minus " should " should subtract point coordinates" in {
    assert(Point(1, 1) - Point(1, 1) == Point(0, 0))
    assert(Point(1, 1) - (1, 1) == Point(0, 0))
  }

  "Multiply " should " should multiply point coordinates" in {
    assert(Point(1, 1) * Point(8, 9) == Point(8, 9))
    assert(Point(1, 1) * (8, 9) == Point(8, 9))
  }

  "Divide " should " should divide point coordinates" in {
    assert(Point(8, 9) / Point(2, 3) == Point(4, 3))
    assert(Point(8, 9) / (2, 3) == Point(4, 3))
  }

  "Point " should " be outside canvas" in {
    val canvas = Canvas.make(width = 1, height = 1).value
    Point(0, 0).isInside(canvas) should be(false)
    Point(2, 1).isInside(canvas) should be(false)
    Point(1, 2).isInside(canvas) should be(false)
    Point(2, 2).isInside(canvas) should be(false)
  }

  it should " should be inside canvas" in {
    val canvas = Canvas
      .make(width = 1, height = 1)
      .value
    Point(1, 1).isInside(canvas) should be(true)
  }
}
