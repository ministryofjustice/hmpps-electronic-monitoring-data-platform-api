package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.DateConverter

class IConvertableTest {

  fun getMyProperties(convertableObject: IConvertable): List<Pair<String, String>>{
    return convertableObject.getProperties()
  }
  @Test
  fun `getProperties will get properties for a location`() {
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
    val location = Location(1, device, 20.0, 20.0, locationTime = dateTime)
    val expected = listOf(
      "device" to "myDeviceId",
      "id" to "1",
      "latitude" to "20.0",
      "locationTime" to "Tue Oct 31 01:30:00 GMT 2000",
      "longitude" to "20.0",
    )

    val result = getMyProperties(location)

    Assertions.assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `getProperties will get properties for a locationAggregation`() {

    val dateTime = DateConverter().convertFromStringToDate("2000-10-31T01:30:00.000-00:00")
    val locationAggregation = LocationAggregation(20.0, 20.0, dateTime)
    val expected = listOf(

      "datetime" to "Tue Oct 31 01:30:00 GMT 2000",
      "latitude" to "20.0",
      "longitude" to "20.0",
    )

    val result = getMyProperties(locationAggregation)

    Assertions.assertThat(result).isEqualTo(expected)
  }
}