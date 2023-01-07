package com.github.mikhaildruzhinin.mpp

import com.github.mikhaildruzhinin.mpp.application.ApplicationRunner
import com.github.mikhaildruzhinin.mpp.application.config.AppConfig
import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.funsuite.AnyFunSuite
import pureconfig.ConfigSource.default.loadOrThrow
import pureconfig.generic.auto._

class ApplicationRunnerSuite extends AnyFunSuite with DataFrameSuiteBase {
  test("application runner test") {
    implicit lazy val spark: SparkSession = SparkSession.getActiveSession.get
    lazy val appConfig: AppConfig = loadOrThrow[AppConfig]

    import spark.implicits._
    val expectedResultDf: DataFrame = Seq(
      ("John", "Apple iPhone 7"),
      ("Philip", "Apple iPhone 7"),
      ("Philip", "Apple iPhone 8"),
      ("Vasili", "Apple iPhone 7"),
      ("Anastasia", "Apple iPhone 8"),
      ("Anastasia", "Apple iPhone 7"),
      ("Robert", "Apple iPad mini 4"),
      ("Sara", "Apple AirPods")
    ).toDF("customerName", "productName")

    class TestApplicationRunner(appConfig: AppConfig)(implicit spark: SparkSession)
      extends ApplicationRunner(appConfig: AppConfig) {

      override protected lazy val run: Unit = assertDataFrameEquals(
        expectedResultDf,
        mostPopularProductDf
      )
    }

    object TestApplicationRunner {
      def apply(appConfig: AppConfig)(implicit spark: SparkSession): Unit = {
        new TestApplicationRunner(appConfig).run
      }
    }

    TestApplicationRunner(appConfig)
  }
}