package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import java.util.*

interface LocationAggregation {
  var latitude: Double
  var longitude: Double
  var datetime: Date
  fun setValue(latitude: Double, longitude: Double, datetime: Date)
}

class LocationAggregationDemo(override var latitude: Double, override var longitude: Double, override var datetime: Date) : LocationAggregation {
  override fun setValue(latitude: Double, longitude: Double, datetime: Date) {
    this.latitude = latitude
    this.latitude = longitude
    this.datetime = datetime
  }
}

