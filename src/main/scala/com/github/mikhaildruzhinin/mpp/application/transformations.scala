package com.github.mikhaildruzhinin.mpp.application

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, max, sum}

object transformations {
  def renameCustomerColumns(): DataFrame => DataFrame = {
    df => df
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
  }

  def addOrderData(orderDf: DataFrame): DataFrame => DataFrame = {
    df => df
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
  }

  def getMostPopularProducts: DataFrame => DataFrame = {
    df => df
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
  }

  def addProductData(productDf: DataFrame): DataFrame => DataFrame = {
    df => df
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
  }
}
