package services

import javax.inject.Inject

import models.{GoodsDetail, InvestGoods}
import models.values.Page.{PageRequest, PageResponse}
import repositories.InvestGoodsRepository
import slick.dbio.Effect.Transactional

import scala.concurrent.Future

/**
  * Created by 30cut on 2017. 2. 22..
  */
object InvestGoodsService {

  def findById(id: Long): Future[Seq[(InvestGoods, GoodsDetail)]] = {
    InvestGoodsRepository.findById(id)
  }

  def findAll(pageRequest: PageRequest): Future[PageResponse[InvestGoods]] = {
    InvestGoodsRepository.findAll(pageRequest)
  }
}
