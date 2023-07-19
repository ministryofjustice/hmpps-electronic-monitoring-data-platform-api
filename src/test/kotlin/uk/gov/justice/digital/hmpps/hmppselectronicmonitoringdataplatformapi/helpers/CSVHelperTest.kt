package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location

class CSVHelperTest {

  @Test
  fun `It converts locations to csv format` () {
    val device = Device(
      id = 1,
      deviceId = "myDeviceId",
      modelId = "modelId",
      firmwareVersion = "firmwareVersion",
      deviceType = "deviceType",
      status = "status",
      batteryLifeRemaining = 20,
    )
    val dateTime = DateConverter().convertFromStringToDate("2000-10-31T01:30:00.000-00:00")
    val location1 = Location(1, device, 20.0, 20.0, locationTime = dateTime)
    val location2 = Location(2, device, 25.0, 10.0, locationTime = dateTime)

    val result = CSVHelper().convertToCSV(listOf(location1, location2))

    Assertions.assertThat(0).isEqualTo(1)

  }
}