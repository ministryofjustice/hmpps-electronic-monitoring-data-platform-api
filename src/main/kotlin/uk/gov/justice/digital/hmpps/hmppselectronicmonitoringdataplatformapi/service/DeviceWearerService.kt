package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceWearerRepository
interface IDeviceWearerService {
  fun getAllDeviceWearers(): List<DeviceWearer>
  fun getMatchingDeviceWearers(parameter: String): List<DeviceWearer>?
  fun getDeviceWearerById(id: String): DeviceWearer?
  fun createDummyDeviceWearers(): List<DeviceWearer>
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
  override fun createDummyDeviceWearers(): List<DeviceWearer> {
    val deviceWearer1 = DeviceWearer(deviceWearerId = "abc123UID", firstName = "Billiam", lastName = "Doors", type = "Dummy")
    deviceWearerRepository.save(deviceWearer1)
    val deviceWearer2 = DeviceWearer(deviceWearerId = "UID456784", firstName = "John", lastName = "Smith", type = "Dummy")
    deviceWearerRepository.save(deviceWearer2)
    return deviceWearerRepository.findAll().toList()
  }
}
