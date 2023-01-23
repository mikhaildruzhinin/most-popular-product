package com.github.mikhaildruzhinin.mpp.application.io

import com.github.mikhaildruzhinin.mpp.application.config.SourceParams
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}

object Reader {
  def apply[T: Encoder](schema: StructType, sourceParams: SourceParams)
                       (implicit spark: SparkSession): Dataset[T] = {
    spark
      .read
      .option("header", sourceParams.header)
      .option("delimiter", sourceParams.delimiter)
      .schema(schema)
      .csv(sourceParams.path)
      .as[T]
  }
}
