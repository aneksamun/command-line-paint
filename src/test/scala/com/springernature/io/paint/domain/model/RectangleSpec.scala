package com.springernature.io.paint.domain.model

import org.scalatest.{FlatSpec, Matchers}

class RectangleSpec extends FlatSpec with Matchers {

  "Rectangle " should "successfully render on canvas" in {
    val canvas = Canvas(5, 4).right.get
    val rectangle = Rectangle(1, 1, 3, 3)

    rectangle.render(canvas)

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
