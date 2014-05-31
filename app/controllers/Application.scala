package controllers

import data.Data
import org.joda.time.format.ISODateTimeFormat
import org.joda.time.{DateTime, DateTimeZone}
import play.api.db._
import play.api.mvc._
import play.api.Play.current

object Application extends Controller {

  implicit def dataSource = DB.getDataSource()

  val dateFormatter = ISODateTimeFormat.date

  def rooms = Action {
    val rooms = Data.rooms
    Ok(views.html.rooms(rooms))
  }

  def logs(room: String, startDate: Option[String] = None) = Action {
    val date = startDate match {
      case Some(date) => DateTime.parse(date, dateFormatter).toLocalDate
      case None => DateTime.now(DateTimeZone.UTC).toLocalDate
    }

    val logs = Data.logs(room, date)
    Ok(views.html.logs(logs))
  }

}