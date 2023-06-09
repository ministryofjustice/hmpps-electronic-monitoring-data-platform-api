package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device

@Repository
interface DeviceRepository : JpaRepository<Device, Int> {

  @Query(
    value = "SELECT DISTINCT d.*" +
      "FROM device as d JOIN device_wearer as dw ON d.device_wearer_id = dw.id" +
      " WHERE dw.device_wearer_id = :deviceWearerId",

    nativeQuery = true,
  )

  fun findDevicesByDeviceWearerId(deviceWearerId: String): List<Device>?

  @Query(
    value = "SELECT d.*" +
      "FROM device as d" +
      " WHERE d.device_id = :deviceId",
    nativeQuery = true,
  )
  fun findDeviceByDeviceId(@Param("deviceId") deviceId: String): List<Device>?


}
