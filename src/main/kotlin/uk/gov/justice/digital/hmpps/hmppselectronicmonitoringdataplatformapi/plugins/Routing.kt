package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.health.health
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.health.home

fun Application.configureRouting() {
  routing {
    health()
    home()
  }
}
