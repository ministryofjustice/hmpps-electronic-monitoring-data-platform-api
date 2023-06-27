package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "gps_data")
data class GPSData(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: UUID = UUID.randomUUID(),
  val deviceWearerId: String = "",
  val latitude: Double = 0.0,
  val longitude: Double = 0.0,
  val locationTime: Date = Date(Long.MIN_VALUE),
)
