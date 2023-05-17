package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.services

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repositories.DatabaseRepository

class DatabaseService(private val databaseRepository: DatabaseRepository) {
  fun initializeDatabase(): Unit = databaseRepository.initializeDatabase();
}