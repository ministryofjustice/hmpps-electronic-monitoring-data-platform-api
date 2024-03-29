package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.LocationAggregation
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.LocationRepository
import java.time.ZonedDateTime

interface ILocationService {
  fun getAllLocations(): List<Location>
  fun getLocationsByDeviceWearerId(deviceWearerId: String): List<Location>

  fun getLocationsByDeviceWearerIdAndTimeFrame(
    deviceWearerId: String,
    startDate: ZonedDateTime,
    endDate: ZonedDateTime,
  ): List<Location>

  fun getLocationsByDeviceId(deviceId: String): List<Location>

  fun getLocationsByDeviceIdAndTimeFrame(
    deviceId: String,
    startDate: ZonedDateTime,
    endDate: ZonedDateTime,
  ): List<Location>

  fun aggregateLocationsByDeviceIdAndTimeFrameAndDuration(
    deviceId: String,
    startDate: ZonedDateTime,
    endDate: ZonedDateTime,
    duration: Int,
  ): List<LocationAggregation>

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

  override fun getLocationsByDeviceWearerIdAndTimeFrame(
    deviceWearerId: String,
    startDate: ZonedDateTime,
    endDate: ZonedDateTime,
  ): List<Location> {
    return locationRepository.findLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate) ?: listOf()
  }

  override fun getLocationsByDeviceId(deviceId: String): List<Location> {
    return locationRepository.findLocationsByDeviceId(deviceId) ?: listOf()
  }

  override fun getLocationsByDeviceIdAndTimeFrame(
    deviceId: String,
    startDate: ZonedDateTime,
    endDate: ZonedDateTime,
  ): List<Location> {
    return locationRepository.findLocationsByDeviceIdAndTimeFrame(deviceId, startDate, endDate) ?: listOf()
  }

  override fun aggregateLocationsByDeviceIdAndTimeFrameAndDuration(
    deviceId: String,
    startDate: ZonedDateTime,
    endDate: ZonedDateTime,
    duration: Int,
  ): List<LocationAggregation> {
    val repositoryResponse = locationRepository.aggregateLocationsByDeviceIdAndTimeFrameAndDuration(
      deviceId,
      startDate,
      endDate,
      duration,
    ) ?: listOf()
    val result = repositoryResponse.map { LocationAggregation(it.latitude, it.longitude, it.datetime) }
    return result
  }
}



