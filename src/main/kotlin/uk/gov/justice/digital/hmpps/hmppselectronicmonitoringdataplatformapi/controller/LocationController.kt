package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.DateConverter
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.StaticHelpers
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.EmApiError
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.LocationResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.ILocationService
import java.util.*


@RequestMapping("locations")
@RestController
class LocationController(@Autowired private val locationService: ILocationService) {
  @GetMapping("/v1")
  fun getAllLocations(): ResponseEntity<LocationResponse> {
    val result: List<Location> = locationService.getAllLocations()
    if (result.isEmpty()) {
      return ResponseEntity(LocationResponse(message = "No data found"), HttpStatus.OK)
    }
    return ResponseEntity(LocationResponse(result), HttpStatus.OK)
  }

  @GetMapping("/v1/device-wearer-id/{id}")
  fun getLocationsByDeviceWearerId(@PathVariable("id") deviceWearerId: String): ResponseEntity<LocationResponse> {
    if (!StaticHelpers().validateUUID(deviceWearerId)) {
      throw EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)
    }
    val result = locationService.getLocationsByDeviceWearerId(deviceWearerId)
    if (result.isEmpty()) {
      return ResponseEntity(LocationResponse(message = "No data found"), HttpStatus.OK)
    }
    return ResponseEntity(LocationResponse(result), HttpStatus.OK)
  }

  @GetMapping("/v1/search-by-time")
  fun getLocationsByDeviceWearerIdAndTimeFrame(
    @RequestParam("deviceWearerId") deviceWearerId: String,
    @RequestParam("startDate") startDate: String,
    @RequestParam("endDate") endDate: String,
  ): ResponseEntity<LocationResponse> {
    var errorMessage = ""
    if (!StaticHelpers().validateUUID(deviceWearerId)) {
      errorMessage = "Insert a valid device wearer id"
    }
    if (!StaticHelpers().isValidISODateTime(startDate)) {
      errorMessage = "Insert a valid start date"
    }
    if (!StaticHelpers().isValidISODateTime(endDate)) {
      errorMessage = "Insert a valid end date"
    }
    if (errorMessage != "") {
      throw EmApiError(errorMessage, HttpStatus.BAD_REQUEST)
    }
    val start: Date = DateConverter().convertFromStringToDate(startDate)
    val end: Date = DateConverter().convertFromStringToDate(endDate)

    val result = locationService.getLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, start, end)
    if (result.isEmpty()) {
      return ResponseEntity(LocationResponse(message = "No data found"), HttpStatus.OK)
    }
    return ResponseEntity(LocationResponse(result), HttpStatus.OK)
  }

  @GetMapping("/v1/device-id/{id}")
  fun getLocationsByDeviceId(@PathVariable("id") deviceId: String): ResponseEntity<LocationResponse> {
    if (!StaticHelpers().validateUUID(deviceId)) {
      throw EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)
    }
    val result = locationService.getLocationsByDeviceId(deviceId)
    if (result.isEmpty()) {
      return ResponseEntity(LocationResponse(message = "No data found"), HttpStatus.OK)
    }
    return ResponseEntity(LocationResponse(result), HttpStatus.OK)
  }
}
