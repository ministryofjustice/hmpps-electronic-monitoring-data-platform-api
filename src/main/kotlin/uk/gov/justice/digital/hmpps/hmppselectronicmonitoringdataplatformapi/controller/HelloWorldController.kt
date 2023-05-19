package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus
import jakarta.validation.ValidationException

@RequestMapping("api")
@RestController
class HelloWorldController {
  @GetMapping("/v1/hello/world")
  fun getHelloWorld(): ResponseEntity<Any> {
    try {
      return ResponseEntity("Hello World!!!", HttpStatus.OK)
    } catch (e: ValidationException) {
      throw ValidationException(e)
    } catch (e: java.lang.Exception) {
      throw java.lang.Exception(e)
    }
  }
}
