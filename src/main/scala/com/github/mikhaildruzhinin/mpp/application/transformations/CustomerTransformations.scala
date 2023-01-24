package com.github.mikhaildruzhinin.mpp.application.transformations

import org.apache.spark.sql.Dataset

object CustomerTransformations extends Transformations {
  def dummyTransformation[T](): Dataset[T] => Dataset[T] = ds => ds
}
