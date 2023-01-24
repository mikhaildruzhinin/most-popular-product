package com.github.mikhaildruzhinin.mpp.application.io.writers

import com.github.mikhaildruzhinin.mpp.application.config.SinkParams
import org.apache.spark.sql.DataFrame
trait Writer {
  def apply(df: DataFrame, sinkParams: SinkParams): Unit
}
