package com.github.mikhaildruzhinin.mpp.application.io

import com.github.mikhaildruzhinin.mpp.application.config.SinkParams
import org.apache.spark.sql.DataFrame

object Writer {
  def apply(df: DataFrame, sinkParams: SinkParams): Unit = {
    df
      .write
      .mode(sinkParams.saveMode)
      .option("header", sinkParams.header)
      .option("sep", sinkParams.delimiter)
      .csv(sinkParams.path)
  }
}
