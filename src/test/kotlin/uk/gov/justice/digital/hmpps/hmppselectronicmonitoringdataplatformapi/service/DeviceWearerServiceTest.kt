package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceWearerRepository


class DeviceWearerServiceTest{
  @Test
  fun `getAllDeviceWearersShouldReturnAnEmptyListWhenThereAreNoDeviceWearers`() {
    val deviceWearerRepository = Mockito.mock(DeviceWearerRepository::class.java)
    val expected = listOf<DeviceWearer>()
    Mockito.`when`(deviceWearerRepository.findAll().toList()).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getAllDeviceWearers()
    Assertions.assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `getAllDeviceWearersShouldReturnAListOfItemsWhenThereAreSomeDeviceWearers`() {
    val deviceWearerRepository = Mockito.mock(DeviceWearerRepository::class.java)
    val expected =  listOf<DeviceWearer>(DeviceWearer(1, "1234", "John", "Smith", "Curfew"), DeviceWearer(2, "5678", "Oliver", "Brown", "Inclusion Zone"))
    Mockito.`when`(deviceWearerRepository.findAll().toList()).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getAllDeviceWearers()
    Assertions.assertThat(actual).isEqualTo(expected)
  }
}
