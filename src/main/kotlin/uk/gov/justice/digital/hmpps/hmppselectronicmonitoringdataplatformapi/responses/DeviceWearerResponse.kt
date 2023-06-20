package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer

class DeviceWearerResponse(deviceWearer: DeviceWearer) : BaseResponse() {
  val deviceWearer: DeviceWearer = deviceWearer
}
