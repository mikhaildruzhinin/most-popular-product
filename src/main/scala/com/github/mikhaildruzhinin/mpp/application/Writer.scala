package com.github.mikhaildruzhinin.mpp.application

import com.github.mikhaildruzhinin.mpp.application.config.Output
import org.apache.spark.sql.DataFrame

object Writer {
  def apply(df: DataFrame, output: Output): Unit = {
    df
      .write
      .mode(output.saveMode)
      .option("header", output.header)
      .option("sep", output.delimiter)
      .csv(output.path)
  }
}
