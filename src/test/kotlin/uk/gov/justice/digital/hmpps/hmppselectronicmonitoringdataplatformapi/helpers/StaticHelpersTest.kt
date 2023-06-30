package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class StaticHelpersTest {

  @Test
  fun `StaticHelper ValidateUUID returns true when the input is a valid UUID`() {
    val id = "cf124f91-a5f2-41bc-840c-5a3e7fcca0ac"
    val result = StaticHelpers().validateUUID(id)
    Assertions.assertThat(result).isEqualTo(true)
  }

  @Test
  fun `StaticHelper ValidateUUID returns false when the input is not a valid UUID`() {
    val id = "not a valid uuid"
    val result = StaticHelpers().validateUUID(id)
    Assertions.assertThat(result).isEqualTo(false)
  }
}
