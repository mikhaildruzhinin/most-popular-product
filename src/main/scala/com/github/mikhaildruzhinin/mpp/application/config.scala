package com.github.mikhaildruzhinin.mpp.application

object config {
  case class SourceParams(header: Boolean,
                         delimiter: String,
                         path: String)

  case class SourceConfig(customer: SourceParams,
                          order: SourceParams,
                          product: SourceParams)

  case class SinkParams(saveMode: String,
                        header: Boolean,
                        delimiter: String,
                        path: String)

  case class SinkConfig(result: SinkParams)

  case class AppConfig(appName: String,
                       source: SourceConfig,
                       sink: SinkConfig)
}
