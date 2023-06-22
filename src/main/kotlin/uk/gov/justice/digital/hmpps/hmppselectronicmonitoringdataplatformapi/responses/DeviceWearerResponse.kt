package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer

class DeviceWearerResponse : BaseResponse {
  val deviceWearers: List<DeviceWearer>
  constructor(deviceWearerList: List<DeviceWearer>) : super() {
    this.deviceWearers = deviceWearerList
  }
  constructor(deviceWearer: DeviceWearer) : super() {
    this.deviceWearers = listOf<DeviceWearer>(deviceWearer)
  }
}
