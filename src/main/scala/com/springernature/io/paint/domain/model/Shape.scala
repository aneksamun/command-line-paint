package com.springernature.io.paint.domain.model

trait Shape {
  def isInside(canvas: Canvas): Boolean
  def render(canvas: Canvas): Unit
}
