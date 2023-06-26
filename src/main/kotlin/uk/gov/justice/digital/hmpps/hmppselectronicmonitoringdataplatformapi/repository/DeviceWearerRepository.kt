package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
@Repository
interface DeviceWearerRepository : JpaRepository<DeviceWearer, Int> {
  fun findByDeviceWearerId(id: String): DeviceWearer?
  //  @Query(value = "SELECT * FROM DeviceWearer", nativeQuery = true)
  @Query(
    value = "SELECT * FROM device_wearer u WHERE u.first_name = 'Jane'",
    nativeQuery = true,
  )
  fun findByDeviceWearerIdSearch(@Param("firstName") firstName: String): List<DeviceWearer>?


  @Query(
    value = "SELECT * FROM device_wearer u\n" +
      "    WHERE (LOWER(u.first_name) Like CONCAT('%',:parameter,'%')) OR\n" +
      "    (LOWER(u.last_name) Like CONCAT('%',:parameter,'%')) OR\n" +
      "    (LOWER(u.type) Like CONCAT('%',:parameter,'%')) OR\n" +
      "    (LOWER(u.device_wearer_id) Like CONCAT('%',:parameter,'%'))",
    nativeQuery = true,
  )
  fun searchDatabase(@Param("parameter") parameter: String): List<DeviceWearer>?

}

