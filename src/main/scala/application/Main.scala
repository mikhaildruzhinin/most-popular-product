package application

import application.Schemas.{customerSchema, orderSchema, productSchema}
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, max, row_number, sum}
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

    val customerDf: DataFrame = Reader.read(
      spark,
      customerSchema,
      "src/main/resources/customer.csv"
    )

    val orderDf: DataFrame = Reader.read(
      spark,
      orderSchema,
      "src/main/resources/order.csv"
    )

    val productDf: DataFrame = Reader.read(
      spark,
      productSchema,
      "src/main/resources/product.csv"
    )

    val mostPopularProductDf: DataFrame = customerDf
      .withColumnRenamed(
        "id",
        "customerID"
      )
      .withColumnRenamed(
        "status",
        "customerStatus"
      )
      .withColumnRenamed(
        "name",
        "customerName"
      )
      .join(
        orderDf
          .withColumnRenamed(
            "status",
            "orderStatus"
          )
          .filter(col("orderStatus") === "delivered"),
        Seq("customerID"),
        "left"
      )
      .groupBy(
        "customerID", "customerName", "productID"
      )
      .agg(
        sum(col("numberOfProduct")).as("numberOfProduct")
      )
      .withColumn(
        "maxNumberForCustomer",
        max("numberOfProduct").over(
          Window
            .partitionBy("customerID")
            .orderBy("customerID")
        )
      )
      .filter(col("numberOfProduct") === col("maxNumberForCustomer"))
      .join(
        productDf
          .withColumnRenamed(
            "id",
            "productID"
          )
          .withColumnRenamed(
            "name",
            "productName"
          ),
        Seq("productID"),
        "left"
      )
      .select("customerName", "productName")

    mostPopularProductDf.show()
  }
}
