plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "5.2.0"
  id("org.springframework.boot") version "3.1.0"
  kotlin("plugin.spring") version "1.8.22"
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
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
  implementation("com.google.code.gson:gson:2.8.9")
  implementation("org.springframework.boot:spring-boot-starter-hateoas:3.1.0")
  implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("com.microsoft.azure:applicationinsights-agent:3.4.0")
  implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
  implementation("org.jetbrains.exposed:exposed-core:0.24.1")
  implementation("org.jetbrains.exposed:exposed-dao:0.24.1")
  implementation("org.jetbrains.exposed:exposed-jdbc:0.24.1")
  implementation("org.postgresql:postgresql")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.testcontainers:testcontainers:1.18.1")
  testImplementation("org.testcontainers:junit-jupiter:1.18.1")
  testImplementation("org.testcontainers:postgresql:1.18.1")
  api("me.paulschwarz:spring-dotenv:2.5.3")
  developmentOnly("org.springframework.boot:spring-boot-devtools:3.1.0")
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
