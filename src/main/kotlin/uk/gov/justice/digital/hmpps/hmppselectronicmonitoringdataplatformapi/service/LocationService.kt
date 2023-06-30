package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.LocationRepository

interface ILocationService {
  fun getAllLocationData(): List<Location>
  fun getAllLocationDataForDeviceWearer(deviceWearerId: String): List<Location>
}

@Service
class LocationService(@Autowired private val locationRepository: LocationRepository) :
  ILocationService {
  override fun getAllLocationData(): List<Location> {
    return locationRepository.findAll().toList()
  }
  override fun getAllLocationDataForDeviceWearer(deviceWearerId: String): List<Location> {
    return locationRepository.findLocationByDeviceWearerId(deviceWearerId) ?: listOf()
  }
}
