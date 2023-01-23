//package com.github.mikhaildruzhinin.mpp.application.runners
//
//import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
//import com.github.mikhaildruzhinin.mpp.application.schemas._
//import com.github.mikhaildruzhinin.mpp.application.transformations._
//import com.github.mikhaildruzhinin.mpp.application.io.{Reader, Writer}
//import org.apache.spark.sql.{DataFrame, SparkSession}
//
//class MPPApplicationRunner(appConfig: AppConfig)(implicit spark: SparkSession)
//  extends ApplicationRunner {
//
//  protected lazy val customerDf: DataFrame = Reader(
//    customerSchema,
//    appConfig.source.customer
//  )
//
//  protected lazy val orderDf: DataFrame = Reader(
//    orderSchema,
//    appConfig.source.order
//  )
//
//  protected lazy val productDf: DataFrame = Reader(
//    productSchema,
//    appConfig.source.product
//  )
//
//  protected lazy val mostPopularProductDf: DataFrame = customerDf
//    .transform(
//      renameCustomerColumns() andThen
//        addOrderData(orderDf) andThen
//        getMostPopularProducts andThen
//        addProductData(productDf)
//    )
//    .select("customerName", "productName")
//
//  lazy val run: Unit = Writer(
//    mostPopularProductDf,
//    appConfig.sink.result
//  )
//}
//
//object MPPApplicationRunner {
//  def apply(appConfig: AppConfig)(implicit spark: SparkSession): MPPApplicationRunner = {
//    new MPPApplicationRunner(appConfig)
//  }
//}
