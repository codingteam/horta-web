package controllers

import data.Data
import play.api.db._
import play.api.mvc._
import play.api.Play.current

object Application extends Controller {

  implicit def dataSource = DB.getDataSource()

  def rooms = Action {
    val rooms = Data.rooms
    Ok(views.html.rooms(rooms))
  }

  def logs(room: String) = Action {
    val logs = Data.logs(room)
    Ok(views.html.logs(logs))
  }

}