package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceWearerRepository

class DeviceWearerServiceTest {
  @Test
  fun `getAllDeviceWearers should return an empty list when there is no device wearer`() {
    val deviceWearerRepository = Mockito.mock(DeviceWearerRepository::class.java)
    val expected = listOf<DeviceWearer>()
    Mockito.`when`(deviceWearerRepository.findAll().toList()).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getAllDeviceWearers()
    Assertions.assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `getAllDeviceWearers should return a list of items when there are some device wearers`() {
    val deviceWearerRepository = Mockito.mock(DeviceWearerRepository::class.java)
    val expected = listOf<DeviceWearer>(
      DeviceWearer(1, "1234", "John", "Smith", "Curfew"),
      DeviceWearer(2, "5678", "Oliver", "Brown", "Inclusion Zone"),
    )
    Mockito.`when`(deviceWearerRepository.findAll().toList()).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getAllDeviceWearers()
    Assertions.assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `getDeviceWearerById should return null when device wearer does not exist`() {
    val deviceWearerRepository = Mockito.mock(DeviceWearerRepository::class.java)
    val expected = null
    val id = "345ab"
    Mockito.`when`(deviceWearerRepository.findByDeviceWearerId(id)).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getDeviceWearerById(id)
    Assertions.assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `getDeviceWearerById should return an item when device wearer exists`() {
    val deviceWearerRepository = Mockito.mock(DeviceWearerRepository::class.java)
    val expected = DeviceWearer(1, "1234", "John", "Smith", "Curfew")
    val id = "345ab"
    Mockito.`when`(deviceWearerRepository.findByDeviceWearerId(id)).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getDeviceWearerById(id)
    Assertions.assertThat(actual).isEqualTo(expected)
  }
}
