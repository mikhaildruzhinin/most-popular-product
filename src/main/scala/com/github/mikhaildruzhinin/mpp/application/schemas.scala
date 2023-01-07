package com.github.mikhaildruzhinin.mpp.application

import org.apache.spark.sql.types._

object schemas {
  val customerSchema: StructType = StructType(
    Array(
      StructField("id", IntegerType),
      StructField("name", StringType),
      StructField("email", StringType),
      StructField("joinDate", DateType),
      StructField("status", StringType)
    )
  )

  val orderSchema: StructType = StructType(
    Array(
      StructField("customerID", IntegerType),
      StructField("orderID", IntegerType),
      StructField("productID", IntegerType),
      StructField("numberOfProduct", IntegerType), // кол-во товара в заказе
      StructField("orderDate", DateType),
      StructField("status", StringType)
    )
  )

  val productSchema: StructType = StructType(
    Array(
      StructField("id", IntegerType),
      StructField("name", StringType),
      StructField("price", DoubleType),
      StructField("numberOfProducts", IntegerType)
    )
  )
}
