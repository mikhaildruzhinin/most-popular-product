package com.github.mikhaildruzhinin.mpp

import com.github.mikhaildruzhinin.mpp.application.{ApplicationRunner, Reader}
import com.github.mikhaildruzhinin.mpp.application.config.{AppConfig, InputParams}
import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.funsuite.AnyFunSuite
import pureconfig.ConfigSource.default.loadOrThrow
import pureconfig.generic.auto._

class ApplicationRunnerSuite extends AnyFunSuite with DataFrameSuiteBase {
  test("application runner test") {
    implicit lazy val spark: SparkSession = SparkSession.getActiveSession.get
    lazy val appConfig: AppConfig = loadOrThrow[AppConfig]

    import spark.implicits._
    val expectedDf: DataFrame = Seq(
      ("John", "Apple iPhone 7"),
      ("Philip", "Apple iPhone 7"),
      ("Philip", "Apple iPhone 8"),
      ("Vasili", "Apple iPhone 7"),
      ("Anastasia", "Apple iPhone 8"),
      ("Anastasia", "Apple iPhone 7"),
      ("Robert", "Apple iPad mini 4"),
      ("Sara", "Apple AirPods")
    ).toDF("customerName", "productName")

    ApplicationRunner(appConfig)
    val resultSchema: StructType = StructType(
      Array(
        StructField("customerName", StringType),
        StructField("productName", StringType)
      )
    )
    val resultParams = InputParams(
      header = true,
      delimiter = "\t",
      path = appConfig.output.result.path
    )
    val resultDf: DataFrame = Reader(resultSchema, resultParams)

    assertDataFrameDataEquals(expectedDf, resultDf)
  }
}