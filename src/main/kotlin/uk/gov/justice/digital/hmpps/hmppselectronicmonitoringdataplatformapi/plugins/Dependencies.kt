package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repositories.DatabaseRepository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repositories.DatabaseRepositoryImplementation
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.services.DatabaseService

val appModule = module {
//  single<DatabaseRepository> { DatabaseRepositoryImplementation() }
//  single{DatabaseService(get())}
  singleOf(::DatabaseRepositoryImplementation) { bind<DatabaseRepository>() }
  singleOf(::DatabaseService)
}
