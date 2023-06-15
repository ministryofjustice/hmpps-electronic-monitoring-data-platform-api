package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.DeviceWearerService

class DeviceWearerControllerTest {
  @Test
  fun `getAllDeviceWearers ShouldReturn AnEmptyListWhenThereAreNoDeviceWearers`() {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val genericlist: List<DeviceWearer> = listOf<DeviceWearer>()
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(genericlist)

    val expected = ResponseEntity(genericlist, HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getAllDeviceWearers()

    Assertions.assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `getAllDeviceWearers ShouldReturn AListOfItems WhenThereAreSomeDeviceWearers`() {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val genericlist: List<DeviceWearer> = listOf<DeviceWearer>(DeviceWearer(1, "1234", "John", "Smith", "Curfew"), DeviceWearer(2, "5678", "Oliver", "Brown", "Inclusion Zone"))
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(genericlist)

    val expected = ResponseEntity(genericlist, HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getAllDeviceWearers()

    Assertions.assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `getDeviceWearerByID should return bad request error when device wearer does not exist`() {
    val id = "783sd"
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)

    val response = null
    Mockito.`when`(deviceWearerService.getDeviceWearerById(id)).thenReturn(response)
    val expected:ResponseEntity<DeviceWearer> = ResponseEntity(HttpStatus.BAD_REQUEST)

    val result = DeviceWearerController(deviceWearerService).getDeviceWearerById(id)
    Assertions.assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `getDeviceWearerByID should return an item when there are some device wearers`() {
    val id = "456an"
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)

    val response = DeviceWearer(1, "456an", "John", "Smith", "Curfew")
    Mockito.`when`(deviceWearerService.getDeviceWearerById(id)).thenReturn(response)
    val expected = ResponseEntity(response, HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getDeviceWearerById(id)
    Assertions.assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `getDeviceWearerByID should return internal server error when there is an internal server issue`() {
    val id = "783sd"
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)

    Mockito.`when`(deviceWearerService.getDeviceWearerById(id)).thenThrow(RuntimeException("Exception"))
    val expected:ResponseEntity<DeviceWearer> = ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

    val result = DeviceWearerController(deviceWearerService).getDeviceWearerById(id)
    Assertions.assertThat(result).isEqualTo(expected)
  }

}
