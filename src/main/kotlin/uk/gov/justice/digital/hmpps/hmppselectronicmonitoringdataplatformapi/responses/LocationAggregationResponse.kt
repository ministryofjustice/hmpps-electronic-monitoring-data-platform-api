package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.ILocationAggregation

class LocationAggregationResponse : BaseResponse {
  val locations: List<ILocationAggregation>
  var message: String = ""
  constructor(locationList: List<ILocationAggregation>?, error: String = "") : super(error) {
    this.locations = locationList ?:listOf()
  }
  constructor(error: String = "", message: String) : super(error) {
    this.locations = listOf<ILocationAggregation>()
    this.message = message
  }
  constructor(error: String) : super(error) {
    this.locations = listOf<ILocationAggregation>()
  }
}