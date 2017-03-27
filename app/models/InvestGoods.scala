package models

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime

import common.timestampFormat
import org.joda.time.DateTime
import play.api.libs.json._

import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by 30cut on 2017. 2. 22..
  */
case class InvestGoods (id: Long, title: String, content: String, ratio: Float, loanDue: Int
                        , investTotalMoney: Long, openTimestamp: Option[Timestamp], exitTimestamp: Option[Timestamp]
                        , finalMoney: Long, status: String, minMoney: Long, returnDay: String
                        , thumbnailUrl: String, comment: String){}

object InvestGoods {

  def tupled = (InvestGoods.apply _).tupled
  implicit val timestamp = timestampFormat
  implicit val jsonFormat = Json.format[InvestGoods];

  implicit val writer = new Writes[(InvestGoods, GoodsDetail)] {
    def writes(t: (InvestGoods, GoodsDetail)): JsValue = {
      Json.obj("InvestGodds" -> t._1 , "GoodsDetail" -> t._2)
    }
  }

  /*
  implicit val writes = new Writes[InvestGoods] {
    def writes(goods: InvestGoods): JsValue = {
      Json.obj(
        "id" -> goods.id
        , "title" -> goods.title
        , "content" -> goods.content
        , "ratio" -> goods.ratio
        , "loanDue" -> goods.loanDue
        , "investTotalMoney" -> goods.investTotalMoney
        , "openTimestamp" -> goods.openTimestamp
        , "exitTimestamp" -> goods.exitTimestamp
        , "finalMoney" -> goods.finalMoney
        , "status" -> goods.status
        , "minMoney" -> goods.minMoney
        , "returnDay" -> goods.returnDay
        , "thumbnailUrl" -> goods.thumbnailUrl
        , "comment" -> goods.comment
      )
    }
  }

  implicit val reads = Json.reads[InvestGoods]
  */
}

class InvestGoodsTableDef(tag: Tag) extends Table[InvestGoods](tag, "invest_goods") {

  def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
  def title = column[String]("title")
  def content = column[String]("content")
  def ratio = column[Float]("ratio")
  def loanDue = column[Int]("loan_due")
  def investTotalMoney = column[Long]("invest_total_money")
  def openTimestamp = column[Option[Timestamp]]("open_timestamp")
  def exitTimestamp = column[Option[Timestamp]]("exit_timestamp")
  def finalMoney = column[Long]("final_money")
  def status = column[String]("status")
  def minMoney = column[Long]("min_money")
  def returnDay = column[String]("return_day")
  def thumbnailUrl = column[String]("thumbnail_url")
  def comment =column[String]("reserv_comment")

  override def * = (id, title, content, ratio, loanDue, investTotalMoney, openTimestamp, exitTimestamp
    ,finalMoney, status, minMoney, returnDay, thumbnailUrl, comment) <>(InvestGoods.tupled, InvestGoods.unapply _)
}
