package com.github.mikhaildruzhinin.mpp.application

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import com.github.mikhaildruzhinin.mpp.application.schemas._
import com.github.mikhaildruzhinin.mpp.application.transformations._
import org.apache.spark.sql.{DataFrame, SparkSession}

class ApplicationRunner(appConfig: AppConfig)(implicit spark: SparkSession) {
  protected lazy val customerDf: DataFrame = Reader(
    customerSchema,
    appConfig.input.customer
  )

  protected lazy val orderDf: DataFrame = Reader(
    orderSchema,
    appConfig.input.order
  )

  protected lazy val productDf: DataFrame = Reader(
    productSchema,
    appConfig.input.product
  )

  protected lazy val mostPopularProductDf: DataFrame = customerDf
    .transform(
      renameCustomerColumns() andThen
        addOrderData(orderDf) andThen
        getMostPopularProducts andThen
        addProductData(productDf)
    )
    .select("customerName", "productName")

  protected lazy val run: Unit = Writer(
    mostPopularProductDf,
    appConfig.output.result
  )
}

object ApplicationRunner {
  def apply(appConfig: AppConfig)(implicit spark: SparkSession): Unit = {
    new ApplicationRunner(appConfig).run
  }
}
