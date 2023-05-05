package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration.helloworld

import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration.IntegrationTestBase

class HelloWorldTest : IntegrationTestBase(){
  @Test
  fun `Hello world page reports ok`() {
    webTestClient.get()
      .uri("/api/v1/hello/world")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody().equals("Hello world!!!")
  }
}
