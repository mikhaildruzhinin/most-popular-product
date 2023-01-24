package com.github.mikhaildruzhinin.mpp.application.transformations

import org.apache.spark.sql.DataFrame

object CustomerTransformations extends Transformations {
  private def dummyTransformation(): DataFrame => DataFrame = df => df

  def apply(customerDf: DataFrame): DataFrame = {
    customerDf.transform(
      dummyTransformation() andThen
        dummyTransformation() andThen
        dummyTransformation()
    )
  }
}
