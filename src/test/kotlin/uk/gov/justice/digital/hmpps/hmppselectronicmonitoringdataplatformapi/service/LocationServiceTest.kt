package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.DateConverter
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.LocationRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class LocationServiceTest {
  @Test
  fun `getAllLocations should call to fetch all data from the database`() {
    val locationRepository = Mockito.mock(LocationRepository::class.java)
    val locationService = LocationService(locationRepository)
    locationService.getAllLocations()
    verify(locationRepository, times(1)).findAll()
  }

  @Test
  fun `getLocationsForDeviceWearer should call findByDeviceWearerId to fetch data from the database and return an empty list if no matches`() {
    val locationRepository = Mockito.mock(LocationRepository::class.java)
    val locationService = LocationService(locationRepository)
    val deviceWearerId = "test user ID"
    val expectedResult: List<Location> = listOf()

    val result: List<Location> = locationService.getAllLocationsForDeviceWearer(deviceWearerId)

    verify(locationRepository, times(1)).findLocationsByDeviceWearerId(deviceWearerId)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }

  @Test
  fun `getAllLocationsByDeviceWearerIdAndTimeFrame should call findLocationsByDeviceWearerIdAndTimeFrame to fetch data from the database and return an empty list if no matches`() {
    val locationRepository = Mockito.mock(LocationRepository::class.java)
    val locationService = LocationService(locationRepository)
    val deviceWearerId = "test user ID"
    val startDate = DateConverter().convertToDateViaInstant(LocalDateTime.parse("2000-11-30T01:32:00"))
    val endDate = DateConverter().convertToDateViaInstant(LocalDateTime.parse("2000-12-10T01:32:00"))
    val expectedResult: List<Location> = listOf()

    val result: List<Location> = locationService.getAllLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate)

    verify(locationRepository, times(1)).findLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }
}

