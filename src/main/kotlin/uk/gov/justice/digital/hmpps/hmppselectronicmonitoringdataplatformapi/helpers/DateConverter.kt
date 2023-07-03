package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class DateConverter {
  fun convertToDateViaInstant(dateToConvert: LocalDateTime): Date {
    return java.sql.Timestamp.valueOf(dateToConvert)
  }

  fun convertFromStringToDate(dateString: String): Date {
    val df2: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val date: Date = df2.parse(dateString)
    return date
  }
}