package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.GPSData

class GPSDataResponse : BaseResponse {
  val gpsData: List<GPSData>
  constructor(gpsDataList: List<GPSData>?, error: String = "") : super(error) {
    this.gpsData = gpsDataList ?:listOf()
  }
  constructor(gpsDataList: GPSData, error: String = "") : super(error) {
    this.gpsData = listOf<GPSData>(gpsDataList)
  }
  constructor(error: String) : super(error) {
    this.gpsData = listOf<GPSData>()
  }
}