package com.github.mikhaildruzhinin.mpp.application

object config {
  case class SparkConfig(appName: String)

  case class InputParams(header: Boolean,
                         delimiter: String,
                         path: String)

  case class OutputParams(saveMode: String,
                          header: Boolean,
                          delimiter: String,
                          path: String)

  case class InputConfig(customer: InputParams,
                         order: InputParams,
                         product: InputParams)

  case class OutputConfig(result: OutputParams)

  case class AppConfig(spark: SparkConfig,
                       input: InputConfig,
                       output: OutputConfig)
}
