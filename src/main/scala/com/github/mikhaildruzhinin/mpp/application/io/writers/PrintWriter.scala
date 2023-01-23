package com.github.mikhaildruzhinin.mpp.application.io.writers

import com.github.mikhaildruzhinin.mpp.application.config.SinkParams
import org.apache.spark.sql.DataFrame

object PrintWriter extends Writer {
  override def apply(df: DataFrame, sinkParams: SinkParams): Unit = {
    df.show()
  }
}
