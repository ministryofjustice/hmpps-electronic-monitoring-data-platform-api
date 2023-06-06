package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository

import org.springframework.data.repository.CrudRepository
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.User

interface UserRepository : CrudRepository<User, Int>
