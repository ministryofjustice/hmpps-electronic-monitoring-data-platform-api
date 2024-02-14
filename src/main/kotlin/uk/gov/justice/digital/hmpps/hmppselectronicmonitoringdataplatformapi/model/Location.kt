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
import jakarta.persistence.Table
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime
import kotlin.reflect.full.memberProperties

@Entity
@Table(name = "location")
data class Location(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int = 0,
  @JsonIgnore
  @ManyToOne(cascade = [(CascadeType.REMOVE)], fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "device_id")
  val device: Device? = null,
  val latitude: Double = 0.0,
  val longitude: Double = 0.0,
//    @Schema(description = "Date time if discovery date different to incident date", example = "2010-10-12T10:00:00")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val locationTime: ZonedDateTime = ZonedDateTime.now(),

) : IConvertable {
  override fun getProperties(): List<Pair<String, String>> {
    val result = Location::class.memberProperties.map { it.name to it.get(this).toString() }
    val deviceId: String = device?.deviceId ?: ""
    val newList: List<Pair<String, String>> = result.map {
      if (it.first == "device") {
        "device" to deviceId
      } else {
        it.first to it.second
      }
    }
    return newList
  }
}
