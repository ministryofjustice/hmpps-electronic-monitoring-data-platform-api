package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
// @EnableMethodSecurity(prePostEnabled = true) // , securedEnabled = true, jsr250Enabled = true)
class MethodSecurityConfig : GlobalMethodSecurityConfiguration()
