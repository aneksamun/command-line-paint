package com.springernature.io.paint.domain.validation

import com.springernature.io.paint.domain.model.{Line, Rectangle}
import com.springernature.io.paint.domain.common.Error.{BadCoordinates, IllegalLine}

object ModelValidation {

  implicit class LineValidator(val line: Line) {
    def validate: Either[String, Line] = {
      if (!line.isVertical && !line.isHorizontal)
        return Left(IllegalLine.toString)
      if ((line.isVertical && line.point2.x < line.point1.x) ||
          (line.isHorizontal && line.point2.y < line.point1.y))
        return Left(BadCoordinates.toString)

      Right(line)
    }
  }

  implicit class RectangleValidator(val rectangle: Rectangle) {
    def validate: Either[String, Rectangle] = {
      if (rectangle.x2 < rectangle.x1 || rectangle.y2 < rectangle.y1)
        return Left(BadCoordinates.toString)

      Right(rectangle)
    }
  }
}
