package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer

class DeviceWearerResponse : BaseResponse {
  val deviceWearers: List<DeviceWearer>
  constructor(deviceWearerList: List<DeviceWearer>?, error: String = "") : super(error) {
    this.deviceWearers = deviceWearerList ?:listOf()
  }
  constructor(deviceWearer: DeviceWearer, error: String = "") : super(error) {
    this.deviceWearers = listOf<DeviceWearer>(deviceWearer)
  }
  constructor(error: String) : super(error) {
    this.deviceWearers = listOf<DeviceWearer>()
  }
}
