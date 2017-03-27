package services

import akka.actor.FSM.Failure
import akka.actor.Status.Success
import models.{FindaGoodsShare, InvestGoods}
import models.values.Page.PageRequest
import repositories.InvestGoodsRepository

import scala.concurrent.Await

/**
  * Created by 30cut on 2017. 3. 9..
  */
object GoodsShareService {

  def findaFindAll(): Unit = {
    //val goods = result(InvestGoodsRepository.findAllByStatus("Y"),0);
  }
}
