package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.health

import Class.Project.Project
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Routing.health() {
  get("/api/v1/hello/world") {
    val mapper = jacksonObjectMapper()
    val file = File("src/Properties.json").readText(Charsets.UTF_8)
    val project = mapper.readValue<Project>(file)
    call.respondText("I am alive, version is = ${project.version}")
  }
}
