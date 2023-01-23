package com.github.mikhaildruzhinin.mpp.application.io.writers

import com.github.mikhaildruzhinin.mpp.application.config.SinkParams
import org.apache.spark.sql.Dataset

object PrintWriter extends Writer {
  override def apply[T](ds: Dataset[T], sinkParams: SinkParams): Unit = {
    ds.show()
  }
}
