package com.github.mikhaildruzhinin.mpp.application.io

import com.github.mikhaildruzhinin.mpp.application.config.SourceParams
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

object Reader {
  def apply(schema: StructType, sourceParams: SourceParams)
                       (implicit spark: SparkSession): DataFrame = {
    spark
      .read
      .option("header", sourceParams.header)
      .option("delimiter", sourceParams.delimiter)
      .schema(schema)
      .csv(sourceParams.path)
  }
}
