package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location

class LocationResponse : BaseResponse {
  val location: List<Location>
  constructor(locationList: List<Location>?, error: String = "") : super(error) {
    this.location = locationList ?:listOf()
  }
  constructor(locationList: Location, error: String = "") : super(error) {
    this.location = listOf<Location>(locationList)
  }
  constructor(error: String) : super(error) {
    this.location = listOf<Location>()
  }
}