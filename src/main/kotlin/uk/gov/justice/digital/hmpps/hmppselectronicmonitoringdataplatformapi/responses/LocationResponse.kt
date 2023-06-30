package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location

class LocationResponse : BaseResponse {
  val gpsData: List<Location>
  constructor(gpsDataList: List<Location>?, error: String = "") : super(error) {
    this.gpsData = gpsDataList ?:listOf()
  }
  constructor(gpsDataList: Location, error: String = "") : super(error) {
    this.gpsData = listOf<Location>(gpsDataList)
  }
  constructor(error: String) : super(error) {
    this.gpsData = listOf<Location>()
  }
}