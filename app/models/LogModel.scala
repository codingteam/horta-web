package models

import org.joda.time.DateTime
import play.api.libs.json.Json

case class LogModel(time: DateTime, sender: String, text: String)

object LogModel {

  implicit val logWrites = Json.writes[LogModel]

}