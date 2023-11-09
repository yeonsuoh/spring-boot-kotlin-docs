import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
    kotlin("plugin.jpa") version "1.9.20"
    // allOpen -> lazy fetching을 위해 필요한 설정
    kotlin("plugin.allopen") version "1.9.20"
    // 사용자 정의 속성(BlogConfiguration)을 인식하기 위해 필요
    kotlin("kapt") version "1.9.20"
}

allOpen { // lazy fetching을 위해 설정
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

group = "com.kotlin"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.security:spring-security-web:6.1.2")
    implementation("org.springframework.security:spring-security-core:6.1.2")
    implementation("com.mysql:mysql-connector-j:8.1.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.5")
    // jpa, jackson
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    // // //
    // mustache-학습용
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    // h2-학습용(테스트용 데이터베이스)
    runtimeOnly("com.h2database:h2")
    // WebMvcTest와 Mockk 관련 - 학습용
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:4.0.0")
    // 사용자 정의 속성 인식을 위해 고유한 메타데이터를 생성하려면 kapt
    kapt("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
