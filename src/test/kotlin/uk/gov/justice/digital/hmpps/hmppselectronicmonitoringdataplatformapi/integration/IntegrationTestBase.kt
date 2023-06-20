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

  @Autowired private val jdbcTemplate: JdbcTemplate? = null

  @Transactional
  protected fun cleanDB() {
    val tablesToTruncate = listOf("DeviceWearer").joinToString()
    val sql = "TRUNCATE TABLE $tablesToTruncate CASCADE"
    jdbcTemplate?.execute(sql)
  }

  @Autowired lateinit var webTestClient: WebTestClient

  @BeforeEach
  fun setUp() {
    webTestClient = webTestClient.mutate().responseTimeout(Duration.ofMillis(30000)).build()
  }
}

//  @Suppress("SpringJavaInjectionPointsAutowiringInspection")
//  @Autowired
//  lateinit var webTestClient: WebTestClient
//
//  @Autowired
//  lateinit var flyway: Flyway
//
//  @Autowired
//  lateinit var objectMapper: ObjectMapper
//
//  @MockBean
//  lateinit var dateTimeProvider: DateTimeProvider
//
//  @SpyBean
//  lateinit var auditingHandler: AuditingHandler

//  companion object {
//    @JvmField
//    internal val prisonApiMockServer = PrisonApiMockServer()
//
//    @JvmField
//    internal val oAuthMockServer = OAuthMockServer()

//    @BeforeAll
//    @JvmStatic
//    fun startMocks() {
//      prisonApiMockServer.start()
//      oAuthMockServer.start()
//      oAuthMockServer.stubGrantToken()
//      oAuthMockServer.stubHealthPing(200)
//      prisonApiMockServer.stubHealth()
//    }
//  }

//  @AfterEach
//  fun resetDb() {
//    flyway.clean()
//    flyway.migrate()
//  }

//  companion object {
//    @Container
//    val container = postgres("postgres:15.3") {
//      withDatabaseName("test_db")
//      withUsername("postgres")
//      withPassword("root")
//    }

//    @JvmStatic
//    @DynamicPropertySource
//    fun datasourceConfig(registry: DynamicPropertyRegistry) {
//      registry.add("spring.datasource.url", container::getJdbcUrl)
//      registry.add("spring.datasource.password", container::getPassword)
//      registry.add("spring.datasource.username", container::getUsername)
//    }
//  }
// }

// fun postgres(imageName: String, opts: JdbcDatabaseContainer<Nothing>.() -> Unit) =
//  PostgreSQLContainer<Nothing>(DockerImageName.parse(imageName)).apply(opts)
