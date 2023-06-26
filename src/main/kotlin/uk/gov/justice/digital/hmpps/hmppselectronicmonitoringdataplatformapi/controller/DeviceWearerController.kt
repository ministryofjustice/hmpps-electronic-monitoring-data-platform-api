package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.StaticHelpers
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.DeviceWearerResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.IDeviceWearerService
import java.util.*
@RequestMapping("device-wearers")
@RestController
class DeviceWearerController(@Autowired private val deviceWearerService: IDeviceWearerService) {
  @GetMapping("/v1")
  fun getAllDeviceWearers(): ResponseEntity<DeviceWearerResponse> {
    try {
      val result: List<DeviceWearer> = deviceWearerService.getAllDeviceWearers()
      if (result.isEmpty()) {
        return ResponseEntity(DeviceWearerResponse("No users found"), HttpStatus.OK)
      }
      return ResponseEntity(DeviceWearerResponse(result), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/v2/search")
  fun searchDeviceWearersV2(@RequestParam("search") queryString: String?): ResponseEntity<DeviceWearerResponse> {
    try {
      if (queryString.isNullOrBlank()) {
        val result = deviceWearerService.getAllDeviceWearers()
        return ResponseEntity(DeviceWearerResponse(result), HttpStatus.OK)
      }
      else {
        val result = deviceWearerService.getMatchingDeviceWearers(queryString)
        return ResponseEntity(DeviceWearerResponse(result), HttpStatus.OK)
      }
    } catch (e: Exception) {
      return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
  
  @GetMapping("/v1/search/{queryString}")
  fun searchDeviceWearers(@PathVariable("queryString") queryString: String?): ResponseEntity<DeviceWearerResponse> {
    try {
      if (queryString.isNullOrBlank()) {
        return ResponseEntity(DeviceWearerResponse("No search string provided"), HttpStatus.BAD_REQUEST)
      }
      val matchingDeviceWearers = filterDeviceWearers(queryString)
      if (matchingDeviceWearers.isEmpty()) {
        return ResponseEntity(DeviceWearerResponse("No matching users found"), HttpStatus.OK)
      }
      return ResponseEntity(DeviceWearerResponse(matchingDeviceWearers), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
  
  fun filterDeviceWearers(queryString: String): List<DeviceWearer> {
    val deviceWearers: List<DeviceWearer> = deviceWearerService.getAllDeviceWearers()
    return deviceWearers.filter {
      it.firstName.contains(queryString, ignoreCase = true) ||
        it.lastName.contains(queryString, ignoreCase = true) ||
        it.type.contains(queryString, ignoreCase = true) ||
        it.deviceWearerId.contains(queryString, ignoreCase = true)
    }
  }
  
  @GetMapping("/v1/id/{id}")
  fun getDeviceWearerById(@PathVariable("id") deviceWearerId: String): ResponseEntity<DeviceWearerResponse> {
    try {
      if (!StaticHelpers().ValidateUUID(deviceWearerId)) {
        return ResponseEntity(DeviceWearerResponse("Insert a valid id"), HttpStatus.BAD_REQUEST)
      }
      val result = deviceWearerService.getDeviceWearerById(deviceWearerId)
        ?: return ResponseEntity(DeviceWearerResponse("No user found"), HttpStatus.OK)
      return ResponseEntity(DeviceWearerResponse(result), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(DeviceWearerResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}