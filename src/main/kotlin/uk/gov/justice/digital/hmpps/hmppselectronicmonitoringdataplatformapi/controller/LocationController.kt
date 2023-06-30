package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.StaticHelpers
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.LocationResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.ILocationService
import java.util.*

@RequestMapping("locations")
@RestController
class LocationController(@Autowired private val locationService: ILocationService) {
  @GetMapping("/v1")
  fun getAllLocations(): ResponseEntity<LocationResponse> {
    try {
      val result: List<Location> = locationService.getAllLocations()
      if (result.isEmpty()) {
        return ResponseEntity(LocationResponse("No data found"), HttpStatus.OK)
      }
      return ResponseEntity(LocationResponse(result), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(LocationResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/v1/device-wearer-id/{id}")
  fun getLocationsDataByDeviceWearerId(@PathVariable("id") deviceWearerId: String): ResponseEntity<LocationResponse> {
    try {
      if (!StaticHelpers().validateUUID(deviceWearerId)) {
        return ResponseEntity(LocationResponse("Insert a valid id"), HttpStatus.BAD_REQUEST)
      }
      val result = locationService.getAllLocationsForDeviceWearer(deviceWearerId)
      if (result.isEmpty()) {
        return ResponseEntity(LocationResponse("No data found"), HttpStatus.OK)
      }
      return ResponseEntity(LocationResponse(result), HttpStatus.OK)
    } catch (e: Exception) {
      return ResponseEntity(LocationResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/v1/serach-by-time")
  fun getLocationsDataByDeviceWearerIdAndTimeFrame(@RequestParam("deviceWearerId") deviceWearerId: String, @RequestParam("startDate") startDate: String, @RequestParam("endDate") endDate: String): ResponseEntity<LocationResponse> {
    var errorMessage = ""
    if (!StaticHelpers().validateUUID(deviceWearerId)) {
      errorMessage = "Insert a valid device wearer id"
    }
    if (!StaticHelpers().validateData(startDate)) {
      errorMessage = "Insert a valid start date"
    }
    if (!StaticHelpers().validateData(endDate)) {
      errorMessage = "Insert a valid end date"
    }
    if (errorMessage != "") {
      return ResponseEntity(LocationResponse(errorMessage), HttpStatus.BAD_REQUEST)
    }
    return TODO()
  }

}
