package com.github.mikhaildruzhinin.mpp.application.runners

import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import com.github.mikhaildruzhinin.mpp.application.io.{Reader, StdOutWriter}
import com.github.mikhaildruzhinin.mpp.application.schemas.customerSchema
import org.apache.spark.sql.{DataFrame, SparkSession}

object CustomerApplicationRunner extends ApplicationRunner {
  override def apply(appConfig: AppConfig)(implicit spark: SparkSession): Unit = {
    lazy val customerDf: DataFrame = Reader(customerSchema, appConfig.source.customer)
    StdOutWriter(customerDf)
  }
}

