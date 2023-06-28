package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceWearerRepository
interface IDeviceWearerService {
  fun getAllDeviceWearers(): List<DeviceWearer>
  fun getMatchingDeviceWearers(parameter: String): List<DeviceWearer>?
  fun getDeviceWearerById(id: String): DeviceWearer?
}

@Service
class DeviceWearerService(@Autowired private val deviceWearerRepository: DeviceWearerRepository) :
  IDeviceWearerService {
  override fun getAllDeviceWearers(): List<DeviceWearer> {
    return deviceWearerRepository.findAll().toList()
  }
  override fun getMatchingDeviceWearers(parameter: String): List<DeviceWearer>? {
    return deviceWearerRepository.searchDatabase(parameter)
  }
  override fun getDeviceWearerById(id: String): DeviceWearer? {
    return deviceWearerRepository.findByDeviceWearerId(id)
  }
}
