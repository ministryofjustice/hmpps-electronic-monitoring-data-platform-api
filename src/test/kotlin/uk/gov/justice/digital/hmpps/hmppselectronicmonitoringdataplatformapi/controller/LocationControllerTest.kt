package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.GPSData
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.GPSDataResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.LocationService
import java.time.LocalDate
import java.util.*

class LocationControllerTest {

  val locationService = Mockito.mock(LocationService::class.java)

  @Test
  fun `getAllGPSData Should return a list of all location data`() {
    val gpsDataList: List<GPSData> = listOf(GPSData(1, UUID.randomUUID().toString(), 20.0, 20.0))

    Mockito.`when`(locationService.getAllGPSData()).thenReturn(gpsDataList)

    val expected: ResponseEntity<GPSDataResponse> = ResponseEntity(GPSDataResponse(gpsDataList), HttpStatus.OK)

    val result = LocationController(locationService).getAllGPSData()

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.gpsData).isEqualTo(expected.body.gpsData)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getAllGPSData Should return No data found when no gps data exists`() {
    Mockito.`when`(locationService.getAllGPSData()).thenReturn(listOf())

    val expected = ResponseEntity(GPSDataResponse("No data found"), HttpStatus.OK)
    val result = LocationController(locationService).getAllGPSData()

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.gpsData).isEqualTo(expected.body.gpsData)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getAllGPSData should return internal server error when there is an internal server issue`() {
    Mockito.`when`(locationService.getAllGPSData()).thenThrow(RuntimeException("Exception"))
    val expected: ResponseEntity<GPSDataResponse> =
      ResponseEntity(GPSDataResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)

    val result = LocationController(locationService).getAllGPSData()

    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getGPSDataByDeviceWearerId Should return a list of location data`() {
    val deviceWearerId: String = UUID.randomUUID().toString()
    val gpsDataList: List<GPSData> = listOf(GPSData(1, deviceWearerId, 20.0, 20.0))

    Mockito.`when`(locationService.getGPSDataForUser(deviceWearerId)).thenReturn(gpsDataList)

    val expected: ResponseEntity<GPSDataResponse> = ResponseEntity(GPSDataResponse(gpsDataList), HttpStatus.OK)

    val result = LocationController(locationService).getGPSDataByDeviceWearerId(deviceWearerId)

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.gpsData).isEqualTo(expected.body.gpsData)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getGPSDataByDeviceWearerId should return bad request when it does not receive a valid id`() {
    val userId = "456an"
    val expected: ResponseEntity<GPSDataResponse> = ResponseEntity(GPSDataResponse("Insert a valid id"), HttpStatus.BAD_REQUEST)

    val result = LocationController(locationService).getGPSDataByDeviceWearerId(userId)

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
    verify(locationService, times(0)).getGPSDataForUser(any())
  }

  @Test
  fun `getGPSDataByDeviceWearerId Should return No data found when no gps data exists`() {
    val userId = "b537065a-094e-47eb-8fab-9698a9664d35"
    Mockito.`when`(locationService.getGPSDataForUser(userId)).thenReturn(listOf())

    val expected = ResponseEntity(GPSDataResponse("No data found"), HttpStatus.OK)
    val result = LocationController(locationService).getGPSDataByDeviceWearerId(userId)

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.gpsData).isEqualTo(expected.body.gpsData)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getGPSDataByDeviceWearerId should return internal server error when there is an internal server issue`() {
    Mockito.`when`(locationService.getGPSDataForUser(any<String>())).thenThrow(RuntimeException("Exception"))
    val expected: ResponseEntity<GPSDataResponse> =
      ResponseEntity(GPSDataResponse("Something went wrong in our side"), HttpStatus.INTERNAL_SERVER_ERROR)
    val userId = "b537065a-094e-47eb-8fab-9698a9664d35"
    val result = LocationController(locationService).getGPSDataByDeviceWearerId(userId)

    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }
}
