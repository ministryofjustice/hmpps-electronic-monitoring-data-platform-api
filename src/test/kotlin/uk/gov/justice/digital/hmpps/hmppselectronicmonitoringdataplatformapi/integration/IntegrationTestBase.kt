package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@ContextConfiguration(initializers = [BaseITInitializer::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class IntegrationTestBase {

  @Autowired
  private val jdbcTemplate: JdbcTemplate? = null

  @Transactional
  protected fun cleanDB() {
    val tablesToTruncate = listOf("DeviceWearer").joinToString()
    val sql = """
        TRUNCATE TABLE $tablesToTruncate CASCADE
    """.trimIndent()
    jdbcTemplate?.execute(sql)
  }

  @Autowired
  lateinit var webTestClient: WebTestClient

  @BeforeEach
  fun setUp() {
    webTestClient = webTestClient
      .mutate()
      .responseTimeout(Duration.ofMillis(30000))
      .build()
  }
}
