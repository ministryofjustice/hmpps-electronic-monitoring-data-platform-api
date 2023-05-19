package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.model.User
import uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.repository.UserRepository

@RestController
@RequestMapping("/users")
class UserController(@Autowired private val userRepository: UserRepository) {

    @GetMapping("/v1")
    fun getAllUsers(): List<User> =
        userRepository.findAll().toList()

    @PostMapping("/v1")
    fun createUser(@ModelAttribute user: User): ResponseEntity<User> {
        val createdUser = userRepository.save(user)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @GetMapping("/v1/{id}")
    fun getUserById(@PathVariable("id") userId: Int): ResponseEntity<User> {
        val user = userRepository.findById(userId).orElse(null)
        return if (user != null) ResponseEntity(user, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/v1/{id}")
    fun updateUserById(@PathVariable("id") userId: Int, @ModelAttribute user: User): ResponseEntity<User> {

        val existingUser = userRepository.findById(userId).orElse(null)

        if (existingUser == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedUser = existingUser.copy(name = user.name, email = user.email)
        userRepository.save(updatedUser)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @DeleteMapping("/v1/{id}")
    fun deleteUserById(@PathVariable("id") userId: Int): ResponseEntity<User> {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userRepository.deleteById(userId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}