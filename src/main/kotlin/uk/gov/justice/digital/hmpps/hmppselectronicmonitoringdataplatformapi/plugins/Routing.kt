package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.health.health

fun Application.configureRouting() {
  routing {
    health()
    get("/") {
      call.respondText("Hello World!")
    }
  }
}
