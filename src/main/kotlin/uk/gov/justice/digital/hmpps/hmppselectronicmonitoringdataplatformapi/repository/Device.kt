package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device

@Repository
interface DeviceRepository : JpaRepository<Device, Int> {

}
