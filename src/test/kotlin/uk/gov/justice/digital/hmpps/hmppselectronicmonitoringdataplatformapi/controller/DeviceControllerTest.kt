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
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.EmApiError
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.DeviceResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.DeviceService

class DeviceControllerTest {
  val deviceService = Mockito.mock(DeviceService::class.java)
  fun confirmException(result: EmApiError, expected: EmApiError) {
    Assertions.assertThat(result.message).isEqualTo(expected.message)
    Assertions.assertThat(result.status).isEqualTo(expected.status)
    Assertions.assertThat(result.exceptionDetails).isEqualTo(expected.exceptionDetails)
  }
  fun confirmNoError(result: ResponseEntity<DeviceResponse>, expected: ResponseEntity<DeviceResponse>) {
    Assertions.assertThat(result.body?.error).isEqualTo("")
    Assertions.assertThat(result.body?.message).isEqualTo(expected.body?.message)
    Assertions.assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
  }

  @Test
  fun `getDevicesByDeviceWearerId should return bad request when it does not receive valid deviceWearerId`() {
    val deviceWearerId = "456an"

    val expected = EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)
    val result = assertThrows<EmApiError> {
      DeviceController(deviceService).getDevicesByDeviceWearerId(deviceWearerId)
    }

    confirmException(result, expected)
    verify(deviceService, times(0)).getDevicesByDeviceWearerId(any<String>())
  }

  @Test
  fun `getDevicesByDeviceWearerId should return no data found when no device exists`() {
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"

    Mockito.`when`(deviceService.getDevicesByDeviceWearerId(deviceWearerId)).thenReturn(listOf())

    val expected = ResponseEntity(DeviceResponse(message = "No data found"), HttpStatus.OK)
    val result = DeviceController(deviceService).getDevicesByDeviceWearerId(deviceWearerId)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.devices).isEqualTo(expected.body.devices)
  }

  @Test
  fun `getDevicesByDeviceWearerId should return a list of devices`() {
    val deviceWearer = DeviceWearer(
      id = 1,
      deviceWearerId = "deviceWearerId",
      firstName = "John",
      lastName = "Smith",
      type = "Historical Case Centric",
    )
    val deviceWearerId = "3fc55bb7-ba52-4854-be96-661f710328fc"
    val dateTagFitted = DateConverter().convertFromStringToDate("2000-10-30T01:32:00.000-00:00")
    val devicesList: List<Device> = listOf(Device(1,"1", "1", "1.0", "GPS", "OK", 80, dateTagFitted, null, deviceWearer ))

    Mockito.`when`(deviceService.getDevicesByDeviceWearerId(deviceWearerId)).thenReturn(devicesList)

    val expected: ResponseEntity<DeviceResponse> = ResponseEntity(DeviceResponse(devicesList), HttpStatus.OK)
    val result = DeviceController(deviceService).getDevicesByDeviceWearerId(deviceWearerId)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.devices).isEqualTo(expected.body.devices)
    verify(deviceService, times(1)).getDevicesByDeviceWearerId(any<String>())
  }

  @Test
  fun `getDevicesByDeviceWearerId should return internal server error when there is an internal server issue`() {
    Mockito.`when`(deviceService.getDevicesByDeviceWearerId(any<String>()))
      .thenThrow(RuntimeException("Exception"))
    val deviceWearerId = "b537065a-094e-47eb-8fab-9698a9664d35"

    assertThrows<Exception> { DeviceController(deviceService).getDevicesByDeviceWearerId(deviceWearerId) }
    verify(deviceService, times(1)).getDevicesByDeviceWearerId(any<String>())
  }

  @Test
  fun `getDeviceByDeviceId should return bad request when it does not receive valid deviceId`() {
    val deviceId = "456an"

    val expected = EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)
    val result = assertThrows<EmApiError> {
      DeviceController(deviceService).getDeviceByDeviceId(deviceId)
    }

    confirmException(result, expected)
    verify(deviceService, times(0)).getDevicesByDeviceWearerId(any<String>())
  }

  @Test
  fun `getDeviceByDeviceId should return no data found when no device exists`() {
    val deviceId = "b537065a-094e-47eb-8fab-9698a9664d35"

    Mockito.`when`(deviceService.getDeviceByDeviceId(deviceId)).thenReturn(listOf())

    val expected = ResponseEntity(DeviceResponse(message = "No data found"), HttpStatus.OK)
    val result = DeviceController(deviceService).getDeviceByDeviceId(deviceId)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.devices).isEqualTo(expected.body.devices)
  }

  @Test
  fun `getDeviceByDeviceId should return a device`() {
    val deviceWearer = DeviceWearer(
      id = 1,
      deviceWearerId = "deviceId",
      firstName = "John",
      lastName = "Smith",
      type = "Historical Case Centric",
    )
    val deviceId = "3fc55bb7-ba52-4854-be96-661f710328fc"
    val dateTagFitted = DateConverter().convertFromStringToDate("2000-10-30T01:32:00.000-00:00")
    val devicesList: List<Device> = listOf(Device(1,"1", "1", "1.0", "GPS", "OK", 80, dateTagFitted, null, deviceWearer ))

    Mockito.`when`(deviceService.getDeviceByDeviceId(deviceId)).thenReturn(devicesList)

    val expected: ResponseEntity<DeviceResponse> = ResponseEntity(DeviceResponse(devicesList.first()), HttpStatus.OK)
    val result = DeviceController(deviceService).getDeviceByDeviceId(deviceId)

    confirmNoError(result, expected)
    Assertions.assertThat(result.body?.devices).isEqualTo(expected.body.devices)
    verify(deviceService, times(1)).getDeviceByDeviceId(any<String>())
  }

  @Test
  fun `getDeviceByDeviceId should return internal server error when there is an internal server issue`() {
    Mockito.`when`(deviceService.getDeviceByDeviceId(any<String>()))
      .thenThrow(RuntimeException("Exception"))
    val deviceId = "b537065a-094e-47eb-8fab-9698a9664d35"

    assertThrows<Exception> { DeviceController(deviceService).getDeviceByDeviceId(deviceId) }
    verify(deviceService, times(1)).getDeviceByDeviceId(any<String>())
  }
}
