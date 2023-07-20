package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import java.util.*
import kotlin.reflect.full.memberProperties

interface ILocationAggregation {
  var latitude: Double
  var longitude: Double
  var datetime: Date
}

data class LocationAggregation(
  override var latitude: Double,
  override var longitude: Double,
  override var datetime: Date,
) : IConvertable, ILocationAggregation {
  override fun getProperties(): List<Pair<String, String>> {
    return LocationAggregation::class.memberProperties.map { it.name to it.get(this).toString() }
  }
}
