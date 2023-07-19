package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.testcontainers.shaded.org.apache.commons.io.IOUtils
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Device
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.Location
import java.nio.charset.StandardCharsets

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
    val dateTime = DateConverter().convertFromStringToDate("2000-10-31T01:30:00.000-00:00")
    val location1 = Location(1, device, 20.0, 20.0, locationTime = dateTime)
    val location2 = Location(2, device, 25.0, 10.0, locationTime = dateTime)

    val expected = "device,id,latitude,locationTime,longitude\r\n" +
      "myDeviceId,1,20.0,Tue Oct 31 01:30:00 GMT 2000,20.0\r\n" +
      "myDeviceId,2,25.0,Tue Oct 31 01:30:00 GMT 2000,10.0\r\n"

    val result = CSVHelper().convertToCSV(listOf(location1, location2))
    val parseRes = IOUtils.toString(result, StandardCharsets.UTF_8)
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
    val dateTime = DateConverter().convertFromStringToDate("2000-10-31T01:30:00.000-00:00")
    val location1 = Location(1, device, 20.0, 20.0, locationTime = dateTime)
    val expected = "device,id,latitude,locationTime,longitude\r\n" +
      "myDeviceId,1,20.0,Tue Oct 31 01:30:00 GMT 2000,20.0\r\n"

    val result = CSVHelper().convertToCSV(listOf(location1))
    val parseRes = IOUtils.toString(result, StandardCharsets.UTF_8)
    Assertions.assertThat(parseRes).isEqualTo(expected)

  }

  @Test
  fun `It does not convert location to CSV format due to internal server issue`() {
    val location = Location()

//    assertThrows<Exception> { LocationController(locationService).getAllLocations() }
    // val convertToCSV = Mockito.mock(CSVHelper::class.java)
    Mockito.`when`(CSVHelper().convertToCSV(listOf(location))).thenThrow(RuntimeException("Exception"))
//    val result = CSVHelper().convertToCSV(listOf(location))
//    val expected = RuntimeException("fail to import data to CSV file: ")
    assertThrows<Exception> { CSVHelper().convertToCSV(listOf(location)) }
    // verify(locationService, times(1)).getLocationsByDeviceWearerId(any<String>())

    // Assertions.assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `it converts devices to CSV format`() {
    val device = Device(
      id = 1,
      deviceId = "myDeviceId",
      modelId = "XYZ",
      firmwareVersion = "739",
      deviceType = "testType",
      status = "itsOK",
      batteryLifeRemaining = 20,
    )


    val expected = "batteryLifeRemaining,dateTagFitted,dateTagRemoved,deviceId,deviceType,deviceWearer,firmwareVersion,id,locations,modelId,status\r\n" +
      "20,Sun Dec 02 16:47:04 GMT 292269055,Sun Dec 02 16:47:04 GMT 292269055,myDeviceId,testType,null,739,1,[],XYZ,itsOK\r\n"

    val result = CSVHelper().convertToCSV(listOf(device))
    val parseRes = IOUtils.toString(result, StandardCharsets.UTF_8)
    Assertions.assertThat(parseRes).isEqualTo(expected)
  }
}