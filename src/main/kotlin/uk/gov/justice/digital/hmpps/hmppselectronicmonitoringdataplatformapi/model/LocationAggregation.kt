package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import java.util.*
import kotlin.reflect.full.memberProperties

data class LocationAggregation (
  var latitude: Double,
  var longitude: Double,
  var datetime: Date
): IConvertable {
   override fun getProperties(): List<Pair<String, String>> {
    return LocationAggregation::class.memberProperties.map { it.name to it.get(this).toString() }
  }
}

