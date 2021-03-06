package com.springernature.io.paint.domain.model

import com.springernature.io.paint.domain.common.Error.{InvalidCanvasSize, BadPosition}

class Canvas private(val width: Int,
                     val height: Int,
                     val background: Char) {

  private val grid: Array[Array[Char]] = Array.fill(height, width) { background }

  def apply(x: Int, y: Int): Char = grid(y)(x)

  def update(x: Int, y: Int, char: Char) {
    grid(y)(x) = char
  }

  def add(shape: Shape): Either[String, Canvas] = {
    if (shape.isInside(this)) {
      shape.render(this)
      return Right(this)
    }
    Left(BadPosition.toString)
  }

  override def toString: String = {
    val builder = new StringBuilder()
    for (i <- grid.indices) {
      builder.appendAll(grid(i))
      if (i + 1 < grid.length)
        builder.append("\n")
    }
    builder.toString
  }
}

object Canvas {
  val margin = 1
  val horizontalEdgeCharacter = '-'
  val verticalEdgeCharacter = '|'

  def apply(width: Int, height: Int): Either[String, Canvas] = {
    if (width < margin || height < margin)
      return Left(InvalidCanvasSize.toString)

    val canvas = new Canvas(getTotal(width), getTotal(height), 32.toChar)

    val topBorder = calculateTopBorder(width)
    val leftBorder = calculateLeftBorder(height)
    val rightBorder = calculateRightBorder(width, height)
    val bottomBorder = calculateBottomBorder(width, height)

    topBorder.render(canvas)
    leftBorder.render(canvas)
    rightBorder.render(canvas)
    bottomBorder.render(canvas)

    Right(canvas)
  }

  def calculateTopBorder(width: Int): Line = {
    val point1 = Point(x = 0, y = 0)
    val point2 = Point(x = width + margin, y = 0)
    Line(point1, point2, horizontalEdgeCharacter)
  }

  def calculateLeftBorder(height: Int): Line = {
    val point1 = Point(x = 0, y = margin)
    val point2 = Point(x = 0, y = height)
    Line(point1, point2, verticalEdgeCharacter)
  }

  def calculateRightBorder(width: Int, height: Int): Line = {
    val point1 = Point(x = width + margin, y = margin)
    val point2 = Point(x = width + margin, y = height)
    Line(point1, point2, verticalEdgeCharacter)
  }

  def calculateBottomBorder(width: Int, height: Int): Line = {
    val point1 = Point(x = 0, y = height + margin)
    val point2 = Point(x = width + margin, y = height + margin)
    Line(point1, point2, horizontalEdgeCharacter)
  }

  private def getTotal(size: Int): Int = size + margin * 2
}
