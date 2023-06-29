package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.StaticHelpers
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.GPSData
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.GPSDataResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.ILocationService
import java.util.*

@RequestMapping("location")
@RestController
class LocationController(@Autowired private val locationService: ILocationService) {
  @GetMapping("/v1")
  fun getAllGPSData(): ResponseEntity<GPSDataResponse> {
    try {
      val result: List<GPSData> = locationService.getAllGPSData()
      if (result.isEmpty()) {
        return ResponseEntity(GPSDataResponse("No data found"), HttpStatus.OK)
      }
      return ResponseEntity(GPSDataResponse(result), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(GPSDataResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/v1/device-wearer-id/{id}")
  fun getGPSDataByDeviceWearerId(@PathVariable("id") deviceWearerId: String): ResponseEntity<GPSDataResponse> {
    try {
      if (!StaticHelpers().ValidateUUID(deviceWearerId)) {
        return ResponseEntity(GPSDataResponse("Insert a valid id"), HttpStatus.BAD_REQUEST)
      }
      val result = locationService.getGPSDataForUser(deviceWearerId)
      if (result.isEmpty()) {
        return ResponseEntity(GPSDataResponse("No data found"), HttpStatus.OK)
      }
      return ResponseEntity(GPSDataResponse(result), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(GPSDataResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}
