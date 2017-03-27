package models

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User
import play.api.libs.functional.syntax.unlift
import play.api.libs.json.{Format, JsValue, Json, Writes}
import play.api.libs.json._
import play.api.libs.functional.syntax._
/**
  * Created by 30cut on 2017. 3. 9..
  */

/*
implicit lazy val userReads: Reads[User] = (
(__ \ "name").read[String] and
(__ \ "friends").lazyRead(Reads.seq[User](userReads))
)(User)

implicit lazy val userWrites: Writes[User] = (
(__ \ "name").write[String] and
(__ \ "friends").lazyWrite(Writes.seq[User](userWrites))
)(unlift(User.unapply))
*/

case class FindaGoodsShare(productName: String, productCode: String, productCategory: String, yearRate: String
                           , yearRateType: String, yearRateMin: String = "0", yearRateMax: String = "0"
                           , investMonth: String, investRate: Int, returnType: String, reserveMonth: String = "0"
                           , returnDate :String, productGrade: String = "0", creditAgent: String = "0"
                           , creditGrade: String ="0", probability: String = "0", status: String, pcUrl:String
                           , mobileUrl: String, desc:String, investProtect:String, isPortfolio:String
                           , portfolioSubcount: String, exInvestPrice:String, exRepaymentSchedule:String) {
}

object FindaGoodsShare{

  implicit lazy val reads: Reads[FindaGoodsShare] = Json.reads[FindaGoodsShare]

  implicit lazy val writes =  new Writes[FindaGoodsShare] {
    def writes(item: FindaGoodsShare) = Json.obj(
      "product_name" -> item.productName,
      "product_code" -> item.productCode,
      "product_category" -> item.productCategory,
      "year_rate" -> item.yearRate,
      "year_rate_type" -> item.yearRateType,
      "year_rate_max" -> item.yearRateMin,
      "year_rate_min" -> item.yearRateMax,
      "invest_month" -> item.investMonth,
      "invest_rate" -> item.investRate,
      "return_type" -> item.returnType,
      "reserve_month" -> item.reserveMonth,
      "return_date" -> item.returnDate,
      "product_grade" -> item.productGrade,
      "credit_agent" -> item.creditAgent,
      "credit_grade" -> item.creditGrade,
      "probability" -> item.probability,
      "status" -> item.status,
      "pc_url" -> item.pcUrl,
      "mobile_url" -> item.mobileUrl,
      "desc" -> item.desc,
      "invest_protect" -> item.investProtect,
      "is_portfolio" -> item.isPortfolio,
      "portfolio_subcount" -> item.portfolioSubcount,
      "ex_invest_price" -> item.exInvestPrice,
      "ex_repayment_schedule" -> item.exRepaymentSchedule
    )
  }
}

object Finda {
  case class FindaResponse(data: Seq[FindaGoodsShare], result: String)

  implicit def findaResponseFormat: Format[FindaResponse] =
    ((__ \ "data").format[Seq[FindaGoodsShare]] ~
      (__ \ "result").format[String])(FindaResponse.apply _, unlift(FindaResponse.unapply))
}