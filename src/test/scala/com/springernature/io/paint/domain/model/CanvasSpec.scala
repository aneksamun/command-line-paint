package com.springernature.io.paint.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CanvasSpec extends AnyFlatSpec with Matchers {

  "Canvas " should "fail to initiate with invalid size" in {
    Canvas(0, 0).isLeft should be (true)
  }

  it should "be initiated with valid size" in {
    Canvas(5, 1).right.get.toString should be (
      "-------\n" +
      "|     |\n" +
      "-------"
    )
  }

  it should "fail to add a shape which not fit canvas" in {
    val canvas = Canvas(1, 1).right.get
    canvas.add(Rectangle(1, 1, 3, 3)).isLeft should be (true)
  }

  it should "succeed to place a shape with good position" in {
    val canvas = Canvas(10, 10).right.get
    canvas.add(Rectangle(1, 1, 3, 3)).isRight should be (true)
  }
}
