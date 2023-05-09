package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi

// import org.springframework.boot.autoconfigure.SpringBootApplication
// import org.springframework.boot.runApplication
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.util.*
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.plugins.configureRouting

// @SpringBootApplication()
// class HmppsElectronicMonitoringDataPlatformApi
//
// fun main(args: Array<String>) {
//  runApplication<HmppsElectronicMonitoringDataPlatformApi>(*args)
// }

fun main() {
  embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
    .start(wait = true)
  val properties = Properties()
  properties.load(Application::class.java.getResourceAsStream("/version.properties"))
  println(properties.getProperty("version"))
  println("cheese")
}

fun Application.module() {
  configureRouting()
}
