package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import java.util.*

class StaticHelpers {

  fun ValidateUUID(id: String): Boolean {
    try {
      UUID.fromString(id)
    } catch (e: Exception) {
      return false
    }
    return true
  }
}
