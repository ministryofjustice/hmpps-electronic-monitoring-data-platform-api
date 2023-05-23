

package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration.controller

import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration.IntegrationTestBase

class HelloWorldTest(): IntegrationTestBase() {
  @Test
  fun `test hello endpoint`() {
    webTestClient.get()
      .uri("/api/v1/hello/world")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody(String::class.java)
      .isEqualTo<Nothing>("Hello World!!!")
  }
}