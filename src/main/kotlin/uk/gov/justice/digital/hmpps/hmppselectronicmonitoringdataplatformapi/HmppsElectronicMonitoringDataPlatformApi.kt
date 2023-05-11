package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi

import com.apurebase.kgraphql.GraphQL
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins.configureDatabases
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins.configureRouting
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins.configureSerialization
import java.util.*

fun main() {
  embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
    .start(wait = true)
  val properties = Properties()
  properties.load(Application::class.java.getResourceAsStream("/version.properties"))
  println(properties.getProperty("version"))
}

fun Application.module() {
//  try{
  configureDatabases()
//  }
//  catch(e:Exception){
//    println(e.message)
//  }
  configureRouting()
  configureSerialization()
}
