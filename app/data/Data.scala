package data

import javax.sql.DataSource
import models.{RoomModel, LogRecord}
import org.joda.time.{DateTime, LocalDate, LocalTime}
import scala.slick.driver.H2Driver.simple._
import java.sql.Timestamp

object Data {

  val log = TableQuery[Log]

  def rooms(implicit dataSource: DataSource): Seq[RoomModel] = {
    Database.forDataSource(dataSource) withSession { implicit session =>
      val roomNames = for (l <- log) yield l.room
      roomNames.groupBy(identity).map(_._1).list map (roomName => RoomModel(roomName, roomName))
    }
  }

  def logs(room: String, date: LocalDate)(implicit dataSource: DataSource): Seq[LogRecord] = {
    val beginDate = toTimeStamp(date)
    val endDate = toTimeStamp(date.plusDays(1))
    Database.forDataSource(dataSource) withSession { implicit session =>
      val logs = for {
        l <- log if l.room === room && l.time >= beginDate && l.time < endDate
      } yield (l.time, l.sender, l.message)

      logs.list map { case (time, sender, message) =>
        LogRecord(new DateTime(time.getTime), sender, message)
      }
    }
  }

  private def toTimeStamp(date: LocalDate): Timestamp = {
    new Timestamp(date.toDateTime(LocalTime.MIDNIGHT).getMillis)
  }

}
