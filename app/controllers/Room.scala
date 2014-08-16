package controllers

import data.Data
import play.api.Play.current
import play.api.db.DB
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object Room extends Controller {

  implicit def dataSource = DB.getDataSource()

  def list = Action {
    val rooms = Data.rooms
    Ok(Json.toJson(rooms))
  }

}
