package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.GPSDataRepository

class LocationServiceTest {
  @Test
  fun `getAllGPSData should call to fetch all data from the database`() {
    val gpsDataRepository = Mockito.mock(GPSDataRepository::class.java)
    val locationService = LocationService(gpsDataRepository)
    locationService.getAllGPSData()
    verify(gpsDataRepository, times(1)).findAll()
  }
}
