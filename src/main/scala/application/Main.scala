package application

import application.Schemas.{customerSchema, orderSchema, productSchema}
import application.Transformations._
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.{DataFrame, SparkSession}

object Main {
  def main(args: Array[String]): Unit = {
    val applicationConf: Config = ConfigFactory.load()

    val appName: String = applicationConf.getString("spark.appname")
    val spark: SparkSession = SparkSession
      .builder()
      .appName(appName)
      .config("spark.master", "local")
      .getOrCreate()

    val customerPath: String = applicationConf.getString("input.customer")
    val customerDf: DataFrame = Reader.read(
      spark,
      customerSchema,
      customerPath
    )

    val orderPath: String = applicationConf.getString("input.order")
    val orderDf: DataFrame = Reader.read(
      spark,
      orderSchema,
      orderPath
    )

    val productPath: String = applicationConf.getString("input.product")
    val productDf: DataFrame = Reader.read(
      spark,
      productSchema,
      productPath
    )

    val mostPopularProductDf: DataFrame = customerDf
      .transform(
        renameCustomerColumns() andThen
          addOrderData(orderDf) andThen
          getMostPopularProducts andThen
          addProductData(productDf)
      )
      .select("customerName", "productName")

    val outputPath: String = applicationConf.getString("output.result")
    Writer.write(mostPopularProductDf, outputPath)
  }
}
