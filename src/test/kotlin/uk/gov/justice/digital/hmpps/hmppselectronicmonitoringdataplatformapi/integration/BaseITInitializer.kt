package uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.integration

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.DockerComposeContainer
import java.io.File
import java.nio.file.Paths

class BaseITInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

  companion object {
    class KDockerComposeContainer(file: File) : DockerComposeContainer<KDockerComposeContainer>(file)

    class Container(
      val serviceName: String,
      val port: Int,
    )

    private val POSTGRES = Container("postgres_1", 5430)

    private val COMPOSE_CONTAINER: KDockerComposeContainer by lazy {
      val path = Paths.get("").toAbsolutePath().toString()
      KDockerComposeContainer(File("docker-compose-test.yml"))
        .withExposedService(POSTGRES.serviceName, POSTGRES.port)
        .withLocalCompose(true)
    }
  }

  override fun initialize(applicationContext: ConfigurableApplicationContext) {
    COMPOSE_CONTAINER.start()

    val jdbcURL = "jdbc:postgresql://${getContainerUrl(POSTGRES)}:${POSTGRES.port}/testcontainers_demo"

    TestPropertyValues.of(
      "spring.datasource.url=$jdbcURL",
    ).applyTo(applicationContext.environment)
  }

  private fun getContainerUrl(container: Container) =
    COMPOSE_CONTAINER.getServiceHost(container.serviceName, container.port)
}
