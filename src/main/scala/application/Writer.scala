package application

import org.apache.spark.sql.DataFrame

object Writer {
  def apply(df: DataFrame, path: String): Unit = {
    df
      .write
      .mode("overwrite")
      .option("header", "true")
      .option("sep", "\t")
      .csv(path)
  }
}
