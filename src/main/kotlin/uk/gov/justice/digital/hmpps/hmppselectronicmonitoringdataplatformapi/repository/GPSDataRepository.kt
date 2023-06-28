package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.GPSData

@Repository
interface GPSDataRepository : JpaRepository<GPSData, Int> {

  @Query(
    value = "SELECT * FROM device_wearer u WHERE u.first_name = 'Jane'",
    nativeQuery = true,
  )
  fun findByDeviceWearerId(@Param("deviceWearerId") deviceWearerId: String): List<GPSData>?
}
