package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.routes.health.health
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.routes.home.home
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.routes.users.users

fun Application.configureRouting() {
  routing {
    health()
    home()
    users()
  }
}

