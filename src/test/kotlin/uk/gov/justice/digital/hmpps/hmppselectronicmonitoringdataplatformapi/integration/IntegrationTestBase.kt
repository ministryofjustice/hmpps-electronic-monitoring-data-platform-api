package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.JdbcDatabaseContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
abstract class IntegrationTestBase {

  @Autowired
  lateinit var webTestClient: WebTestClient

  @Container
  val container = postgres("postgres:latest") {
    withDatabaseName("demo_db")
    withUsername("postgres")
    withPassword("password1")
  }
//  @Autowired
//  lateinit var testRestTemplate: TestRestTemplate
}

fun postgres(imageName: String, opts: JdbcDatabaseContainer<Nothing>.() -> Unit) =
  PostgreSQLContainer<Nothing>(DockerImageName.parse(imageName)).apply(opts)