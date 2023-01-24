package com.github.mikhaildruzhinin.mpp.application.runners

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import org.apache.spark.sql.SparkSession

trait ApplicationRunner {
  def apply()(implicit appConfig: AppConfig, spark: SparkSession): Unit
}

object ApplicationRunner {
  def apply()(implicit appConfig: AppConfig, spark: SparkSession): Unit = {
    appConfig.appName match {
//      case "most_popular_product" => MPPApplicationRunner()
      case "customer" => CustomerApplicationRunner()
      case _ => throw new Exception("Invalid application name")
    }
  }
}
