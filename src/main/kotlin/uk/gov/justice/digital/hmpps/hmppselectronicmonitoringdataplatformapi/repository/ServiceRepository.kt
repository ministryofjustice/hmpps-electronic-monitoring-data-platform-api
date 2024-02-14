package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import java.sql.ResultSet

interface ServiceRepository {
  fun getData(fieldList: String = "*", criteria: String = ""): ResultSet?
  fun runDefaultQuery(): ResultSet?
}
