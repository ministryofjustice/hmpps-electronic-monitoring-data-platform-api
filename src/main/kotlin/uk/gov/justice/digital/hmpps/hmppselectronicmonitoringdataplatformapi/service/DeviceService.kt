package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceRepository

interface IDeviceService {
  fun getAllDevices(): List<Device>
  fun getDevicesByDeviceWearerId(deviceWearerId: String): List<Device>
  fun getDeviceByDeviceId(deviceId: String): Device?
}

@Service
class DeviceService(@Autowired private val deviceRepository: DeviceRepository) :
  IDeviceService {
  override fun getAllDevices(): List<Device> {
    return deviceRepository.findAll().toList()
  }

  override fun getDevicesByDeviceWearerId(deviceWearerId: String): List<Device> {
    return deviceRepository.findDevicesByDeviceWearerId(deviceWearerId) ?: listOf()
  }
  override fun getDeviceByDeviceId(deviceId: String): Device? {
    return deviceRepository.findDeviceByDeviceId(deviceId)?.firstOrNull()
  }
}
