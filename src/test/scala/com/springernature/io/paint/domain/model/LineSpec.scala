package com.springernature.io.paint.domain.model

import org.scalatest.{FlatSpec, Matchers}

class LineSpec extends FlatSpec with Matchers {

  "Horizontal Line " should "successfully render on canvas" in {
    val canvas = Canvas(5, 4).right.get
    val line = Line((1, 1), (5, 1))

    line.render(canvas)

    canvas.toString should be (
        "-------\n" +
        "|XXXXX|\n" +
        "|     |\n" +
        "|     |\n" +
        "|     |\n" +
        "-------"
    )
  }

  "Vertical Line " should "successfully render on canvas" in {
    val canvas = Canvas(5, 4).right.get
    val line = Line((2, 1), (2, 3))

    line.render(canvas)

    canvas.toString should be (
        "-------\n" +
        "| X   |\n" +
        "| X   |\n" +
        "| X   |\n" +
        "|     |\n" +
        "-------"
    )
  }
}
