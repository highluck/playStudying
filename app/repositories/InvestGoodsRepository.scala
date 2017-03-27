package repositories

import javax.inject.{Inject, Singleton}

import common.MultipleDatabase
import models.values.Page.{PageRequest, PageResponse}
import models.{GoodsDetail, GoodsDetailTableDef, InvestGoods, InvestGoodsTableDef}
import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import slick.backend.DatabaseConfig

import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by 30cut on 2017. 2. 22..
  */
object InvestGoodsRepository extends MultipleDatabase{

  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  protected val dbConfig = super.dbProfile("default")//DatabaseConfig.forConfig[JdbcProfile]("default")
  //DatabaseConfigProvider.get[JdbcProfile](Play.current)
  //dbConfig.db =  Database.forConfig("default");

  var investGoodsies = TableQuery[InvestGoodsTableDef]
  var goodsDetails = TableQuery[GoodsDetailTableDef]

  def findById(id: Long): Future[Seq[(InvestGoods, GoodsDetail)]] = {
    val query = (investGoodsies join goodsDetails on (_.id === _.goodsId)).filter{
      case(i, g)  => (i.id === id)
    }
    dbConfig.db.run(query.result)
  }

  def findAllByStatus(status: String): Future[Seq[InvestGoods]] = {
    dbConfig.db.run(investGoodsies.filter(s => s.status === status).result)
  }

  def findAll(pageRequest: PageRequest): Future[PageResponse[InvestGoods]] = {
    dbConfig.db.run(investGoodsies.result)
      .map{ r =>
        PageResponse(items = r.slice(pageRequest.offset, pageRequest.offset + pageRequest.size), total = r.size)
      }
  }
}


