package models

import org.joda.time.DateTime

case class LogRecord(time: DateTime, sender: String, text: String)