package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.LocationRepository

class LocationServiceTest {
  @Test
  fun `getAllGPSData should call to fetch all data from the database`() {
    val gpsDataRepository = Mockito.mock(LocationRepository::class.java)
    val locationService = LocationService(gpsDataRepository)
    locationService.getAllLocationData()
    verify(gpsDataRepository, times(1)).findAll()
  }

  @Test
  fun `getGPSDataForUser should call findByDeviceWearerId to fetch data from the database and return an empty list if no matches`() {
    val gpsDataRepository = Mockito.mock(LocationRepository::class.java)
    val locationService = LocationService(gpsDataRepository)
    val userId = "test user ID"
    val expectedResult: List<Location> = listOf()

    val result: List<Location> = locationService.getAllLocationDataForDeviceWearer(userId)

    verify(gpsDataRepository, times(1)).findLocationByDeviceWearerId(userId)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }
}
