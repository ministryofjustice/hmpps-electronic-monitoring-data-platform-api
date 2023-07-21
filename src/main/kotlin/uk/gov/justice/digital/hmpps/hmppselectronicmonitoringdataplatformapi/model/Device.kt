package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime
import kotlin.reflect.full.memberProperties

@Entity
@Table(name = "device")
data class Device(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int = 0,
  val deviceId: String = "0",
  val modelId: String = "",
  val firmwareVersion: String = "0",
  val deviceType: String = "",
  val status: String = "",
  val batteryLifeRemaining: Int = 0,

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val dateTagFitted: ZonedDateTime = ZonedDateTime.now(),

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val dateTagRemoved: ZonedDateTime? = ZonedDateTime.now(),

  @JsonIgnore
  @ManyToOne(cascade = [(CascadeType.REMOVE)], fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "device_wearer_id")
  val deviceWearer: DeviceWearer? = null,
) : IConvertable {
  @JsonIgnore
  @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "device")
  val locations = listOf<Location>()

  override fun getProperties(): List<Pair<String, String>> {
    val result = Device::class.memberProperties.map {
      it.name to it.get(this).toString()
    }
    val deviceWearerId: String = deviceWearer?.deviceWearerId ?: ""
    val wearerInfo = result.map {
      if (it.first == "deviceWearer") {
        "deviceWearer" to deviceWearerId
      } else it.first to it.second
    }

    val testResult = wearerInfo.filter { it.first != "locations" }.toList()
    return testResult
  }
}
