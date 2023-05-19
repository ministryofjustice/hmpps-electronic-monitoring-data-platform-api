package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration.controller

import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.http.HttpStatus
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration.IntegrationTestBase

class HelloWorldTest : IntegrationTestBase() {
  @Test
  fun testHelloWorldController() {
    val result = testRestTemplate.getForEntity("/api/v1/hello/world", String::class.java)
    assertNotNull(result)
    assertEquals(result.statusCode, HttpStatus.OK)
    assertEquals(result.body, "Hello World!!!")
  }
}
