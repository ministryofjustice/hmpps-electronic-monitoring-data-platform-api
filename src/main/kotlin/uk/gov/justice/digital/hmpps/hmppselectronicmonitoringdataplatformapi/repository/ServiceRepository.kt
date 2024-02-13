package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import java.sql.DriverManager
import java.sql.ResultSet

class ServiceRepository {
  val jdbcUrl: String = ""
  val user: String = ""
  val password: String = ""
  val connection = DriverManager.getConnection(jdbcUrl, user, password)
  val table: String = "service"


  fun getData(fieldList: String = "*", criteria: String = ""): ResultSet? {
    val whereClause: String = if (criteria != "") "" else "WHERE $criteria"
    val sqlQuery: String = "SELECT $fieldList FROM $table $whereClause"

    val query = connection.prepareStatement(fieldList)

    return query.executeQuery()
  }
}