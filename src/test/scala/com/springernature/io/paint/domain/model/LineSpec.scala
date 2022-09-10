package com.springernature.io.paint.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LineSpec extends AnyFlatSpec with Matchers {

  "Horizontal Line " should "successfully render on canvas" in {
    val canvas = new Canvas(width = 5, height = 4)
    val line = Line((1, 1), (5, 1))

    line.render(canvas).isRight should be (true)

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
    val canvas = new Canvas(width = 5, height = 4)
    val line = Line((2, 1), (2, 3))

    line.render(canvas).isRight should be (true)

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
