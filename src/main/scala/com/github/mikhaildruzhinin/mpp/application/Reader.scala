package com.github.mikhaildruzhinin.mpp.application

import com.github.mikhaildruzhinin.mpp.application.config.InputParams
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

object Reader {
  def apply(schema: StructType, inputParams: InputParams)(implicit spark: SparkSession): DataFrame = {
    spark
      .read
      .option("header", inputParams.header)
      .option("delimiter", inputParams.delimiter)
      .schema(schema)
      .csv(inputParams.path)
  }
}
