package models.values

import play.api.libs.json.{Format, JsValue, Json, Writes}
import play.api.libs.json._
import play.api.libs.functional.syntax._


/**
  * Created by 30cut on 2017. 2. 23..
  */

object Page {
  case class PageRequest(
                      page: Int = 1,
                      size: Int = 10) {
    def offset = (page - 1) * size
  }

  case class PageResponse[T](items: Seq[(T)], total: Long)

  implicit def pageResponseFormat[T: Format]: Format[PageResponse[T]] =
    ((__ \ "items").format[Seq[T]] ~
      (__ \ "total").format[Long])(PageResponse.apply, unlift(PageResponse.unapply))
}
