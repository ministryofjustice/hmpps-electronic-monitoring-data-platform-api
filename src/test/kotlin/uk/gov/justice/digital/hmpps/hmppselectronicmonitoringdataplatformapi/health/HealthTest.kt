package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.health

import Class.Project.Project
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.server.testing.testApplication
import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.module
import java.io.File
import kotlin.test.assertContains

class HealthTest {

  @Test
  fun testGetHealth() = testApplication {
    application {
      module()

    }
    val mapper = jacksonObjectMapper()
    val internalPropsFile = File("src/Properties.json").readText(Charsets.UTF_8)
    val internalProps = mapper.readValue<Project>(internalPropsFile)
    val response = client.get("/health")
    response.bodyAsText()
    assertContains(
      response.bodyAsText(),
      "I am alive, internal version is ${internalProps.version} and build pipeline data is",
    )
    assertContains(response.bodyAsText(), "role={name=hmpps-electronic-monitoring-data-platform-api}")
  }
}
