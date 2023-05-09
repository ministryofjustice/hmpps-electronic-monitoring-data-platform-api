package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.helloworld

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.ValidationException

@RequestMapping("api")
@RestController
class HelloWorldController {
  @GetMapping("/v1/hello/world")
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
