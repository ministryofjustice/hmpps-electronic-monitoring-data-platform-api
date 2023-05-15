package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins

import io.ktor.serialization.gson.gson
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun Application.configureSerialization() {
  install(ContentNegotiation) {
    gson {
    }
    json()
  }
//    routing {
//        get("/json/gson") {
//            call.respond(mapOf("hello" to "world"))
//        }
//        get("/json/kotlinx-serialization") {
//            call.respond(mapOf("hello" to "world"))
//        }
//    }
}

