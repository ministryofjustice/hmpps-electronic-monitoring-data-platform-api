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
import java.util.stream.Stream

class DeviceWearerControllerTest {
  val allUsers: List<DeviceWearer> = listOf<DeviceWearer>(
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
    Assertions.assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
  }

  @Test
  fun `getAllDeviceWearers Should return Ok with an empty value error when there are no device wearers`() {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(listOf<DeviceWearer>())
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse("No users found"), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getAllDeviceWearers()

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @Test
  fun `getAllDeviceWearers ShouldReturn AListOfItems WhenThereAreSomeDeviceWearers`() {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val genericlist: List<DeviceWearer> = listOf<DeviceWearer>(
      DeviceWearer(1, "1234", "John", "Smith", "Curfew"),
      DeviceWearer(2, "5678", "Oliver", "Brown", "Inclusion Zone"),
    )
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(genericlist)
    val expected = ResponseEntity(DeviceWearerResponse(genericlist), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getAllDeviceWearers()

    Assertions.assertThat(result.body?.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
  }

  @Test
  fun `getDeviceWearerByID should return Ok with an empty value error when device wearer does not exist`() {
    val id = "c6a5aa13-c948-41ca-8d6a-dfe9ff2e8fd3"
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)

    val response = null
    Mockito.`when`(deviceWearerService.getDeviceWearerById(id)).thenReturn(response)
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse("No user found"), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getDeviceWearerById(id)

    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
  }

  @Test
  fun `getDeviceWearerByID should return an item when there are some device wearers`() {
    val id = "4db8a1bb-c0ef-4222-8f9b-5f62636d75c1"
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)

    val response = DeviceWearer(1, id, "John", "Smith", "Curfew")
    Mockito.`when`(deviceWearerService.getDeviceWearerById(id)).thenReturn(response)
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(response), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getDeviceWearerById(id)

    Assertions.assertThat(result.body?.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
  }

  @Test
  fun `getDeviceWearerByID should return internal server error when there is an internal server issue`() {
    val id = "db2451a6-ef09-45a7-a940-b4c46bd94b1b"
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)

    Mockito.`when`(deviceWearerService.getDeviceWearerById(any<String>())).thenThrow(RuntimeException("Exception"))
    // val expected = EmApiError("Something went wrong in our side", HttpStatus.INTERNAL_SERVER_ERROR)

    val result = assertThrows<Exception> { DeviceWearerController(deviceWearerService).getDeviceWearerById(id) }

    // confirmException(result as EmApiError, expected)
  }

  @Test
  fun `getDeviceWearerByID should return bad request when it does not receive a valid id`() {
    val id = "456an"
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val expected = EmApiError("Insert a valid id", HttpStatus.BAD_REQUEST)

    val result = assertThrows<EmApiError> { DeviceWearerController(deviceWearerService).getDeviceWearerById(id) }

    confirmException(result, expected)
    verify(deviceWearerService, times(0)).getDeviceWearerById(any())
  }

  @Test
  fun `searchDeviceWearers should return a bad request error if no query string is provided`() {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val expected = EmApiError("No search string provided", HttpStatus.BAD_REQUEST)

    val result = assertThrows<EmApiError> { DeviceWearerController(deviceWearerService).searchDeviceWearers("") }
    confirmException(result, expected)
  }

  @ParameterizedTest(name = "searchDeviceWearers should return Ok with an empty value error when the search string is: {0}")
  @MethodSource("nonMatchingSearches")
  fun `searchDeviceWearers should return Ok with an empty value error if no device wearers found`(queryString: String) {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse("No matching users found"), HttpStatus.OK)

    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(allUsers)
    val result = DeviceWearerController(deviceWearerService).searchDeviceWearers(queryString)

    Assertions.assertThat(result.statusCode).isEqualTo(expected.statusCode)
    Assertions.assertThat(result.body?.error).isEqualTo(expected.body.error)
  }

  @ParameterizedTest(name = "searchDeviceWearers should return Ok with a list of matching values when the search string is: {0}")
  @MethodSource("matchingSearches")
  fun `searchDeviceWearers should return Ok with a list of matching values if matching device wearers found`(queryString: String) {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val expectedData = allUsers[0]

    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(allUsers)
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(expectedData), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).searchDeviceWearers(queryString)

    Assertions.assertThat(result.body.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
  }

  @Test
  fun `searchDeviceWearers v2 should return all users if no query string is provided`() {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(allUsers), HttpStatus.OK)
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(allUsers)

    val result = DeviceWearerController(deviceWearerService).searchDeviceWearersV2("")

    Assertions.assertThat(result.body.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
  }

  @ParameterizedTest(name = "searchDeviceWearers v2 should return Ok with an empty list when the search string is: {0}")
  @MethodSource("nonMatchingSearches")
  fun `searchDeviceWearers v2 should return Ok with an empty list if no device wearers found`(queryString: String) {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(listOf()), HttpStatus.OK)

    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(allUsers)
    val result = DeviceWearerController(deviceWearerService).searchDeviceWearersV2(queryString)

    Assertions.assertThat(result.body.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
  }

  @ParameterizedTest(name = "searchDeviceWearers v2 should return Ok with a list of matching values when the search string is: {0}")
  @MethodSource("matchingSearches")
  fun `searchDeviceWearers v2 should return Ok with a list of matching values if matching device wearers found`(queryString: String) {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val expectedData = allUsers[0]

    Mockito.`when`(deviceWearerService.getMatchingDeviceWearers(queryString)).thenReturn(listOf(expectedData))
    val expected: ResponseEntity<DeviceWearerResponse> = ResponseEntity(DeviceWearerResponse(expectedData), HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).searchDeviceWearersV2(queryString)

    Assertions.assertThat(result.body.deviceWearers).isEqualTo(expected.body.deviceWearers)
    confirmNoError(result, expected)
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
