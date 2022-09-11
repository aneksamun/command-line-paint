package co.uk.redpixel.paint.domain.model

import co.uk.redpixel.paint.domain.common.Error

trait Shape {

  def isInside(canvas: Canvas): Boolean

  def render(canvas: Canvas): Either[Error, Unit]

}
