package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication()
@ConfigurationPropertiesScan
class HmppsElectronicMonitoringDataPlatformApi

fun main(args: Array<String>) {
  runApplication<HmppsElectronicMonitoringDataPlatformApi>(*args)
}
