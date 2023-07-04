package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location

class DeviceWearerResponse : BaseResponse {
  val deviceWearers: List<DeviceWearer>
  var message: String = ""
  constructor(deviceWearerList: List<DeviceWearer>?, error: String = "") : super(error) {
    this.deviceWearers = deviceWearerList ?:listOf()
  }
  constructor(deviceWearer: DeviceWearer, error: String = "") : super(error) {
    this.deviceWearers = listOf<DeviceWearer>(deviceWearer)
  }

  constructor(error: String = "", message: String) : super(error) {
    this.deviceWearers = listOf<DeviceWearer>()
    this.message = message
  }
  constructor(error: String) : super(error) {
    this.deviceWearers = listOf<DeviceWearer>()
  }
}