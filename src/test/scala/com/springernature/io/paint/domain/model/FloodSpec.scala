package com.springernature.io.paint.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FloodSpec extends AnyFlatSpec with Matchers {

  "Flood point " should " be outside canvas" in {
    val canvas = Canvas(1, 1).right.get
    Flood((0, 0), '?').isInside(canvas) should be (false)
    Flood((2, 1), '?').isInside(canvas) should be (false)
    Flood((1, 2), '?').isInside(canvas) should be (false)
    Flood((2, 2), '?').isInside(canvas) should be (false)
  }

  "Flood point " should " be inside canvas" in {
    val canvas = Canvas(1, 1).right.get
    Flood((1, 1), '?').isInside(canvas) should be (true)
  }

  "Flood " should " successfully render on canvas" in {
    val canvas = Canvas(5, 4).right.get

    Rectangle(1, 1, 3, 3).render(canvas)
    Flood((1, 4), '$').render(canvas)

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
    val canvas = Canvas(5, 4).right.get

    Rectangle(1, 1, 3, 3).render(canvas)
    Flood((1, 3), '@').render(canvas)

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
