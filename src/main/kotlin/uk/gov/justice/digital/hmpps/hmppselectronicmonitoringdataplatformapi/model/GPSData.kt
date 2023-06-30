package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "gps_data")
data class GPSData(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int = 0,
  val deviceWearerId: String = "",
  val latitude: Double = 0.0,
  val longitude: Double = 0.0,
//    @Schema(description = "Date time if discovery date different to incident date", example = "2010-10-12T10:00:00")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val locationTime: Date = Date(Long.MIN_VALUE),

)
