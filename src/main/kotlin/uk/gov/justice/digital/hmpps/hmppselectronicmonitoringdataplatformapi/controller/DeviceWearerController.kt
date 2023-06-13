package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.IDeviceWearerService

@RequestMapping("device-wearers")
@RestController
class DeviceWearerController(@Autowired private val deviceWearerService: IDeviceWearerService) {

  @GetMapping("/v1")
  fun getAllDeviceWearers(): ResponseEntity<List<DeviceWearer>> {
    try {
      return ResponseEntity(deviceWearerService.getAllDeviceWearers(), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @PostMapping("/v1")
  fun createDummyDeviceWearers(): ResponseEntity<List<DeviceWearer>> {
    try {
      return ResponseEntity(deviceWearerService.createDummyDeviceWearers(), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/v1/{id}")
  fun getDeviceWearerById(@PathVariable("id") deviceWearerId: String): ResponseEntity<DeviceWearer>{
    val deviceWearers = deviceWearerService.getAllDeviceWearers()
    val result = deviceWearers.firstOrNull { it.deviceWearerId == deviceWearerId }
    return ResponseEntity(result, HttpStatus.OK)
//    return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
  }
}