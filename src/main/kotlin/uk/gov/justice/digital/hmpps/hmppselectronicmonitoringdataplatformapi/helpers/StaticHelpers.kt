package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import java.time.LocalDateTime
import java.util.*

class StaticHelpers {

  fun validateUUID(id: String): Boolean {
    try {
      UUID.fromString(id)
    } catch (e: Exception) {
      return false
    }
    return true
  }

  fun validateData(date: String): Boolean {
    try {
      LocalDateTime.parse(date)
    } catch (e: Exception) {
      return false
    }
    return true
  }
}
