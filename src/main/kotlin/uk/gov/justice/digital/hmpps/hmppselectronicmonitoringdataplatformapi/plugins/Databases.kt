package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.inject
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.services.DatabaseService

fun Application.configureDatabases(): Int {
  val databaseService: DatabaseService by inject()
  // h2 connection
  // Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1", "org.h2.Driver")
  // docker connection
  // Database.connect("jdbc:postgresql://database:5432/",driver = "org.postgresql.Driver",user = "postgres", password = "root")
  Database.connect(
    System.getenv("EM_DATABASE_CONNECTION"),
    System.getenv("EM_DATABASE_DRIVER"),
    System.getenv("EM_DATABASE_USER"),
    System.getenv("EM_DATABASE_PASSWORD"),
  )
  if (System.getenv("EM_DATABASE_DRIVER") == "org.h2.Driver") {
    databaseService.initializeDatabase()
  }

  return 1
}
