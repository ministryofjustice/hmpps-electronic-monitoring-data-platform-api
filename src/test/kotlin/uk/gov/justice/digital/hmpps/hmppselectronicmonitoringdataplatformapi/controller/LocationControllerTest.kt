package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.GPSData
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.DeviceWearerResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.GPSDataResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.DeviceWearerService
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.LocationService
import java.util.*
import java.util.stream.Stream

class LocationControllerTest {

    val locationService = Mockito.mock(LocationService::class.java)

  @Test
  fun `getAllGPSData Should return a list of all location data`() {
    val deviceWearerID : UUID = UUID.randomUUID()
    val latitude:Double = 20.0
    val longitude:Double = 20.0
    val gpsDataList: List<GPSData> = listOf(GPSData(UUID.randomUUID(),deviceWearerID.toString(),latitude,longitude))

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

}
