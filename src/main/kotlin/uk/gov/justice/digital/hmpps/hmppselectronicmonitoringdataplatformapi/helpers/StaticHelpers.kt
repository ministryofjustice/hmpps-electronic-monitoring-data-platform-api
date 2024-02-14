package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
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

  fun isValidISODateTime(date: String?): Boolean {
    return try {
      Instant.from(DateTimeFormatter.ISO_INSTANT.parse(date))
      true
    } catch (e: DateTimeParseException) {
      false
    }
  }

  fun validateDuration(duration: Int): Boolean {
    return duration in 1..24
  }
}
