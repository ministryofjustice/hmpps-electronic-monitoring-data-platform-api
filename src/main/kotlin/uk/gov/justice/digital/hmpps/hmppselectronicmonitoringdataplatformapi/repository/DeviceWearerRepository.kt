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

//  fun generateSearchQuery(searchParameter: String): String {
//    return """SELECT * FROM device_wearer u
//    WHERE (LOWER(u.first_name) Like '%${searchParameter}%') OR
//    (LOWER(u.last_name) Like '%${searchParameter}%') OR
//    (LOWER(u.type) Like '%${searchParameter}%') OR
//    (LOWER(u.device_wearer_id) Like '%${searchParameter}%')"""
//  }
//
//  fun find(searchParameter: String): List<DeviceWearer>? {
//    val query = generateSearchQuery(searchParameter)
//    return searchDatabase(query)
//  }


}

//@Query(
//  value = """SELECT * FROM device_wearer u WHERE u.first_name = Jane""",
//  nativeQuery = true)
//fun findByDeviceWearerIdSearch1(@Param("first_Name") first_Name: String): List<DeviceWearer>?
//}
//@Query(
//  value = "select * from reported_adjudications ra " +
//    "where ra.date_time_of_discovery > :startDate and ra.date_time_of_discovery <= :endDate " +
//    "and (ra.originating_agency_id = :agencyId or ra.override_agency_id = :agencyId)",
//  nativeQuery = true,
//)
