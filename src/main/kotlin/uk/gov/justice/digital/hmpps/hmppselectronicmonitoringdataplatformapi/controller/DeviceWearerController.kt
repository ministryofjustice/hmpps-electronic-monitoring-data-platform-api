package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.StaticHelpers
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.BaseResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.DeviceWearerResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.IDeviceWearerService
import java.util.*

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

  @GetMapping("/v1/search")
  fun searchDeviceWearers(@RequestParam("search") queryString: String?): ResponseEntity<BaseResponse> {
    if (queryString.isNullOrBlank()) {
      return ResponseEntity(BaseResponse("No search string provided"), HttpStatus.BAD_REQUEST)
    }
    return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
  }

  @GetMapping("/v1/id/{id}")
  fun getDeviceWearerById(@PathVariable("id") deviceWearerId: String): ResponseEntity<BaseResponse> {
    try {
      if (!StaticHelpers().ValidateUUID(deviceWearerId)) {
        return ResponseEntity(BaseResponse("Insert a valid id"), HttpStatus.BAD_REQUEST)
      }
      val result = deviceWearerService.getDeviceWearerById(deviceWearerId)
        ?: return ResponseEntity(BaseResponse("No user found"), HttpStatus.OK)
      return ResponseEntity(DeviceWearerResponse(result), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(BaseResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}
