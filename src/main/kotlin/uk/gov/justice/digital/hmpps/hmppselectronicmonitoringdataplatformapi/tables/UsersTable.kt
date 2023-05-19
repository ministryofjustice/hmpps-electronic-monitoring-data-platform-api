package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.classes.User

object UsersTable : Table() {
  val id: Column<Int> = integer("id").autoIncrement()
  val name: Column<String> = varchar("name", 255)
  val age: Column<Int> = integer("age")
  override val primaryKey = PrimaryKey(id, name = "PK_USER_ID")

  fun toUser(row: ResultRow): User =
    User(
      id = row[id],
      name = row[name],
      age = row[age],
    )
}
