package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

  @PostMapping("/v1")
  fun createDummyDeviceWearers(): ResponseEntity<List<DeviceWearer>> {
    return try {
      ResponseEntity(deviceWearerService.createDummyDeviceWearers(), HttpStatus.OK)
    } catch (e: Exception) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/v1/id/{id}")
  fun getDeviceWearerById(@PathVariable("id") deviceWearerId: String): ResponseEntity<BaseResponse> {
    try {
      var id = UUID.fromString(deviceWearerId);
      //  return ResponseEntity(DeviceWearerResponse(deviceWearerService.getDeviceWearerById(deviceWearerId)), HttpStatus.OK)
      val result = deviceWearerService.getDeviceWearerById(deviceWearerId)
      if (result != null) {

        return ResponseEntity(DeviceWearerResponse(result), HttpStatus.OK)
      }

      return ResponseEntity(BaseResponse("No user found"), HttpStatus.OK)


    } catch (e: Exception) {
      when (e) {
        is IllegalArgumentException -> {
          return ResponseEntity(BaseResponse("Insert a valid id"), HttpStatus.BAD_REQUEST)
        }

        else -> {
          return ResponseEntity(BaseResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
      }
    }
  }
}


//   catch (IllegalArgumentException exception){
//    //handle the case where string is not valid UUID
//     ResponseEntity(HttpStatus.BAD_REQUEST) //just guessing


