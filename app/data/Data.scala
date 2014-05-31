package data

import scala.slick.driver.H2Driver.simple._
import javax.sql.DataSource
import models.LogRecord
import org.joda.time.DateTime

object Data {

  val log = TableQuery[Log]

  def rooms(implicit dataSource: DataSource): Seq[String] = {
    Database.forDataSource(dataSource) withSession { implicit session =>
      val roomNames = for (l <- log) yield l.room
      roomNames.groupBy(identity).map(_._1).list
    }
  }

  def logs(room: String)(implicit dataSource: DataSource): Seq[LogRecord] = {
    Database.forDataSource(dataSource) withSession { implicit session =>
      val logs = for {
        l <- log if l.room === room
      } yield (l.time, l.sender, l.message)

      logs.list map { case (time, sender, message) =>
        LogRecord(new DateTime(time.getTime), sender, message)
      }
    }
  }

}
