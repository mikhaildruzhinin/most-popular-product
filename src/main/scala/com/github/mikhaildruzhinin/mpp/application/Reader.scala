package com.github.mikhaildruzhinin.mpp.application

import com.github.mikhaildruzhinin.mpp.application.config.Input
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

object Reader {
  def apply(schema: StructType, input: Input)(implicit spark: SparkSession): DataFrame = {
    spark
      .read
      .option("header", input.header)
      .option("delimiter", input.delimiter)
      .schema(schema)
      .csv(input.path)
  }
}
