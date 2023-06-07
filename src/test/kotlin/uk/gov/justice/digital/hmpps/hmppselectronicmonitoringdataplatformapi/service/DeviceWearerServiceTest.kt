package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.assertj.core.api.Assertions
import org.hibernate.mapping.List
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceWearerRepository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service.DeviceWearerService


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
