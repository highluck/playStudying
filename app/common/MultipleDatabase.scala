package common

import play.api.Play
import slick.backend.DatabaseConfig
import slick.driver.H2Driver
import slick.driver.JdbcProfile
/**
  * Created by 30cut on 2017. 2. 24..
  */
trait MultipleDatabase {
  def dbProfile(db :String)= {
    db match {
      case "service" =>  DatabaseConfig.forConfig[JdbcProfile]("service")
      case _ =>  DatabaseConfig.forConfig[JdbcProfile]("default")
    }
  }
}
