package com.springernature.io.paint.console

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, PropSpec}

class CommandSpec extends PropSpec with TableDrivenPropertyChecks with Matchers {

  val badInput =
    Table(
      "input",
      "LOL",
      "C 1 R",
      "B 10 20",
      "Q YU",
      "C -1 -1",
      "B -1 -2 Y"
    )

  val commands =
    Table(
      "c 1 2",
      "l 1 2 1 3",
      "r 1 2 3 7",
      "b 10 20 J",
      "q"
    )

  property("No commands are matched") {
    forAll(badInput) { input => Command.of(input) shouldBe None }
  }

  property("All commands are matched") {
    forAll(commands) { input => Command.of(input).isDefined shouldBe true }
  }

}
