package com.github.mikhaildruzhinin.mpp.application

import com.github.mikhaildruzhinin.mpp.application.config.OutputParams
import org.apache.spark.sql.DataFrame

object Writer {
  def apply(df: DataFrame, outputParams: OutputParams): Unit = {
    df
      .write
      .mode(outputParams.saveMode)
      .option("header", outputParams.header)
      .option("sep", outputParams.delimiter)
      .csv(outputParams.path)
  }
}
