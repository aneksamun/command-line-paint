package com.springernature.io.paint.integration

import com.springernature.io.paint.console.Command
import com.springernature.io.paint.domain.model.{Canvas, Line, Rectangle}
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class PaintingApplicationSpec extends FeatureSpec with GivenWhenThen with Matchers {

  info("As a user")
  info("I want to be able to draw an items on canvas")
  info("So that I can create my own paint")

  feature("Test case") {
    scenario("User creates a paint") {

      Given("a canvas")
      val canvas = extract[Canvas](Command.of("C 20 4"))

      And("lines")
      canvas.add(extract[Line](Command.of("L 1 2 6 2")))
      canvas.add(extract[Line](Command.of("L 6 3 6 4")))

      And("rectangle")
      canvas.add(extract[Rectangle](Command.of("R 16 1 20 3")))

      And("bucket fill")
      canvas.add(extract[Rectangle](Command.of("B 10 3 o")))

      When("canvas is rendered")
      val actual = canvas.toString

      Then("it has all items in place")
      actual should be (
        "----------------------\n" +
        "|oooooooooooooooXXXXX|\n" +
        "|XXXXXXoooooooooX   X|\n" +
        "|     XoooooooooXXXXX|\n" +
        "|     Xoooooooooooooo|\n" +
        "----------------------"
      )
    }
  }

  private def extract[T](option: Option[(Command, Either[String, _])]): T = {
    option.get._2.right.get.asInstanceOf[T]
  }
}
