package models

import play.api.libs.json.Json

case class LogInfoModel(timestamp: Long, logs: Seq[LogModel])

object LogInfoModel {

  implicit val logInfoWrites = Json.writes[LogInfoModel]

}
