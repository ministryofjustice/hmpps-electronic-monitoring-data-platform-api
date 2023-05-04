package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.ValidationException

@RequestMapping("api/v1/hello")
@RestController
class HelloWorldController {
  @GetMapping("/world")
  fun getHelloWorld(): ResponseEntity<Any> {
    try {
      return ResponseEntity.ok("Hello world!!!")
    } catch (e: ValidationException) {
      throw ValidationException(e)
    } catch (e: java.lang.Exception) {
      throw java.lang.Exception(e)
    }
  }
}
