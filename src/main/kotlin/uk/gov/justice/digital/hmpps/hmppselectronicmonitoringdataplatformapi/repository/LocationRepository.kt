package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location

@Repository
interface LocationRepository : JpaRepository<Location, Int> {
  @Query(
    value = "SELECT DISTINCT l.*" +
      "FROM location as l JOIN device as d ON l.device_id = d.id" +
      " JOIN device_wearer as dw ON dw.id = d.device_wearer_id " +
      " WHERE dw.device_wearer_id = :deviceWearerId",
    nativeQuery = true,
  )
  fun findLocationByDeviceWearerId(@Param("deviceWearerId") deviceWearerId: String): List<Location>?
}
