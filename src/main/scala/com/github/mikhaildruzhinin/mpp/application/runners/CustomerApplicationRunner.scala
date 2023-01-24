package com.github.mikhaildruzhinin.mpp.application.runners

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import com.github.mikhaildruzhinin.mpp.application.io.Reader
import com.github.mikhaildruzhinin.mpp.application.io.writers.PrintWriter
import com.github.mikhaildruzhinin.mpp.application.models.Customer
import com.github.mikhaildruzhinin.mpp.application.transformations.CustomerTransformations
import org.apache.spark.sql.{DataFrame, Encoders, SparkSession}

object CustomerApplicationRunner extends ApplicationRunner {
  override def apply()(implicit appConfig: AppConfig, spark: SparkSession): Unit = {
    lazy val customerDf: DataFrame = Reader(
      Encoders.product[Customer].schema,
      appConfig.source.customer
    )
    lazy val transformedCustomerDf: DataFrame = CustomerTransformations(customerDf)
    PrintWriter(transformedCustomerDf, appConfig.sink.result)
  }
}
