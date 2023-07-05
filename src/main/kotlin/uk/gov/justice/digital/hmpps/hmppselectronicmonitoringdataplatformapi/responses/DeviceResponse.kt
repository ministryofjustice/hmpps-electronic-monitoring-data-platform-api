package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device

class DeviceResponse: BaseResponse {
  val devices: List<Device>
  var message: String = ""
  constructor(deviceList: List<Device>?, error: String = "") : super(error) {
    this.devices = deviceList ?:listOf()
  }
  constructor(deviceList: Device, error: String = "") : super(error) {
    this.devices = listOf<Device>(deviceList)
  }

  constructor(error: String = "", message: String) : super(error) {
    this.devices = listOf<Device>()
    this.message = message
  }
  constructor(error: String) : super(error) {
    this.devices = listOf<Device>()
  }
}