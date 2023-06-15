package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration

import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.DeviceWearerRepository

class DeviceWearerSeeds(private val repository: DeviceWearerRepository) {
  fun insertMultipleDeviceWearers() {
    repository.saveAll(
      listOf(
        DeviceWearer(
          id = 1,
          deviceWearerId = "1",
          firstName = "Godofredo",
          lastName = "Agodofredado",
          type = "type1",
        ),
        DeviceWearer(
          id = 2,
          deviceWearerId = "2",
          firstName = "John",
          lastName = "Smith",
          type = "type2",
        ),
        DeviceWearer(
          id = 3,
          deviceWearerId = "3",
          firstName = "Oliver",
          lastName = "Brown",
          type = "type3",
        ),
      ),
    )
  }
}
