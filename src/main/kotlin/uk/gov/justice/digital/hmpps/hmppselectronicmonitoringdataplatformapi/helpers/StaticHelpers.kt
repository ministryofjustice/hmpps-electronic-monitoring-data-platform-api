package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import java.time.LocalDateTime
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

  fun validateData(date: String): Boolean {
    try {
      LocalDateTime.parse(date)
    } catch (e: Exception) {
      return false
    }
    return true
  }

  fun isValidISODateTime(date: String?): Boolean {
    return try {
      DateTimeFormatter.ISO_DATE_TIME.parse(date)
      true
    } catch (e: DateTimeParseException) {
      false
    }
  }

  fun validateDuration(duration: Int): Boolean {
    return duration in 1..24
  }
}








