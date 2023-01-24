package com.github.mikhaildruzhinin.mpp.application.runners

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import com.github.mikhaildruzhinin.mpp.application.io.Reader
import com.github.mikhaildruzhinin.mpp.application.io.writers.PrintWriter
import com.github.mikhaildruzhinin.mpp.application.models.Customer
import com.github.mikhaildruzhinin.mpp.application.transformations.CustomerTransformations._
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}

object CustomerApplicationRunner extends ApplicationRunner {
  override def apply()(implicit appConfig: AppConfig, spark: SparkSession): Unit = {
    import spark.implicits._
    lazy val customerDs: Dataset[Customer] = Reader[Customer](
      Encoders.product[Customer].schema,
      appConfig.source.customer
    )
    val transformedCustomerDs: Dataset[Customer] = customerDs.transform(dummyTransformation())
    PrintWriter(transformedCustomerDs, appConfig.sink.result)
  }
}
