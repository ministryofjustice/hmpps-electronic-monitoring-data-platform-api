package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repositories

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.tables.UsersTable

interface DatabaseRepository {
  fun initializeDatabase()
}

class DatabaseRepositoryImplementation : DatabaseRepository {
  override fun initializeDatabase() {
    transaction {
      SchemaUtils.create(UsersTable)
      UsersTable.insert {
        it[name] = "Jown"
        it[age] = 36
      }
      UsersTable.insert {
        it[name] = "Jack"
        it[age] = 38
      }
      UsersTable.insert {
        it[name] = "Jill"
        it[age] = 34
      }
    }
  }
}
