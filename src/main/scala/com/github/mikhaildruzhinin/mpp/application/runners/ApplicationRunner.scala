package com.github.mikhaildruzhinin.mpp.application.runners

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import org.apache.spark.sql.SparkSession

trait ApplicationRunner {
   val run: Unit
}

object ApplicationRunner {
  def apply(appName: String, appConfig: AppConfig)
           (implicit spark: SparkSession): ApplicationRunner = {
    appName match {
      case "most_popular_product" => MPPApplicationRunner(appConfig)
      case _ => throw new Exception("Invalid application name")
    }
  }
}
