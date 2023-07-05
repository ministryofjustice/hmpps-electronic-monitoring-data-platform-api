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

//  @Test
//  fun `getAllDevicesByDeviceWearerId should call to fetch all devices for the specific device wearer and return empty if no info`() {
//    val deviceRepository = Mockito.mock(DeviceRepository::class.java)
//    val deviceService = DeviceService(deviceRepository)
//    val deviceWearerId = "test user ID"
//    // val location = null
//    // val deviceWearerId = "3fc55bb7-ba52-4854-be96-661f710328fc"
//
//    val expected: List<Device> = listOf()
//    val result: List<Device> = deviceService.getAllDevicesByDeviceWearerId(deviceWearerId)

//    verify(deviceRepository, times(1)).findDevicesByDeviceWearerId(deviceWearerId)
//    Assertions.assertThat(result).isEqualTo(expected)
  }
