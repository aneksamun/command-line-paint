package com.springernature.io.paint.integration

import com.springernature.io.paint.console.Command
import com.springernature.io.paint.domain.model.{Canvas, Line, Rectangle}
import org.scalatest.{GivenWhenThen, OptionValues}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers

class PaintingApplicationSpec extends AnyFeatureSpec
  with GivenWhenThen
  with OptionValues
  with Matchers {

  info("As a user")
  info("I want to be able to draw an items on canvas")
  info("So that I can create my own paint")

  Feature("Test case") {
    Scenario("User creates a paint") {

      Given("a canvas")
      val canvas = getOrFail[Canvas]("C 20 4")

      And("lines")
      val line1 = getOrFail[Line]("L 1 2 6 2")
      val line2 = getOrFail[Line]("L 6 3 6 4")
      canvas add line1
      canvas add line2

      And("rectangle")
      val rectangle = getOrFail[Rectangle]("R 16 1 20 3")
      canvas add rectangle

      And("bucket fill")
      val bucket = getOrFail[Rectangle]("B 10 3 o")
      canvas add bucket

      When("canvas is rendered")
      val paint = canvas.toString

      Then("it has all items in place")
      paint should be(
        "----------------------\n" +
          "|oooooooooooooooXXXXX|\n" +
          "|XXXXXXoooooooooX   X|\n" +
          "|     XoooooooooXXXXX|\n" +
          "|     Xoooooooooooooo|\n" +
          "----------------------"
      )
    }
  }

  private def getOrFail[A](input: String): A = (for {
    cmd <- Command.from(input)
    outcome <- cmd.execute.map(_.asInstanceOf[A]).toOption
  } yield outcome).value
}
