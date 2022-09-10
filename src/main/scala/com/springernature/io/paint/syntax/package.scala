package com.springernature.io.paint

import com.springernature.io.paint.domain.common.Error

package object syntax {

  implicit class CommandExecutionOps[A](private val execution: Either[Error, A]) extends AnyVal {
    def applying(action: A => Unit): Unit =
      execution.fold(
        error => println(error.message),
        action
      )
  }
}
