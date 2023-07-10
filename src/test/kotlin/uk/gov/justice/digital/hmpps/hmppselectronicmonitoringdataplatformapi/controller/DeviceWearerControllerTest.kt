package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.EmApiError
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.responses.DeviceWearerResponse
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.DeviceWearerService
import java.util.*
import java.util.stream.Stream

class DeviceWearerControllerTest {
  val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)

  val allDeviceWearers: List<DeviceWearer> = listOf<DeviceWearer>(
    DeviceWearer(1, "1234", "John", "Smith", "Curfew"),
    DeviceWearer(2, "5678", "Oliver", "Brown", "Inclusion Zone"),
  )

  fun confirmException(result: EmApiError, expected: EmApiError) {
    Assertions.assertThat(result.message).isEqualTo(expected.message)
    Assertions.assertThat(result.status).isEqualTo(expected.status)
    Assertions.assertThat(result.exceptionDetails).isEqualTo(expected.exceptionDetails)
  }

  fun confirmNoError(result: ResponseEntity<DeviceWearerResponse>, expected: ResponseEntity<DeviceWearerResponse>) {
    Assertions.assertThat(result.body?.error).isEqualTo("")
    Assertions.assertThat(result.body?.message).isEqualTo(expected.body?.message)
    Assertions.assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
  }

  @Test
  fun `getAllDeviceWearers should return Ok with a message when there are no device wearers`() {
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(message = "No data found"), HttpStatus.OK)
    val result = DeviceWearerController(deviceWearerService).getAllDeviceWearers()

    Assertions.assertThat(result.body?.deviceWearers).isEqualTo(expected.body?.deviceWearers)
    confirmNoError(result, expected)
  }

  @Test
  fun `getAllDeviceWearers should return a list of items when there are some device wearers`() {
    val genericlist: List<DeviceWearer> = listOf<DeviceWearer>(
      DeviceWearer(1, "1234", "John", "Smith", "Curfew"),
      DeviceWearer(2, "5678", "Oliver", "Brown", "Inclusion Zone"),
    )
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(genericlist)
    val expected = ResponseEntity(DeviceWearerResponse(genericlist), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getAllDeviceWearers()

    Assertions.assertThat(result.body?.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
    verify(deviceWearerService, times(1)).getAllDeviceWearers()
  }

  @Test
  fun `getDeviceWearerByID should return Ok with a message when device wearer does not exist`() {
    val id = "c6a5aa13-c948-41ca-8d6a-dfe9ff2e8fd3"

    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(message = "No data found"), HttpStatus.OK)
    val result = DeviceWearerController(deviceWearerService).getDeviceWearerById(id)

    Assertions.assertThat(result.body?.deviceWearers).isEqualTo(expected.body?.deviceWearers)
    confirmNoError(result, expected)
  }

  @Test
  fun `getDeviceWearerByID should return an item when there are some device wearers`() {
    val id = "4db8a1bb-c0ef-4222-8f9b-5f62636d75c1"

    val response = DeviceWearer(1, id, "John", "Smith", "Curfew")
    Mockito.`when`(deviceWearerService.getDeviceWearerById(id)).thenReturn(response)
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(response), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getDeviceWearerById(id)

    Assertions.assertThat(result.body?.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
    verify(deviceWearerService, times(1)).getDeviceWearerById(any<String>())
  }

  @Test
  fun `getDeviceWearerByID should return internal server error when there is an internal server issue`() {
    val id = "db2451a6-ef09-45a7-a940-b4c46bd94b1b"
    Mockito.`when`(deviceWearerService.getDeviceWearerById(any<String>())).thenThrow(RuntimeException("Exception"))

    assertThrows<Exception> { DeviceWearerController(deviceWearerService).getDeviceWearerById(id) }
    verify(deviceWearerService, times(1)).getDeviceWearerById(any<String>())
  }

  @Test
  fun `getDeviceWearerByID should return bad request when it does not receive a valid id`() {
    val id = "456an"
    val expected = EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)

    val result = assertThrows<EmApiError> { DeviceWearerController(deviceWearerService).getDeviceWearerById(id) }

    confirmException(result, expected)
    verify(deviceWearerService, times(0)).getDeviceWearerById(any<String>())
  }

  @Test
  fun `searchDeviceWearers should return a bad request error if no query string is provided`() {
    val expected = EmApiError("No search string provided", HttpStatus.BAD_REQUEST)

    val result = assertThrows<EmApiError> { DeviceWearerController(deviceWearerService).searchDeviceWearers("") }
    confirmException(result, expected)
  }

  @ParameterizedTest(name = "searchDeviceWearers should return Ok with an empty value error when the search string is: {0}")
  @MethodSource("nonMatchingSearches")
  fun `searchDeviceWearers should return Ok with a message if no device wearers found`(queryString: String) {
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(message = "No matching data found"), HttpStatus.OK)

    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(allDeviceWearers)
    val result = DeviceWearerController(deviceWearerService).searchDeviceWearers(queryString)

    confirmNoError(result, expected)
  }

  @ParameterizedTest(name = "searchDeviceWearers should return Ok with a list of matching values when the search string is: {0}")
  @MethodSource("matchingSearches")
  fun `searchDeviceWearers should return Ok with a list of matching values if matching device wearers found`(queryString: String) {
    val expectedData = allDeviceWearers[0]

    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(allDeviceWearers)
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(expectedData), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).searchDeviceWearers(queryString)

    Assertions.assertThat(result.body.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
  }

  @Test
  fun `searchDeviceWearers v2 should return all device wearers if no query string is provided`() {
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(allDeviceWearers), HttpStatus.OK)
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(allDeviceWearers)

    val result = DeviceWearerController(deviceWearerService).searchDeviceWearersV2("")

    Assertions.assertThat(result.body.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
    verify(deviceWearerService, times(1)).getAllDeviceWearers()
  }

  @ParameterizedTest(name = "searchDeviceWearers v2 should return Ok with an empty list when the search string is: {0}")
  @MethodSource("nonMatchingSearches")
  fun `searchDeviceWearers v2 should return Ok with an empty list if no device wearers found`(queryString: String) {
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(listOf()), HttpStatus.OK)

    Mockito.`when`(deviceWearerService.getMatchingDeviceWearers(queryString)).thenReturn(listOf())
    val result = DeviceWearerController(deviceWearerService).searchDeviceWearersV2(queryString)

    Assertions.assertThat(result.body.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)

  }

  @ParameterizedTest(name = "searchDeviceWearers v2 should return Ok with a list of matching values when the search string is: {0}")
  @MethodSource("matchingSearches")
  fun `searchDeviceWearers v2 should return Ok with a list of matching values if matching device wearers found`(queryString: String) {
    val expectedData = allDeviceWearers[0]

    Mockito.`when`(deviceWearerService.getMatchingDeviceWearers(queryString)).thenReturn(listOf(expectedData))
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(expectedData), HttpStatus.OK)

    val result1 = DeviceWearerController(deviceWearerService).searchDeviceWearersV2(queryString)
    val result2 = DeviceWearerController(deviceWearerService).searchDeviceWearersV2PathVariable(queryString)

    Assertions.assertThat(result1.body.deviceWearers)
      .isEqualTo(result2.body.deviceWearers)
      .isEqualTo(expected.body.deviceWearers)
    confirmNoError(result1, expected)
    verify(deviceWearerService, times(2)).getMatchingDeviceWearers(any<String>())
  }

  private companion object {
    @JvmStatic
    fun matchingSearches() = Stream.of(
      Arguments.of("John"),
      Arguments.of("ohn"),
      Arguments.of("john"),
      Arguments.of("Smith"),
      Arguments.of("smith"),
      Arguments.of("Curfew"),
      Arguments.of("few"),
      Arguments.of("34"),
      Arguments.of("1234"),
    )

    @JvmStatic
    fun nonMatchingSearches() = Stream.of(
      Arguments.of("I really quite enjoy cheesecake."),
      Arguments.of("12233333333333333"),
    )
  }
}
