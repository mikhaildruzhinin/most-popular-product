package application

import application.Schemas._
import application.Transformations._
import com.typesafe.config.Config
import org.apache.spark.sql.{DataFrame, SparkSession}

object ApplicationLauncher {
  def apply(applicationConf: Config, spark: SparkSession): Unit = {
    val customerPath: String = applicationConf.getString("input.customer")
    val customerDf: DataFrame = Reader(
      spark,
      customerSchema,
      customerPath
    )

    val orderPath: String = applicationConf.getString("input.order")
    val orderDf: DataFrame = Reader(
      spark,
      orderSchema,
      orderPath
    )

    val productPath: String = applicationConf.getString("input.product")
    val productDf: DataFrame = Reader(
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
    Writer(mostPopularProductDf, outputPath)
  }
}
