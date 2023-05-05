package com.example.plugins

import com.example.pages.hellowWorld
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.boot.info.BuildProperties
import org.springframework.stereotype.Component

fun Application.configureRouting() {
  routing {
    hellowWorld()


  }
}


@Component
class HealthInfo(buildProperties: BuildProperties) : HealthIndicator {

  val version: String = buildProperties.version

  override fun health(): Health = Health.up().withDetail("version", version).build()
}


