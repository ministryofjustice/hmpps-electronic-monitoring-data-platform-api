package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.JdbcDatabaseContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
@ActiveProfiles("test")
@Testcontainers
abstract class IntegrationTestBase {

  @Autowired
  lateinit var webTestClient: WebTestClient

  @Autowired
  lateinit var jdbc: JdbcTemplate

  companion object {
    @Container
    val container = postgres("postgres:15.3") {
      withDatabaseName("test_db")
      withUsername("postgres")
      withPassword("root")
    }

    @JvmStatic
    @DynamicPropertySource
    fun datasourceConfig(registry: DynamicPropertyRegistry) {
      registry.add("spring.datasource.url", container::getJdbcUrl)
      registry.add("spring.datasource.password", container::getPassword)
      registry.add("spring.datasource.username", container::getUsername)
    }
  }
}

fun postgres(imageName: String, opts: JdbcDatabaseContainer<Nothing>.() -> Unit) =
  PostgreSQLContainer<Nothing>(DockerImageName.parse(imageName)).apply(opts)
