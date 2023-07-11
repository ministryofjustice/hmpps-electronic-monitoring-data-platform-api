package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.StaticHelpers
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.EmApiError
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.DeviceResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.IDeviceService

@RequestMapping("devices")
@RestController
class DeviceController(@Autowired private val deviceService: IDeviceService) {

  @GetMapping("/v1/device-wearer-id/{id}")
  fun getDevicesByDeviceWearerId(@PathVariable("id") deviceWearerId: String): ResponseEntity<DeviceResponse> {
    if (!StaticHelpers().validateUUID(deviceWearerId)) {
      throw EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)
    }
    val result = deviceService.getDevicesByDeviceWearerId(deviceWearerId)
    if (result.isEmpty()) {
      return ResponseEntity(DeviceResponse(message = "No data found"), HttpStatus.OK)
    }
    return ResponseEntity(DeviceResponse(result), HttpStatus.OK)
  }

  @GetMapping("/v1/device-id/{id}")
  fun getDeviceByDeviceId(@PathVariable("id") deviceId: String): ResponseEntity<DeviceResponse> {
    if (!StaticHelpers().validateUUID(deviceId)) {
      throw EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)
    }
    val result = deviceService.getDeviceByDeviceId(deviceId)
    if (result.isEmpty()) {
      return ResponseEntity(DeviceResponse(message = "No data found"), HttpStatus.OK)
    }
    return ResponseEntity(DeviceResponse(result.first()), HttpStatus.OK)
  }
}