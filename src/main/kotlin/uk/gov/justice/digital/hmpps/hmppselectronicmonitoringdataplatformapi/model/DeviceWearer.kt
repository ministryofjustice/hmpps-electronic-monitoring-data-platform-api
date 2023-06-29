package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import jakarta.persistence.*

@Entity
@Table(name = "device_wearer")
data class DeviceWearer(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Int = 0,
  val deviceWearerId: String = "0",
  val firstName: String = "",
  val lastName: String = "",
  val type: String = ""
)
{
  @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "deviceWearer")
  val devices = listOf<Device>()
}
