package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

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
    val dateTime = ZonedDateTime.parse("2000-10-31T01:30:00.000-00:00")
    val location = Location(1, device, 20.0, 20.0, locationTime = dateTime)
    val expected = listOf(
      "device" to "myDeviceId",
      "id" to "1",
      "latitude" to "20.0",
      "locationTime" to "2000-10-31T01:30Z",
      "longitude" to "20.0",
    )

    val result = getMyProperties(location)
    val resultInUTC = result.map {
      if (it.first == "locationTime") {
        it.first to it.second.replace("GMT", "UTC")
      } else {
        it
      }
    }

    Assertions.assertThat(resultInUTC).isEqualTo(expected)
  }

  @Test
  fun `getProperties will get properties for a locationAggregation`() {
    val dateTime = ZonedDateTime.parse("2000-10-31T01:30:00.000-00:00")
    val locationAggregation = LocationAggregation(20.0, 20.0, dateTime)
    val expected = listOf(

      "datetime" to "2000-10-31T01:30Z",
      "latitude" to "20.0",
      "longitude" to "20.0",
    )

    val result = getMyProperties(locationAggregation)
    val resultInUTC = result.map {
      if (it.first == "datetime") {
        it.first to it.second.replace("GMT", "UTC")
      } else {
        it
      }
    }

    Assertions.assertThat(resultInUTC).isEqualTo(expected)
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
        dateTagRemoved = ZonedDateTime.parse("2000-10-31T01:30:00.000-00:00"),
        dateTagFitted = ZonedDateTime.parse("2000-10-31T01:30:00.000-00:00"),
    )

    val expected = listOf(
      "batteryLifeRemaining" to "20",
      "dateTagFitted" to "2000-10-31T01:30Z",
      "dateTagRemoved" to "2000-10-31T01:30Z",
      "deviceId" to "myDeviceId",
      "deviceType" to "deviceType",
      "deviceWearer" to "0",
      "firmwareVersion" to "firmwareVersion",
      "id" to "1",
      "modelId" to "modelId",
      "status" to "status",
    )

    val result = getMyProperties(device)
    val resultInUTC = result.map {
      if (it.first == "dateTagFitted" || it.first == "dateTagRemoved") {
        it.first to it.second.replace("GMT", "UTC")
      } else {
        it
      }
    }
    Assertions.assertThat(resultInUTC).isEqualTo(expected)
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
