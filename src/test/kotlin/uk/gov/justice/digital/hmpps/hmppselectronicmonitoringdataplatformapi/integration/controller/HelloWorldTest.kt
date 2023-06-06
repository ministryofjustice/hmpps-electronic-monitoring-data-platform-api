

package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration.controller

import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration.IntegrationTestBase

class HelloWorldTest : IntegrationTestBase() {
  @Test
  fun `test hello endpoint`() {
    webTestClient.get()
      .uri("/hello/v1")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody(String::class.java)
      .isEqualTo<Nothing>("Hello World!!!")
  }
}
