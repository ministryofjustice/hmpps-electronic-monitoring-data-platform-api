package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.GPSData

@Repository
interface GPSDataRepository : JpaRepository<GPSData, Int> {

  @Query(
    value = "SELECT * FROM gps_data u WHERE u.device_wearer_id = :deviceWearerId",
    nativeQuery = true,
  )
  fun findByDeviceWearerId(@Param("deviceWearerId") deviceWearerId: String): List<GPSData>?
}
