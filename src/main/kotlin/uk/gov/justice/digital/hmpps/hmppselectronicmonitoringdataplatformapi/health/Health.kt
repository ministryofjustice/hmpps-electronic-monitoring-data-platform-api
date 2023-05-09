package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.health

import Class.Project.Project
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import java.io.File

fun Routing.health() {
  get("/health") {
    val mapper = jacksonObjectMapper()
    val internalPropsFile = File("src/Properties.json").readText(Charsets.UTF_8)
    val internalProps = mapper.readValue<Project>(internalPropsFile)
    val appInsightsDevFile = File("applicationinsights.dev.json").readText(Charsets.UTF_8)
    val appInsightsDev = mapper.readValue<Any>(appInsightsDevFile)
    call.respondText("I am alive, internal version is ${internalProps.version} and build pipeline data is \n $appInsightsDev")
  }
}
