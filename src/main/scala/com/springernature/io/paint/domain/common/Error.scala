package com.springernature.io.paint.domain.common

import com.springernature.io.paint.domain.model.Canvas
import enumeratum._

import scala.collection.immutable.IndexedSeq

sealed abstract class Error(val message: String) extends EnumEntry

object Error extends Enum[Error] {

  object InvalidCanvasSize extends Error(s"Invalid size. Canvas width and height should be at least ${Canvas.margin}.")
  object BadCoordinates extends Error("The ending coordinates must be greater than starting point.")
  object BadPosition extends Error("Bad position. Please place an item within canvas.")
  object IllegalLine extends Error("Only vertical or horizontal lines are supported.")
  object CanvasNotPresent extends Error("Please create a canvas first.")

  def values: IndexedSeq[Error] = findValues
}
