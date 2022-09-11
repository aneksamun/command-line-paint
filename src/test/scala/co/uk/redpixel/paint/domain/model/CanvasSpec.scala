package co.uk.redpixel.paint.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CanvasSpec extends AnyFlatSpec with Matchers  {

  "Canvas " should "fail to initiate with invalid size" in {
    Canvas.make(width = 0, height = 0).isLeft should be (true)
  }

  it should "be initiated with valid size" in {
    Canvas.make(width = 5, height = 1).map(_.toString) shouldBe Right(
      "-------\n" +
      "|     |\n" +
      "-------"
    )
  }

  it should "fail to add a shape which not fit canvas" in {
    Canvas.make(width = 1, height = 1)
      .flatMap(_.add(Rectangle(x1 = 1, y1 = 1, x2 = 3, y2 = 3)))
      .isLeft shouldBe true
  }

  it should "succeed to place a shape with good position" in {
    Canvas.make(width = 10, height = 10)
      .flatMap(_.add(Rectangle(x1 = 1, y1 = 1, x2 = 3, y2 = 3)))
      .isRight should be (true)
  }
}
