package com.github.mikhaildruzhinin.mpp.application

object config {
  case class SparkConfig(appName: String)

  case class SourceParams(header: Boolean,
                         delimiter: String,
                         path: String)

  case class SinkParams(saveMode: String,
                        header: Boolean,
                        delimiter: String,
                        path: String)

  case class SourceConfig(customer: SourceParams,
                          order: SourceParams,
                          product: SourceParams)

  case class SinkConfig(result: SinkParams)

  case class AppConfig(spark: SparkConfig,
                       source: SourceConfig,
                       sink: SinkConfig)
}
