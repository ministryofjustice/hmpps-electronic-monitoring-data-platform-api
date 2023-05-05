
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val project_version: String by project
//val systemPropproject_version: String by project

plugins {
  kotlin("jvm") version "1.8.21"
  id("io.ktor.plugin") version "2.3.0"
    id("uk.gov.justice.hmpps.gradle-spring-boot") version "4.8.5"
  kotlin("plugin.spring") version "1.8.10"
}
group = "uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi"
//version = "0.0.1"
version = project_version


application {
  mainClass.set("uk.gov.justice.digital.hmpps.hmppselectronicmonitoringdataplatformapi.HmppsEletronicMonitoringDataPlatformApi.kt")

  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}
repositories {
  mavenCentral()
}

//plugins {
//  id("uk.gov.justice.hmpps.gradle-spring-boot") version "4.8.5"
//  kotlin("plugin.spring") version "1.8.10"
//}

configurations {
  testImplementation { exclude(group = "org.junit.vintage") }
}


dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
//
//java {
//  toolchain.languageVersion.set(JavaLanguageVersion.of(19))
//}
//
//tasks {
//  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions {
//      jvmTarget = "19"
//    }
//  }
//}


