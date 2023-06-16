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
    return try {
      ResponseEntity(deviceWearerService.getAllDeviceWearers(), HttpStatus.OK)
    } catch (e: Exception) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @PostMapping("/v1")
  fun createDummyDeviceWearers(): ResponseEntity<List<DeviceWearer>> {
    return try {
      ResponseEntity(deviceWearerService.createDummyDeviceWearers(), HttpStatus.OK)
    } catch (e: Exception) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/v1/id/{id}")
  fun getDeviceWearerById(@PathVariable("id") deviceWearerId: String): ResponseEntity<Any> {
    try {
      val result = deviceWearerService.getDeviceWearerById(deviceWearerId)
      if (result != null) {
        return ResponseEntity(result, HttpStatus.OK)
      } else {
        return ResponseEntity("No data found for that device wearer",HttpStatus.BAD_REQUEST)
      }
    } catch (e: Exception) {
      return ResponseEntity("Something went wrong in our side",HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}
