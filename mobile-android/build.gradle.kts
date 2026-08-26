import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("io.qameta.allure")
}

group = "io.github.vikindor"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.18.0")
    testImplementation("io.qameta.allure:allure-selenide:2.35.4")
    testImplementation("io.rest-assured:rest-assured:6.0.1")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("io.qameta.allure:allure-rest-assured:2.35.4")
    allureRawResultElements(files(rootProject.layout.buildDirectory.dir("allure-results")))
    testImplementation("org.aeonbits.owner:owner:1.0.12")
    testImplementation("net.datafaker:datafaker:2.7.0")
    testImplementation("io.appium:java-client:10.1.1")
    testImplementation("commons-io:commons-io:2.22.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.18")
}

allure {
    report {
        version.set("2.45.0") // allure-framework/allure2
    }
    adapter {
        autoconfigure.set(true)
        autoconfigureListeners.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.35.4") // Same as allure-framework/allure-java
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()

    systemProperties(
        System.getProperties()
            .entries
            .associate { (k, v) -> k.toString() to v }
    )

    systemProperty(
        "allure.results.directory",
        rootProject.layout.buildDirectory.dir("allure-results").get().asFile.absolutePath
    )

    testLogging {
        events = setOf(
            TestLogEvent.STARTED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.FAILED,
            TestLogEvent.STANDARD_OUT,
            TestLogEvent.STANDARD_ERROR
        )
        exceptionFormat = TestExceptionFormat.SHORT
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }

    jvmArgs("-Dfile.encoding=UTF-8", "-Dorg.slf4j.simpleLogger.logFile=System.out")
    environment("SE_AVOID_STATS", "true")
}

tasks.withType<Test>().configureEach {
    dependsOn(rootProject.tasks.named("prepareAllureExecutor"))
}
