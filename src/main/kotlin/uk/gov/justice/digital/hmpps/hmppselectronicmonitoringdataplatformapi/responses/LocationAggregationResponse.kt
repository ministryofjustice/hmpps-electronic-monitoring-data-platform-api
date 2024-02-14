package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.LocationAggregation

class LocationAggregationResponse : BaseResponse {
  val locations: List<LocationAggregation>
  var message: String = ""
  constructor(locationList: List<LocationAggregation>?, error: String = "") : super(error) {
    this.locations = locationList ?: listOf()
  }
  constructor(error: String = "", message: String) : super(error) {
    this.locations = listOf<LocationAggregation>()
    this.message = message
  }
  constructor(error: String) : super(error) {
    this.locations = listOf<LocationAggregation>()
  }
}
