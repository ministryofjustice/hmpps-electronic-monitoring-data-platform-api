package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.GPSData

interface GPSDataRepository : CrudRepository<GPSData, Int> {

  @Query(
    value = "SELECT * FROM device_wearer u WHERE u.first_name = 'Jane'",
    nativeQuery = true,
  )
  fun findByDeviceWearerId(@Param("deviceWearerId") deviceWearerId: String): List<GPSData>?
}
