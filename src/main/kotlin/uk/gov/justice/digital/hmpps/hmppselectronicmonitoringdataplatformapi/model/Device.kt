package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

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
  val dateTagFitted: Date = Date(Long.MIN_VALUE),

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val dateTagRemoved: Date? = Date(Long.MIN_VALUE),

  @ManyToOne(cascade = [(CascadeType.REMOVE)], fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "device_wearer_id")
  val deviceWearer: DeviceWearer? = null
)




