package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.testcontainers.shaded.org.apache.commons.io.IOUtils
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.DeviceWearer
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import java.nio.charset.StandardCharsets
import java.time.ZonedDateTime

class CSVHelperTest {

  @Test
  fun `It converts locations to csv format`() {
    val device = Device(
      id = 1,
      deviceId = "myDeviceId",
      modelId = "modelId",
      firmwareVersion = "firmwareVersion",
      deviceType = "deviceType",
      status = "status",
      batteryLifeRemaining = 20,
    )
    val dateTime = ZonedDateTime.parse("2000-10-31T01:30:00.000-00:00")
    val location1 = Location(1, device, 20.0, 20.0, locationTime = dateTime)
    val location2 = Location(2, device, 25.0, 10.0, locationTime = dateTime)

    val expected = "device,id,latitude,locationTime,longitude\r\n" +
      "myDeviceId,1,20.0,2000-10-31T01:30Z,20.0\r\n" +
      "myDeviceId,2,25.0,2000-10-31T01:30Z,10.0\r\n"

    val result = CSVHelper().convertToCSV(listOf(location1, location2))
    val parseRes = IOUtils.toString(result, StandardCharsets.UTF_8).replace("GMT", "UTC")
    Assertions.assertThat(parseRes).isEqualTo(expected)
  }

  @Test
  fun `It converts location to csv format and returns one row`() {
    val device = Device(
      id = 1,
      deviceId = "myDeviceId",
      modelId = "modelId",
      firmwareVersion = "firmwareVersion",
      deviceType = "deviceType",
      status = "status",
      batteryLifeRemaining = 20,
    )
    val dateTime = ZonedDateTime.parse("2000-10-31T01:30:00.000-00:00")
    val location1 = Location(1, device, 20.0, 20.0, locationTime = dateTime)
    val expected = "device,id,latitude,locationTime,longitude\r\n" +
      "myDeviceId,1,20.0,2000-10-31T01:30Z,20.0\r\n"

    val result = CSVHelper().convertToCSV(listOf(location1))
    val parseRes = IOUtils.toString(result, StandardCharsets.UTF_8).replace("GMT", "UTC")
    Assertions.assertThat(parseRes).isEqualTo(expected)
  }

  @Test
  fun `it converts devices to CSV format`() {
    val dateTime = ZonedDateTime.parse("2000-10-31T01:30:00.000-00:00")
    val device = Device(
      deviceWearer = DeviceWearer(),
      id = 1,
      deviceId = "myDeviceId",
      modelId = "XYZ",
      firmwareVersion = "739",
      deviceType = "testType",
      status = "itsOK",
      batteryLifeRemaining = 20,
      dateTagFitted = dateTime,
      dateTagRemoved = dateTime,
    )

    val expected =
      "batteryLifeRemaining,dateTagFitted,dateTagRemoved,deviceId,deviceType,deviceWearer,firmwareVersion,id,modelId,status\r\n" +
        "20,2000-10-31T01:30Z,2000-10-31T01:30Z,myDeviceId,testType,0,739,1,XYZ,itsOK\r\n"

    val result = CSVHelper().convertToCSV(listOf(device))
    val parseRes = IOUtils.toString(result, StandardCharsets.UTF_8).replace("GMT", "UTC")
    Assertions.assertThat(parseRes).isEqualTo(expected)
  }
}
