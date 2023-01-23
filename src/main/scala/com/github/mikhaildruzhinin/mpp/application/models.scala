package com.github.mikhaildruzhinin.mpp.application

object models {
  case class Customer(id: Int,
                      name: String,
                      email: String,
                      joinDate: java.sql.Date,
                      status: String)

  case class Order(customerId: Int,
                  orderId: Int,
                  productId: Int,
                  numberOfProduct: Int,
                  orderDate: java.sql.Date,
                  status: String)

  case class Product(id: Int,
                     name: String,
                     price: Double,
                     numberOfProducts: Int)
}
