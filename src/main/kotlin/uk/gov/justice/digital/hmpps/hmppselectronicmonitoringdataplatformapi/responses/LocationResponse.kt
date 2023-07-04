package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location

class LocationResponse : BaseResponse {
  val locations: List<Location>
  var message: String = ""
  constructor(locationList: List<Location>?, error: String = "") : super(error) {
    this.locations = locationList ?:listOf()
  }
  constructor(locationList: Location, error: String = "") : super(error) {
    this.locations = listOf<Location>(locationList)
  }

  constructor(error: String = "", message: String) : super(error) {
    this.locations = listOf<Location>()
    this.message = message
  }
  constructor(error: String) : super(error) {
    this.locations = listOf<Location>()
  }
}