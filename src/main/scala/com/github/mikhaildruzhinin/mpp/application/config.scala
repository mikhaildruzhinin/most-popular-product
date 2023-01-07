package com.github.mikhaildruzhinin.mpp.application

object config {
  case class SparkConfig(appName: String)

  case class Input(header: Boolean,
                   delimiter: String,
                   path: String)

  case class Output(saveMode: String,
                    header: Boolean,
                    delimiter: String,
                    path: String)

  case class InputConfig(customer: Input,
                         order: Input,
                         product: Input)

  case class OutputConfig(result: Output)

  case class AppConfig(spark: SparkConfig,
                       input: InputConfig,
                       output: OutputConfig)
}
