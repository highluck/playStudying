package controllers


import javax.inject.{Inject, Singleton}

import models.values.Page.PageRequest
import play.api.libs.json.Json
import play.api.mvc._
import services.InvestGoodsService

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by 30cut on 2017. 2. 22..
  */

object InvestGoodsController extends Controller {

  def findAll(page:Int, size:Int) = Action.async { implicit request =>
    InvestGoodsService.findAll(new PageRequest(page, size)) map { goods =>
      Ok(Json.toJson(goods))
    }
  }

  def findById(id:Long) = Action.async { implicit request =>
    InvestGoodsService.findById(id) map { goods =>
      Ok(Json.toJson(goods))
    }
  }
}
