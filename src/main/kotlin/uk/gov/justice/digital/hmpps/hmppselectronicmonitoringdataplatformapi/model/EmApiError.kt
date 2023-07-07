package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model

import org.springframework.http.HttpStatus

data class EmApiError(
  override val message: String = "Something went wrong in our side",
  val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
  val exceptionDetails: Exception? = null,
) : Exception() {
  @Synchronized
  fun fillInStackTrace(): Throwable? {
    return this
  }
}
