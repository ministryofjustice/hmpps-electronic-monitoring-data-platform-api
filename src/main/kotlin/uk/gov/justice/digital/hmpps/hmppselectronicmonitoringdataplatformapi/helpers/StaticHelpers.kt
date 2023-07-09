package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import javax.xml.stream.events.StartDocument

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

  fun isStartDatePriorEndDate(startDate: Calendar, endDate: Calendar) {} Boolean
  {
    val startDate = Calendar.getInstance()
    val endDate = Calendar.getInstance()
    var startInMin = startDate[Calendar.HOUR_OF_DAY] * 60 + startDate[Calendar.MINUTE]
    var endInMin = endDate[Calendar.HOUR_OF_DAY] * 60 + endDate()[Calendar.MINUTE]

    try {
      startInMin = < endInMin
        true
    } catch (e: DateTimeParseException) {
      false
    }
  }
}






