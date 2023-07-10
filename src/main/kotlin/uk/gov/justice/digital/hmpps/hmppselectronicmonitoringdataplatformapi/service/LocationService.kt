package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.LocationRepository
import java.util.*

interface ILocationService {
  fun getAllLocations(): List<Location>
  fun getLocationsByDeviceWearerId(deviceWearerId: String): List<Location>

  fun getLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId: String, startDate: Date, endDate: Date): List<Location>

  fun getLocationsByDeviceId(deviceId: String): List<Location>

  fun getLocationsByDeviceIdAndTimeFrame(deviceId: String, startDate: Date, endDate: Date): List<Location>

  fun aggregateLocationsByDeviceIdAndTimeFrameAndDuration(deviceId: String, startDate: Date, endDate: Date, duration: Int): List<Location>
}

@Service
class LocationService(@Autowired private val locationRepository: LocationRepository) :
  ILocationService {
  override fun getAllLocations(): List<Location> {
    return locationRepository.findAll().toList()
  }
  override fun getLocationsByDeviceWearerId(deviceWearerId: String): List<Location> {
    return locationRepository.findLocationsByDeviceWearerId(deviceWearerId) ?: listOf()
  }
  override fun getLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId: String, startDate: Date, endDate: Date): List<Location> {
    return locationRepository.findLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate) ?: listOf()
  }
  override fun getLocationsByDeviceId(deviceId: String): List<Location> {
    return locationRepository.findLocationsByDeviceId(deviceId) ?: listOf()
  }
  override fun getLocationsByDeviceIdAndTimeFrame(deviceId: String, startDate: Date, endDate: Date): List<Location> {
    return locationRepository.findLocationsByDeviceIdAndTimeFrame(deviceId, startDate, endDate) ?: listOf()
  }
  override fun aggregateLocationsByDeviceIdAndTimeFrameAndDuration(deviceId: String, startDate: Date, endDate: Date, duration: Int): List<Location> {
    return locationRepository.aggregateLocationsByDeviceIdAndTimeFrameAndDuration(deviceId, startDate, endDate, duration) ?: listOf()
  }
}



