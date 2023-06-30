package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.LocationResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.LocationService

class LocationControllerTest {

  val locationService = Mockito.mock(LocationService::class.java)

  @Test
  fun `getAllLocations Should return a list of all location data`() {
    val device = Device(
      id = 1,
      deviceId = "deviceId",
      modelId = "modelId",
      firmwareVersion = "firmwareVersion",
      deviceType = "deviceType",
      status = "status",
      batteryLifeRemaining = 20,
    )
    val locationList: List<Location> = listOf(Location(1, device, 20.0, 20.0))

    Mockito.`when`(locationService.getAllLocationData()).thenReturn(locationList)

    val expected: ResponseEntity<LocationResponse> = ResponseEntity(LocationResponse(locationList), HttpStatus.OK)

    val result = LocationController(locationService).getAllLocations()

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.location).isEqualTo(expected.body.location)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getAllLocations Should return No data found when no location exists`() {
    Mockito.`when`(locationService.getAllLocationData()).thenReturn(listOf())

    val expected = ResponseEntity(LocationResponse("No data found"), HttpStatus.OK)
    val result = LocationController(locationService).getAllLocations()

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.location).isEqualTo(expected.body.location)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getAllLocations should return internal server error when there is an internal server issue`() {
    Mockito.`when`(locationService.getAllLocationData()).thenThrow(RuntimeException("Exception"))
    val expected: ResponseEntity<LocationResponse> =
      ResponseEntity(LocationResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)

    val result = LocationController(locationService).getAllLocations()

    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getLocationsByDeviceWearerId Should return a list of location data`() {
    val device = Device(
      id = 1,
        deviceId = "deviceId",
        modelId = "modelId",
        firmwareVersion = "firmwareVersion",
        deviceType = "deviceType",
        status = "status",
        batteryLifeRemaining = 20,
      )
    val deviceWearerId = "3fc55bb7-ba52-4854-be96-661f710328fc"
    val locationDataList: List<Location> = listOf(Location(1, device, 20.0, 20.0))

    Mockito.`when`(locationService.getAllLocationDataForDeviceWearer(deviceWearerId)).thenReturn(locationDataList)

    val expected: ResponseEntity<LocationResponse> = ResponseEntity(LocationResponse(locationDataList), HttpStatus.OK)

    val result = LocationController(locationService).getLocationsDataByDeviceWearerId(deviceWearerId)

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.location).isEqualTo(expected.body.location)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getLocationsByDeviceWearerId should return bad request when it does not receive a valid id`() {
    val userId = "456an"
    val expected: ResponseEntity<LocationResponse> = ResponseEntity(LocationResponse("Insert a valid id"), HttpStatus.BAD_REQUEST)

    val result = LocationController(locationService).getLocationsDataByDeviceWearerId(userId)

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
    verify(locationService, times(0)).getAllLocationDataForDeviceWearer(any())
  }

  @Test
  fun `getLocationsByDeviceWearerId Should return No data found when no location exists`() {
    val userId = "b537065a-094e-47eb-8fab-9698a9664d35"
    Mockito.`when`(locationService.getAllLocationDataForDeviceWearer(userId)).thenReturn(listOf())

    val expected = ResponseEntity(LocationResponse("No data found"), HttpStatus.OK)
    val result = LocationController(locationService).getLocationsDataByDeviceWearerId(userId)

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.location).isEqualTo(expected.body.location)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getLocationsByDeviceWearerId should return internal server error when there is an internal server issue`() {
    Mockito.`when`(locationService.getAllLocationDataForDeviceWearer(any<String>())).thenThrow(RuntimeException("Exception"))
    val expected: ResponseEntity<LocationResponse> =
      ResponseEntity(LocationResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
    val userId = "b537065a-094e-47eb-8fab-9698a9664d35"
    val result = LocationController(locationService).getLocationsDataByDeviceWearerId(userId)

    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }
}
