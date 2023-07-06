package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceRepository

class DeviceServiceTest {

  @Test
  fun `getDevicesByDeviceWearerId should call to fetch all devices for the specific device wearer`() {
    val deviceRepository = Mockito.mock(DeviceRepository::class.java)
    val deviceService = DeviceService(deviceRepository)
    val deviceWearerId = "test user ID"

    val expected: List<Device> = listOf()
    val result: List<Device> = deviceService.getDevicesByDeviceWearerId(deviceWearerId)

    verify(deviceRepository, times(1)).findDevicesByDeviceWearerId(deviceWearerId)
    Assertions.assertThat(result).isEqualTo(expected)
  }

  }
