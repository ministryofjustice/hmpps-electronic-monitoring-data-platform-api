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
  fun `getAllLocations should call to fetch all data from the database`() {
    val locationRepository = Mockito.mock(LocationRepository::class.java)
    val locationService = LocationService(locationRepository)
    locationService.getAllLocationData()
    verify(locationRepository, times(1)).findAll()
  }

  @Test
  fun `getLocationsForDeviceWearer should call findByDeviceWearerId to fetch data from the database and return an empty list if no matches`() {
    val locationRepository = Mockito.mock(LocationRepository::class.java)
    val locationService = LocationService(locationRepository)
    val userId = "test user ID"
    val expectedResult: List<Location> = listOf()

    val result: List<Location> = locationService.getAllLocationDataForDeviceWearer(userId)

    verify(locationRepository, times(1)).findLocationsByDeviceWearerId(userId)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }
}
