package models

import scala.slick.driver.H2Driver.simple._
import java.sql.Timestamp

class Log(tag: Tag) extends Table[(Int, Timestamp, String, String, String, String)](tag, "LOG") {
  def id = column[Int]("ID", O.PrimaryKey)
  def time = column[Timestamp]("TIME")
  def room = column[String]("ROOM")
  def sender = column[String]("SENDER")
  def typeName = column[String]("TYPE")
  def message = column[String]("MESSAGE")

  def * = (id, time, room, sender, typeName, message)
}
