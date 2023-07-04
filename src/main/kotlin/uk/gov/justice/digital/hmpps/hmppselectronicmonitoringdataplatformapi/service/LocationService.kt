package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.LocationRepository
import java.util.*

interface ILocationService {
  fun getAllLocations(): List<Location>
  fun getAllLocationsForDeviceWearer(deviceWearerId: String): List<Location>

  fun getAllLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId: String, startDate: Date, endDate: Date): List<Location>
}

@Service
class LocationService(@Autowired private val locationRepository: LocationRepository) :
  ILocationService {
  override fun getAllLocations(): List<Location> {
    return locationRepository.findAll().toList()
  }
  override fun getAllLocationsForDeviceWearer(deviceWearerId: String): List<Location> {
    return locationRepository.findLocationsByDeviceWearerId(deviceWearerId) ?: listOf()
  }
  override fun getAllLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId: String, startDate: Date, endDate: Date): List<Location> {
    // val diff = endDate.time - startDate.time
   // println("difference between dates $diff")
    return locationRepository.findLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate) ?: listOf()
  }
}



