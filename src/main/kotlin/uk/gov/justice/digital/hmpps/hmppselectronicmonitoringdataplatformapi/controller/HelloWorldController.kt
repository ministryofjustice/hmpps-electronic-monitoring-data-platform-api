package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("hello")
@RestController
class HelloWorldController {
  @GetMapping("/v1")
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
