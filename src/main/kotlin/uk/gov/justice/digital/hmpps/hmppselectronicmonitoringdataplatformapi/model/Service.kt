package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import jakarta.persistence.*
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime
import kotlin.reflect.full.memberProperties

@Entity
@Table(name = "service")
data class Service(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val serviceId: String = "",
  val serviceSid: String = "",
  val serviceType: String = "",
  val serviceDescription: String = "",

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val serviceStartDate: ZonedDateTime = ZonedDateTime.now(),

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val serviceEndDate: ZonedDateTime = ZonedDateTime.now(),
  )