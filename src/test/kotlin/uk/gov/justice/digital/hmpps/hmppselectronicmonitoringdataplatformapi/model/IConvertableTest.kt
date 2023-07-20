package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helpers.DateConverter

class IConvertableTest {

  fun getMyProperties(convertableObject: IConvertable): List<Pair<String, String>> {

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

  @Test
  fun `getProperties will get device info`() {

    val deviceWearerId = DeviceWearer(
      id = 0,
      deviceWearerId = "0",
      firstName = "",
      lastName = "",
      type = "",
    )
    val device = Device(
      id = 1,
      deviceId = "myDeviceId",
      modelId = "modelId",
      firmwareVersion = "firmwareVersion",
      deviceType = "deviceType",
      status = "status",
      batteryLifeRemaining = 20,
      deviceWearer = deviceWearerId,
    )


    val expected = listOf(
      "batteryLifeRemaining" to "20",
      "dateTagFitted" to "Sun Dec 02 16:47:04 GMT 292269055",
      "dateTagRemoved" to "Sun Dec 02 16:47:04 GMT 292269055",
      "deviceId" to "myDeviceId",
      "deviceType" to "deviceType",
      "deviceWearer" to "0",
      "firmwareVersion" to "firmwareVersion",
      "id" to "1",
      "modelId" to "modelId",
      "status" to "status",
    )

    val result = getMyProperties(device)

    Assertions.assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `getProperties will get deviceWearer info`() {

    val deviceWearerId = DeviceWearer(
      id = 123,
      deviceWearerId = "345",
      firstName = "Mark",
      lastName = "Andreas",
      type = "chip",
    )

    val devices = listOf(
      Device(
        id = 1,
        deviceId = "myDeviceId",
        modelId = "modelId",
        firmwareVersion = "firmwareVersion",
        deviceType = "deviceType",
        status = "status",
        batteryLifeRemaining = 20,
        deviceWearer = deviceWearerId,
      ),
      Device(
        id = 2,
        deviceId = "myOtherDeviceId",
        modelId = "modelId",
        firmwareVersion = "firmwareVersion",
        deviceType = "deviceType",
        status = "status",
        batteryLifeRemaining = 20,
        deviceWearer = deviceWearerId,
      ),

      )
    deviceWearerId.devices = devices

    val expected = listOf(
      "deviceWearerId" to "345",
      "devices" to "[myDeviceId, myOtherDeviceId]",
      "firstName" to "Mark",
      "id" to 123,
      "lastName" to "Andreas",
      "type" to "chip",
    )

    val result = getMyProperties(deviceWearerId)

    Assertions.assertThat(result.toString()).isEqualTo(expected.toString())
  }

}