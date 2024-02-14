plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "5.15.0"
  id("org.springframework.boot") version "3.2.2"
  kotlin("plugin.spring") version "1.9.22"
}

configurations {
  testImplementation { exclude(group = "org.junit.vintage") }
}

ktlint {
  enableExperimentalRules.set(true)
  disabledRules.set(setOf("experimental:package-name", "no-wildcard-imports"))
}

repositories {
  jcenter()
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.2")
  implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
  implementation("com.google.code.gson:gson:2.10.1")
  implementation("org.springframework.boot:spring-boot-starter-hateoas:3.2.2")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
  implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")
  implementation("com.microsoft.azure:applicationinsights-agent:3.4.19")
  implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.2")
  implementation("org.jetbrains.exposed:exposed-core:0.47.0")
  implementation("org.jetbrains.exposed:exposed-dao:0.47.0")
  implementation("org.jetbrains.exposed:exposed-jdbc:0.47.0")
  implementation("org.postgresql:postgresql:42.7.1")
  implementation("org.apache.commons:commons-csv:1.10.0")

  // https://mvnrepository.com/artifact/software.amazon.awssdk/athena
  implementation("software.amazon.awssdk:athena:2.24.0")

  testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.2")
  testImplementation("org.testcontainers:testcontainers:1.19.5")
  testImplementation("org.testcontainers:junit-jupiter:1.19.5")
  testImplementation("org.testcontainers:postgresql:1.19.5")

  api("me.paulschwarz:spring-dotenv:4.0.0")
  developmentOnly("org.springframework.boot:spring-boot-devtools:3.2.2")

  constraints {
    implementation("com.jayway.jsonpath:json-path:2.9.0") {
      because("To resolve CVE-2023-51074")
    }
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1") {
      because("To resolve CVE-2023-35116")
    }

    testImplementation("com.jayway.jsonpath:json-path:2.9.0") {
      because("To resolve CVE-2023-51074")
    }
  }
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(19))
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "19"
    }
  }
}
