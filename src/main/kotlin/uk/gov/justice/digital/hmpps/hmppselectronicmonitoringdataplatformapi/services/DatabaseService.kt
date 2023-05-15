package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.services

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.tables.Users

class DatabaseService {
  fun initializeDatabase() {
    transaction {
      SchemaUtils.create(Users);
      Users.insert {
        it[name] = "Jown"
        it[age] = 36
      }
      Users.insert {
        it[name] = "Jack"
        it[age] = 38
      }
      Users.insert {
        it[name] = "Jill"
        it[age] = 34
      }
    }
  }
}
