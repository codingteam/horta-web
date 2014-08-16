package controllers

import data.Data
import org.joda.time.{DateTime, DateTimeZone}
import play.api.Play.current
import play.api.db.DB
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object Log extends Controller {

  implicit def dataSource = DB.getDataSource()

  def roomLogs(roomId: String) = Action {
    val fromDate = startOfToday
    val toDate = fromDate.plusDays(1)
    val info = Data.logs(roomId, fromDate, toDate)
    Ok(Json.toJson(info))
  }

  def roomLogTail(roomId: String, timestamp: Long) = Action {
    val fromDate = new DateTime(timestamp)
    val toDate = startOfToday.plusDays(1)
    val info = Data.logs(roomId, fromDate, toDate)
    Ok(Json.toJson(info))
  }

  private def startOfToday = DateTime.now(DateTimeZone.UTC).withTimeAtStartOfDay()

}
