package models

import scala.slick.driver.H2Driver.simple._
import javax.sql.DataSource

object Data {
  val log = TableQuery[Log]

  def rooms(dataSource: DataSource): Seq[String] = {
    Database.forDataSource(dataSource) withSession { implicit session =>
      val roomNames = for (l <- log) yield l.room
      roomNames.groupBy(identity).map(_._1).list
    }
  }
}
