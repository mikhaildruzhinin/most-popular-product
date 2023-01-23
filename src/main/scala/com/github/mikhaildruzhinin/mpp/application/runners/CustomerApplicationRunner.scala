package com.github.mikhaildruzhinin.mpp.application.runners

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import com.github.mikhaildruzhinin.mpp.application.io.Reader
import com.github.mikhaildruzhinin.mpp.application.io.writers.PrintWriter
import com.github.mikhaildruzhinin.mpp.application.models.Customer
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}

object CustomerApplicationRunner extends ApplicationRunner {
  override def apply(appConfig: AppConfig)(implicit spark: SparkSession): Unit = {
    import spark.implicits._
    val customerSchema: StructType = Encoders.product[Customer].schema
    lazy val customerDs: Dataset[Customer] = Reader[Customer](customerSchema, appConfig.source.customer)
    PrintWriter(customerDs, appConfig.sink.result)
  }
}
