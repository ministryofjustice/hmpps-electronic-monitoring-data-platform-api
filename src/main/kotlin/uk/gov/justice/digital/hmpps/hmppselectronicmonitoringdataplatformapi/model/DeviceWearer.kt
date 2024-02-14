package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import kotlin.reflect.full.memberProperties

@Entity
@Table(name = "device_wearer")
data class DeviceWearer(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int = 0,
  val deviceWearerId: String = "0",
  val firstName: String = "",
  val lastName: String = "",
  val type: String = "",

) : IConvertable {

  @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "deviceWearer")
  @JsonIgnore
  var devices = listOf<Device>()

  override fun getProperties(): List<Pair<String, String>> {
    val result = DeviceWearer::class.memberProperties.map { it.name to it.get(this).toString() }
    val listOfDeviceId = devices.map { it.deviceId }.toString()

    val newList: List<Pair<String, String>> = result.map {
      if (it.first == "devices") {
        "devices" to listOfDeviceId
      } else {
        it
      }
    }

    return newList
  }
}
