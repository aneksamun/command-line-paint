package com.springernature.io.paint.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FloodSpec extends AnyFlatSpec with Matchers {

  "Flood point " should " be outside canvas" in {
    val canvas = new Canvas(width = 1, height = 1)
    Flood((0, 0), '?').isInside(canvas) should be (false)
    Flood((2, 1), '?').isInside(canvas) should be (false)
    Flood((1, 2), '?').isInside(canvas) should be (false)
    Flood((2, 2), '?').isInside(canvas) should be (false)
  }

  "Flood point " should " be inside canvas" in {
    val canvas = new Canvas(width = 1, height = 1)
    Flood((1, 1), '?').isInside(canvas) should be (true)
  }

  "Flood " should " successfully render on canvas" in {
    val canvas = new Canvas(width = 5, height = 4)

    Rectangle(x1 = 1, y1 = 1, x2 = 3, y2 = 3)
      .render(canvas)
      .isRight should be (true)

    Flood((1, 4), '$')
      .render(canvas)
      .isRight should be (true)

    canvas.toString should be (
        "-------\n" +
        "|XXX$$|\n" +
        "|X X$$|\n" +
        "|XXX$$|\n" +
        "|$$$$$|\n" +
        "-------"
    )
  }

  "Flood " should " re-render on canvas" in {
    val canvas = new Canvas(width = 5, height = 4)

    Rectangle(x1 = 1, y1 = 1, x2 = 3, y2 = 3)
      .render(canvas)
      .isRight should be (true)

    Flood((1, 3), '@')
      .render(canvas)
      .isRight should be (true)

    canvas.toString should be (
      "-------\n" +
      "|@@@  |\n" +
      "|@ @  |\n" +
      "|@@@  |\n" +
      "|     |\n" +
      "-------"
    )
  }
}
