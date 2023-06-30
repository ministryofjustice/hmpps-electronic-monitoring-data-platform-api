package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.EmApiError

@ControllerAdvice
class ErrorController {

  val devMode = true

  @ExceptionHandler
  fun handleEmApiError(e: EmApiError): ResponseEntity<EmApiError> {
    return if (devMode) {
      ResponseEntity(EmApiError(e.message, e.status, e.exceptionDetails), e.status)
    } else {
      ResponseEntity(EmApiError(e.message, e.status), e.status)
    }
  }

  @ExceptionHandler
  fun handleGenericException(e: RuntimeException): ResponseEntity<EmApiError> {
    return if (devMode) {
      ResponseEntity(EmApiError("Something went wrong in our side", HttpStatus.INTERNAL_SERVER_ERROR, e), HttpStatus.INTERNAL_SERVER_ERROR)
    } else {
      ResponseEntity(EmApiError("Something went wrong in our side", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}
