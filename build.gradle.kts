val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val project_version: String by project
val postgresql_version: String by project
val kgraphql_version: String by project

plugins {
  kotlin("jvm") version "1.8.21"
  id("io.ktor.plugin") version "2.3.0"
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "4.8.5"
  kotlin("plugin.spring") version "1.8.10"
}
group = "uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi"
version = project_version

application {
  mainClass.set("uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.HmppsEletronicMonitoringDataPlatformApi.kt")
  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}
repositories {
  mavenCentral()
}

configurations {
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  implementation("org.testng:testng:7.1.0")
  testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
  testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.0")
  implementation("org.postgresql:postgresql:$postgresql_version")
  implementation("io.ktor:ktor-jackson:1.6.8")
  implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
  implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")
  implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")

  implementation ("org.jetbrains.exposed:exposed-core:0.41.1")
  implementation ("org.jetbrains.exposed:exposed-jdbc:0.41.1")
//  implementation ("com.h2database:h2:1.4.199")
  implementation ("com.apurebase:kgraphql:${kgraphql_version}")
  implementation("com.apurebase:kgraphql-ktor:$kgraphql_version")



  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
}
