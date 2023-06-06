package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "DeviceWearer")
data class DeviceWearer(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int = 0,
  val deviceWearerId: Int = 0,
  val firstName: String = "",
  val lastName: String = "",
  val type: String = "",
)
