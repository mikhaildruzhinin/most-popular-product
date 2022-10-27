package application

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    val applicationConf: Config = ConfigFactory.load()

    val appName: String = applicationConf.getString("spark.appname")
    val spark: SparkSession = SparkSession
      .builder()
      .appName(appName)
      .config("spark.master", "local")
      .getOrCreate()
  }
}
