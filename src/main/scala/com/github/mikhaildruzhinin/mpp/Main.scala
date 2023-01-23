package com.github.mikhaildruzhinin.mpp

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import com.github.mikhaildruzhinin.mpp.application.runners.ApplicationRunner
import org.apache.spark.sql.SparkSession
import pureconfig.ConfigSource.default.loadOrThrow
import pureconfig.generic.auto._

object Main {
  def main(args: Array[String]): Unit = {
    lazy val appConfig: AppConfig = loadOrThrow[AppConfig]

    implicit lazy val spark: SparkSession = SparkSession
      .builder()
      .appName(appConfig.spark.appName)
      .getOrCreate()

      ApplicationRunner(appConfig.spark.appName, appConfig)
  }
}
