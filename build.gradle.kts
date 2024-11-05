plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.asciidoctor.jvm.convert") version "3.3.2" // (1) Asciidoctor 플러그인을 추가하여 문서 변환 기능을 제공합니다.
}

group = "com.raonsecure.sample"
version = "0.0.1-SNAPSHOT"
val asciidoctorExt: Configuration by configurations.creating // (2) Asciidoctor 문서 생성을 위한 Configuration을 생성합니다.

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // open api
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")

    // Object Mapping
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // poi
    implementation("org.apache.poi:poi-ooxml:5.2.5")

    // spring rest docs
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor") // (3) Asciidoctor를 사용해 REST Docs를 Ascii 문서로 변환하는 의존성을 추가합니다.

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // mariadb
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    // h2
    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val snippetsDir by extra { file("build/generated-snippets") } // (4) 테스트 결과로 생성될 문서 스니펫의 저장 디렉터리를 지정합니다.

tasks {
    test { // (5) 테스트 작업을 정의하고, 문서 스니펫을 snippetsDir에 저장합니다.
        outputs.dir(snippetsDir)
        useJUnitPlatform()
    }
    asciidoctor { // (6) Asciidoctor 플러그인을 사용하여 스니펫을 기반으로 문서를 생성하는 작업을 정의합니다.
        dependsOn(test)
        configurations("asciidoctorExt")
        inputs.dir(snippetsDir)
        baseDirFollowsSourceFile()
    }
    val createDocument by registering(Copy::class) { // (7) 생성된 문서를 정적 리소스 디렉터리로 복사하는 작업을 정의합니다.
        dependsOn(asciidoctor)
        from("build/docs/asciidoc")
        into("src/main/resources/static/docs")
    }
    bootJar {
        dependsOn(createDocument)
    }
}
