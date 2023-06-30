package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class DateConverter {
  fun convertToDateViaInstant(dateToConvert: LocalDateTime): Date {
    return java.sql.Timestamp.valueOf(dateToConvert)
  }
}