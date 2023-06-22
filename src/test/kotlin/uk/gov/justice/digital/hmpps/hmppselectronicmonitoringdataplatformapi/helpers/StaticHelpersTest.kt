package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class StaticHelpersTest {

  @Test
  fun `StaticHelper ValidateUUID returns true when the input is a valid UUID`() {
    val id = UUID.randomUUID().toString()
    val result = StaticHelpers().ValidateUUID(id)
    Assertions.assertThat(result).isEqualTo(true)
  }

  @Test
  fun `StaticHelper ValidateUUID returns false when the input is not a valid UUID`() {
    val id = "not a valid uuid"
    val result = StaticHelpers().ValidateUUID(id)
    Assertions.assertThat(result).isEqualTo(false)
  }
}
