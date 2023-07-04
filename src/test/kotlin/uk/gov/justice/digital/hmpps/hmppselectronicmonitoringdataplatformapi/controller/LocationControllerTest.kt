package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.DateConverter
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.EmApiError
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.LocationResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.LocationService
import java.util.*

class LocationControllerTest {

  val locationService = Mockito.mock(LocationService::class.java)

  fun confirmException(result: EmApiError, expected: EmApiError) {
    Assertions.assertThat(result.message).isEqualTo(expected.message)
    Assertions.assertThat(result.status).isEqualTo(expected.status)
    Assertions.assertThat(result.exceptionDetails).isEqualTo(expected.exceptionDetails)
  }

  fun confirmNoError(result: ResponseEntity<LocationResponse>, expected: ResponseEntity<LocationResponse>) {
    Assertions.assertThat(result.body?.error).isEqualTo("")
    Assertions.assertThat(result.body?.message).isEqualTo(expected.body?.message)
    Assertions.assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
  }

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

    Mockito.`when`(locationService.getAllLocations()).thenReturn(locationList)

    val expected: ResponseEntity<LocationResponse> = ResponseEntity(LocationResponse(locationList), HttpStatus.OK)

    val result = LocationController(locationService).getAllLocations()

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.locations).isEqualTo(expected.body.locations)
    verify(locationService, times(1)).getAllLocations()
  }

  @Test
  fun `getAllLocations Should return No data found when no location exists`() {
    Mockito.`when`(locationService.getAllLocations()).thenReturn(listOf())

    val expected = ResponseEntity(LocationResponse(message = "No data found"), HttpStatus.OK)
    val result = LocationController(locationService).getAllLocations()

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.locations).isEqualTo(expected.body.locations)
    verify(locationService, times(1)).getAllLocations()
  }

  @Test
  fun `getAllLocations should return internal server error when there is an internal server issue`() {
    Mockito.`when`(locationService.getAllLocations()).thenThrow(RuntimeException("Exception"))
    assertThrows<Exception> { LocationController(locationService).getAllLocations() }
    verify(locationService, times(1)).getAllLocations()
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

    Mockito.`when`(locationService.getAllLocationsForDeviceWearer(deviceWearerId)).thenReturn(locationDataList)

    val expected: ResponseEntity<LocationResponse> = ResponseEntity(LocationResponse(locationDataList), HttpStatus.OK)
    val result = LocationController(locationService).getLocationsByDeviceWearerId(deviceWearerId)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.locations).isEqualTo(expected.body.locations)
    verify(locationService, times(1)).getAllLocationsForDeviceWearer(any<String>())

  }

  @Test
  fun `getLocationsByDeviceWearerId should return bad request when it does not receive a valid id`() {
    val deviceWearerId = "456an"
    val expected = EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)
    val result =
      assertThrows<EmApiError> { LocationController(locationService).getLocationsByDeviceWearerId(deviceWearerId) }

    confirmException(result, expected)
    verify(locationService, times(0)).getAllLocationsForDeviceWearer(any<String>())
  }

  @Test
  fun `getLocationsByDeviceWearerId Should return No data found when no location exists`() {
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"
    Mockito.`when`(locationService.getAllLocationsForDeviceWearer(deviceWearerId)).thenReturn(listOf())

    val expected = ResponseEntity(LocationResponse(message = "No data found"), HttpStatus.OK)
    val result = LocationController(locationService).getLocationsByDeviceWearerId(deviceWearerId)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.locations).isEqualTo(expected.body.locations)
    verify(locationService, times(1)).getAllLocationsForDeviceWearer(any<String>())
  }

  @Test
  fun `getLocationsByDeviceWearerId should return internal server error when there is an internal server issue`() {
    Mockito.`when`(locationService.getAllLocationsForDeviceWearer(any<String>()))
      .thenThrow(RuntimeException("Exception"))
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"

    assertThrows<Exception> { LocationController(locationService).getLocationsByDeviceWearerId(deviceWearerId) }
    verify(locationService, times(1)).getAllLocationsForDeviceWearer(any<String>())
  }

  @Test
  fun `getLocationsByDeviceWearerIdAndTimeFrame should return bad request when it does not receive valid deviceWearerId`() {
    val deviceWearerId = "456an"
    val startDate = "2000-10-31T01:30:00.000-05:00"
    val endDate = "2000-10-31T01:30:00.000-05:00"

    val expected = EmApiError("Insert a valid device wearer id", HttpStatus.BAD_REQUEST)
    val result = assertThrows<EmApiError> {
      LocationController(locationService).getLocationsByDeviceWearerIdAndTimeFrame(
        deviceWearerId,
        startDate,
        endDate,
      )
    }

    confirmException(result, expected)
    verify(locationService, times(0)).getAllLocationsByDeviceWearerIdAndTimeFrame(
      any<String>(),
      any<Date>(),
      any<Date>(),
    )
  }

  @Test
  fun `getLocationsByDeviceWearerIdAndTimeFrame should return bad request when it does not receive valid startDate`() {
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"
    val startDate = "2000-10-31T01:30:67.000-05:00"
    val endDate = "2000-10-31T01:30:00.000-05:00"

    val expected = EmApiError("Insert a valid start date", HttpStatus.BAD_REQUEST)
    val result = assertThrows<EmApiError> {
      LocationController(locationService).getLocationsByDeviceWearerIdAndTimeFrame(
        deviceWearerId,
        startDate,
        endDate,
      )
    }

    confirmException(result, expected)
    verify(locationService, times(0)).getAllLocationsByDeviceWearerIdAndTimeFrame(
      any<String>(),
      any<Date>(),
      any<Date>(),
    )
  }

  @Test
  fun `getLocationsByDeviceWearerIdAndTimeFrame should return bad request when it does not receive valid endDate`() {
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"
    val startDate = "2000-10-31T01:30:00.000-05:00"
    val endDate = "2000-10-31T01:30:67.000-05:00"

    val expected = EmApiError("Insert a valid end date", HttpStatus.BAD_REQUEST)
    val result = assertThrows<EmApiError> {
      LocationController(locationService).getLocationsByDeviceWearerIdAndTimeFrame(
        deviceWearerId,
        startDate,
        endDate,
      )
    }

    confirmException(result, expected)
    verify(locationService, times(0)).getAllLocationsByDeviceWearerIdAndTimeFrame(
      any<String>(),
      any<Date>(),
      any<Date>(),
    )
  }

  @Test
  fun `getLocationsByDeviceWearerIdAndTimeFrame should return empty list when receive valid dates and id but no data inside`() {
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"
    val startDate = "2000-10-31T01:30:07.000-05:00"
    val endDate = "2000-10-31T01:30:20.000-05:00"
    val expected = ResponseEntity(LocationResponse(message = "No data found"), HttpStatus.OK)
    val result =
      LocationController(locationService).getLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.locations).isEqualTo(listOf<LocationResponse>())
    verify(locationService, times(1)).getAllLocationsByDeviceWearerIdAndTimeFrame(
      any<String>(),
      any<Date>(),
      any<Date>(),
    )

  }

  @Test
  fun `getLocationsByDeviceWearerIdAndTimeFrame should return location list when receive valid dates and id and data exist`() {
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"
    val startDate = "2000-10-31T01:30:07.000-05:00"
    val endDate = "2000-10-31T01:30:20.000-05:00"
    val device = Device(
      id = 1,
      deviceId = "deviceId",
      modelId = "modelId",
      firmwareVersion = "firmwareVersion",
      deviceType = "deviceType",
      status = "status",
      batteryLifeRemaining = 20,
    )

    val locationDataList: List<Location> = listOf(Location(1, device, 20.0, 20.0))
    val start: Date = DateConverter().convertFromStringToDate(startDate)
    val end: Date = DateConverter().convertFromStringToDate(endDate)

    Mockito.`when`(locationService.getAllLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, start, end))
      .thenReturn(locationDataList)

    val expected: ResponseEntity<LocationResponse> = ResponseEntity(LocationResponse(locationDataList), HttpStatus.OK)
    val result =
      LocationController(locationService).getLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.locations).isEqualTo(expected.body?.locations)
    verify(locationService, times(1)).getAllLocationsByDeviceWearerIdAndTimeFrame(
      any<String>(),
      any<Date>(),
      any<Date>(),
    )
  }

  @Test
  fun `getLocationsByDeviceWearerIdAndTimeFrame should return list of two locations when receive valid dates and id and data exist`() {
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"
    val startDate = "2000-10-31T01:30:07.000-05:00"
    val endDate = "2000-10-31T01:30:20.000-05:00"
    val device = Device(
      id = 1,
      deviceId = "deviceId",
      modelId = "modelId",
      firmwareVersion = "firmwareVersion",
      deviceType = "deviceType",
      status = "status",
      batteryLifeRemaining = 20,
    )

    val locationDataList: List<Location> = listOf(Location(1, device, 20.0, 20.0), Location(1, device, 30.0, 30.0))
    val start: Date = DateConverter().convertFromStringToDate(startDate)
    val end: Date = DateConverter().convertFromStringToDate(endDate)

    Mockito.`when`(locationService.getAllLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, start, end))
      .thenReturn(locationDataList)

    val expected: ResponseEntity<LocationResponse> = ResponseEntity(LocationResponse(locationDataList), HttpStatus.OK)
    val result =
      LocationController(locationService).getLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.locations).isEqualTo(expected.body?.locations)
    verify(locationService, times(1)).getAllLocationsByDeviceWearerIdAndTimeFrame(
      any<String>(),
      any<Date>(),
      any<Date>(),
    )
  }

  @Test
  fun `getLocationsByDeviceWearerIdAndTimeFrame should return internal server error when there is an internal server issue`() {
    Mockito.`when`(locationService.getAllLocationsByDeviceWearerIdAndTimeFrame(any<String>(), any<Date>(), any<Date>()))
      .thenThrow(RuntimeException("Exception"))
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"
    val startDate = "2000-10-31T01:30:07.000-05:00"
    val endDate = "2000-10-31T01:30:20.000-05:00"

    assertThrows<Exception> {
      LocationController(locationService).getLocationsByDeviceWearerIdAndTimeFrame(
        deviceWearerId,
        startDate,
        endDate,
      )
    }
    verify(locationService, times(1)).getAllLocationsByDeviceWearerIdAndTimeFrame(
      any<String>(),
      any<Date>(),
      any<Date>(),
    )
  }
}