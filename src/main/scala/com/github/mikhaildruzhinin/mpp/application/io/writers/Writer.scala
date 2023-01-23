package com.github.mikhaildruzhinin.mpp.application.io.writers

import com.github.mikhaildruzhinin.mpp.application.config.SinkParams
import org.apache.spark.sql.Dataset

trait Writer {
  def apply[T](ds: Dataset[T], sinkParams: SinkParams): Unit
}
