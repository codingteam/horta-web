package controllers

import data.Data
import models.LogRecord
import org.joda.time.{DateTime, DateTimeZone}
import play.api.db.DB
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import play.api.Play.current

object Log extends Controller {

  implicit def dataSource = DB.getDataSource()

  implicit val logWrites = Json.writes[LogRecord]

  def roomLogs(roomId: String) = Action {
    val date = DateTime.now(DateTimeZone.UTC).toLocalDate
    val logs = Data.logs(roomId, date)
    Ok(Json.toJson(logs))
  }

  def roomLogTail(roomId: String, timestamp: Long) = Action {
    // TODO: implement this
    Ok("")
  }

}
