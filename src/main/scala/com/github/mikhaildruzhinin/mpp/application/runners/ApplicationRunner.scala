package com.github.mikhaildruzhinin.mpp.application.runners

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import org.apache.spark.sql.SparkSession

trait ApplicationRunner {
  def apply(appConfig: AppConfig)(implicit spark: SparkSession): Unit
}

object ApplicationRunner {
  def apply(appName: String, appConfig: AppConfig)
           (implicit spark: SparkSession): Unit = {
    appName match {
//      case "most_popular_product" => MPPApplicationRunner(appConfig)
      case "customer" => CustomerApplicationRunner(appConfig)
      case _ => throw new Exception("Invalid application name")
    }
  }
}
