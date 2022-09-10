package com.springernature.io.paint.domain.model

import com.springernature.io.paint.domain.common.Error

trait Shape {

  def isInside(canvas: Canvas): Boolean

  def render(canvas: Canvas): Either[Error, Unit]

}
