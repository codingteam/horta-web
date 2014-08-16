package data

import java.sql.Timestamp
import javax.sql.DataSource

import models.{LogInfoModel, LogModel, RoomModel}
import org.joda.time.DateTime

import scala.slick.driver.H2Driver.simple._

object Data {

  val log = TableQuery[Log]

  def rooms(implicit dataSource: DataSource): Seq[RoomModel] = {
    Database.forDataSource(dataSource) withSession { implicit session =>
      val roomNames = for (l <- log) yield l.room
      roomNames.groupBy(identity).map(_._1).list map (roomName => RoomModel(roomName, roomName))
    }
  }

  def logs(room: String, fromDate: DateTime, toDate: DateTime)(implicit dataSource: DataSource): LogInfoModel = {
    val beginDate = toTimeStamp(fromDate)
    val endDate = toTimeStamp(toDate)
    Database.forDataSource(dataSource) withSession { implicit session =>
      val logs = for {
        l <- log.sortBy(_.time) if l.room === room && l.time > beginDate && l.time <= endDate
      } yield (l.time, l.sender, l.message)

      val logModels = logs.list map { case (time, sender, message) =>
        LogModel(new DateTime(time.getTime), sender, message)
      }
      val timestamp = logModels.lastOption.map(_.time).getOrElse(fromDate).getMillis
      LogInfoModel(timestamp, logModels)
    }
  }

  private def toTimeStamp(date: DateTime): Timestamp = {
    new Timestamp(date.getMillis)
  }

}
