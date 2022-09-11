package co.uk.redpixel.paint.domain.model

import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RectangleSpec extends AnyFlatSpec with Matchers with EitherValues {

  "Rectangle " should "successfully render on canvas" in {
    val canvas = Canvas.make(width = 5, height = 4).value
    val rectangle = Rectangle(x1 = 1, y1 = 1, x2 = 3, y2 = 3)

    rectangle.render(canvas).isRight should be (true)

    canvas.toString should be (
      "-------\n" +
      "|XXX  |\n" +
      "|X X  |\n" +
      "|XXX  |\n" +
      "|     |\n" +
      "-------"
    )
  }
}
