package models

import play.api.libs.json.Json

case class RoomModel(id: String, name: String)

object RoomModel {
  implicit val roomWrites = Json.writes[RoomModel]
}