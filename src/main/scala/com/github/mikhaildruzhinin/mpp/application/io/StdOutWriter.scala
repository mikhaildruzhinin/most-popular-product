package com.github.mikhaildruzhinin.mpp.application.io

import org.apache.spark.sql.DataFrame

object StdOutWriter {
  def apply(df: DataFrame): Unit = {
    df.show()
  }
}
