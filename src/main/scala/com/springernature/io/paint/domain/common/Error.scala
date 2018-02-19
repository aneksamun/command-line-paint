package com.springernature.io.paint.domain.common

import com.springernature.io.paint.domain.model.Canvas

object Error extends Enumeration {
  val InvalidCanvasSize: Value = Value(s"Invalid size. Canvas width and height should be at least ${Canvas.margin}.")
  val BadCoordinates: Value = Value("The ending coordinates must be greater than starting point.")
  val BadPosition: Value = Value("Bad position. Please place an item within canvas.")
  val IllegalLine: Value = Value("Only vertical or horizontal lines are supported.")
  val CanvasNotPresent: Value = Value("Please create a canvas first.")
}
