package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.health.health

fun Application.configureRouting() {
    routing {
      health()


        get("/") {
            call.respondText("Hello Worldssss!")
        }
    }
}
