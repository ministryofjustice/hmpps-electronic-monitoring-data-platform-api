package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.home

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.server.testing.testApplication
import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.module
import kotlin.test.assertEquals

class HomeTest {

  @Test
  fun testGet() = testApplication {
    application {
      module()
    }
    client.get("/").apply {
      val response = client.get("/")
      assertEquals("Hello World!", response.bodyAsText())
    }
  }
}