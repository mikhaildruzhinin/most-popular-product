package application

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types.StructType

object Reader {
  def apply(spark: SparkSession, schema: StructType, path: String): DataFrame = {
    spark
      .read
      .option("header", "false")
      .option("delimiter", "\t")
      .schema(schema)
      .csv(path)
  }
}
