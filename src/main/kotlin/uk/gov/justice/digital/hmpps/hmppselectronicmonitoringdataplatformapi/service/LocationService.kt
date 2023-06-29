package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.GPSData
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.GPSDataRepository

interface ILocationService {
  fun getAllGPSData(): List<GPSData>
  fun getGPSDataForUser(deviceWearerId: String): List<GPSData>
}

@Service
class LocationService(@Autowired private val gpsDataRepository: GPSDataRepository) :
  ILocationService {
  override fun getAllGPSData(): List<GPSData> {
    return gpsDataRepository.findAll().toList()
  }
  override fun getGPSDataForUser(deviceWearerId: String): List<GPSData> {
    return gpsDataRepository.findByDeviceWearerId(deviceWearerId) ?: listOf()
  }
}
