package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceWearerRepository
import java.util.*

class DeviceWearerServiceTest {
  val deviceWearerRepository = Mockito.mock(DeviceWearerRepository::class.java)
  @Test
  fun `getAllDeviceWearers should return an empty list when there is no device wearer`() {
    val expected = listOf<DeviceWearer>()
    Mockito.`when`(deviceWearerRepository.findAll().toList()).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getAllDeviceWearers()
    Assertions.assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `getAllDeviceWearers should return a list of items when there are some device wearers`() {
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
    val expected = null
    val id = "345ab"
    Mockito.`when`(deviceWearerRepository.findByDeviceWearerId(id)).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getDeviceWearerById(id)
    Assertions.assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `getDeviceWearerById should return an item when device wearer exists`() {
    val expected = DeviceWearer(1, "1234", "John", "Smith", "Curfew")
    val id = "345ab"
    Mockito.`when`(deviceWearerRepository.findByDeviceWearerId(id)).thenReturn(expected)
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)
    val actual = deviceWearerService.getDeviceWearerById(id)
    Assertions.assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `getMatchingDeviceWearers should search the database with a supplied parameter converted to lowercase`() {
    val param = "ETdgh34"
    val expectedParameter = param.lowercase(Locale.getDefault())
    val deviceWearerService = DeviceWearerService(deviceWearerRepository)

    deviceWearerService.getMatchingDeviceWearers(param)

    verify(deviceWearerRepository, times(1)).searchDatabase(expectedParameter)
  }
}
