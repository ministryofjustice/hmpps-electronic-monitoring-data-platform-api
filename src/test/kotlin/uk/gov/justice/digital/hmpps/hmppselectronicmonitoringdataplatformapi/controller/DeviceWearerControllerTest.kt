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
  fun `getAllDeviceWearersShouldReturnAnEmptyListWhenThereAreNoDeviceWearers`() {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val genericlist: List<DeviceWearer> = listOf<DeviceWearer>()
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(genericlist)

    val expected = ResponseEntity(genericlist, HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getAllDeviceWearers()

    Assertions.assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `getAllDeviceWearersShouldReturnAListOfItemsWhenThereAreSomeDeviceWearers`() {
    val deviceWearerService = Mockito.mock(DeviceWearerService::class.java)
    val genericlist: List<DeviceWearer> = listOf<DeviceWearer>(DeviceWearer(1, "1234", "John", "Smith", "Curfew"), DeviceWearer(2, "5678", "Oliver", "Brown", "Inclusion Zone"))
    Mockito.`when`(deviceWearerService.getAllDeviceWearers()).thenReturn(genericlist)

    val expected = ResponseEntity(genericlist, HttpStatus.OK)

    val result = DeviceWearerController(deviceWearerService).getAllDeviceWearers()

    Assertions.assertThat(result).isEqualTo(expected)
  }
}
