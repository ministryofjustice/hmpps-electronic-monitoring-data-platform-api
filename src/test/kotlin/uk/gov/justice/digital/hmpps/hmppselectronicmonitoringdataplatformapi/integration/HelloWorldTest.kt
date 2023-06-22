package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration

import org.junit.jupiter.api.Test

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
