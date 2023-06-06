package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceWearerRepository

interface IDeviceWearerService {
  fun getAllDeviceWearers(): List<DeviceWearer>
}

@Service
class DeviceWearerService(@Autowired private val deviceWearerRepository: DeviceWearerRepository) :
  IDeviceWearerService {
  override fun getAllDeviceWearers(): List<DeviceWearer> {
    return deviceWearerRepository!!.findAll().toList()
  }
}
