package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.routes.home

import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get


fun Routing.home() {
  get("/") {
    call.respondText("Hello World!")
  }
}

