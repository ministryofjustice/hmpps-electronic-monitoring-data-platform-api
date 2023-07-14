package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.DateConverter
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.LocationAggregation
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.LocationRepository

class LocationServiceTest {
  val locationRepository = Mockito.mock(LocationRepository::class.java)

  @Test
  fun `getAllLocations should call to fetch all data from the database`() {
    val locationService = LocationService(locationRepository)
    locationService.getAllLocations()
    verify(locationRepository, times(1)).findAll()
  }

  @Test
  fun `getLocationsByDeviceWearer should call findByDeviceWearerId to fetch data from the database and return an empty list if no matches`() {
    val locationService = LocationService(locationRepository)
    val deviceWearerId = "test device wearer id"

    val expectedResult: List<Location> = listOf()
    val result: List<Location> = locationService.getLocationsByDeviceWearerId(deviceWearerId)

    verify(locationRepository, times(1)).findLocationsByDeviceWearerId(deviceWearerId)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }

  @Test
  fun `getAllLocationsByDeviceWearerIdAndTimeFrame should call findLocationsByDeviceWearerIdAndTimeFrame to fetch data from the database and return an empty list if no matches`() {
    val locationService = LocationService(locationRepository)
    val deviceWearerId = "test device wearer id"
    val startDate = DateConverter().convertFromStringToDate("2000-11-30T01:32:00.000-00:00")
    val endDate = DateConverter().convertFromStringToDate("2000-12-10T01:32:00.000-00:00")

    val expectedResult: List<Location> = listOf()
    val result: List<Location> =
      locationService.getLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate)

    verify(locationRepository, times(1)).findLocationsByDeviceWearerIdAndTimeFrame(deviceWearerId, startDate, endDate)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }

  @Test
  fun `getLocationsByDevice should call findByDeviceId to fetch data from the database and return an empty list if no matches`() {
    val locationService = LocationService(locationRepository)
    val deviceId = "test device id"

    val expectedResult: List<Location> = listOf()
    val result: List<Location> = locationService.getLocationsByDeviceId(deviceId)

    verify(locationRepository, times(1)).findLocationsByDeviceId(deviceId)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }

  @Test
  fun `getLocationsByDeviceIdAndTimeFrame should call findLocationsByDeviceIdAndTimeFrame to fetch data from the database and return an empty list if no matches`() {
    val locationService = LocationService(locationRepository)
    val deviceId = "test device id"
    val startDate = DateConverter().convertFromStringToDate("2000-11-30T01:32:00.000-00:00")
    val endDate = DateConverter().convertFromStringToDate("2000-11-30T01:32:00.000-00:00")

    val expectedResult: List<Location> = listOf()
    val result: List<Location> = locationService.getLocationsByDeviceIdAndTimeFrame(deviceId, startDate, endDate)

    verify(locationRepository, times(1)).findLocationsByDeviceIdAndTimeFrame(deviceId, startDate, endDate)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }

  @Test
  fun `aggregateLocationsByDeviceIdAndTimeFrameAndDuration should call aggregateLocationsByDeviceIdAndTimeFrameAndDuration to fetch data from the database and return an empty list if no matches`() {
    val locationService = LocationService(locationRepository)
    val deviceId = "test device id"
    val startDate = DateConverter().convertFromStringToDate("2000-11-30T01:32:00.000-00:00")
    val endDate = DateConverter().convertFromStringToDate("2000-11-30T01:32:00.000-00:00")
    val duration = 1

    val expectedResult: List<Location> = listOf()
    val result: List<LocationAggregation> = locationService.aggregateLocationsByDeviceIdAndTimeFrameAndDuration(deviceId, startDate, endDate, duration)

    verify(locationRepository, times(1)).aggregateLocationsByDeviceIdAndTimeFrameAndDuration(deviceId, startDate, endDate, duration)
    Assertions.assertThat(result).isEqualTo(expectedResult)
  }
}

