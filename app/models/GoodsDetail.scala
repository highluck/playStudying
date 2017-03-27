package models

import java.sql.Timestamp
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime

import common.timestampFormat
import org.joda.time.DateTime
import play.api.libs.json._
import repositories.InvestGoodsRepository

import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by 30cut on 2017. 2. 23..
  */

case class GoodsDetail (goodsId:Long, totalContent:String, detailStatus:String, qaStatus:String,
                       recommendationStatus:String, pointStatus:String, notice:String, notice2:String)

object GoodsDetail{
  def tupled = (GoodsDetail.apply _).tupled
  implicit val jsonFormat = Json.format[GoodsDetail];
}

class GoodsDetailTableDef(tag: Tag) extends Table[GoodsDetail](tag, "goods_detail") {

  def goodsId = column[Long]("goods_id", O.PrimaryKey,O.AutoInc)
  def totalContent = column[String]("total_content")
  def detailStatus = column[String]("detail_status")
  def qaStatus = column[String]("qa_status")
  def recommendationStatus = column[String]("recommendation_status")
  def pointStatus = column[String]("point_status")
  def notice = column[String]("notice")
  def notice2 = column[String]("notice2")

  override def * = (goodsId, totalContent, detailStatus, qaStatus, recommendationStatus
    , pointStatus, notice, notice2) <>(GoodsDetail.tupled, GoodsDetail.unapply _)

  def investGoods = foreignKey("invest_goods",goodsId,InvestGoodsRepository.investGoodsies)(_.id)
}

