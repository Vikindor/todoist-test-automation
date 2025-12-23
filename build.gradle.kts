plugins {
    id("io.qameta.allure") version "3.0.0"
}

group = "io.github.vikindor"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    allureRawResultElements(
        files(layout.buildDirectory.dir("allure-results"))
    )
}

val sharedAllureResultsDir =
    rootProject.layout.buildDirectory.dir("allure-results")

tasks.register("prepareAllureExecutor") {
    val executorFile = sharedAllureResultsDir.map { it.file("executor.json") }

    doLast {
        val file = executorFile.get().asFile
        file.parentFile.mkdirs()

        if (!file.exists()) {
            file.writeText(
                """
                {
                  "name": "Gradle",
                  "type": "gradle",
                  "buildName": "todoist-tests",
                  "projectPath": ":",
                  "projectVersion": "1.0"
                }
                """.trimIndent()
            )
        }
    }
}

tasks.named("allureReport") {
    doFirst {
        delete(layout.buildDirectory.dir("reports/allure-report"))
    }
}

subprojects {
    tasks.matching { it.name == "allureReport" }.configureEach {
        enabled = false
    }
    tasks.matching { it.name == "allureServe" }.configureEach {
        enabled = false
    }
}

tasks.register<Exec>("sendAllureTelegram") {
    dependsOn("allureReport")

    commandLine(
        "java",
        "-DconfigFile=notifications/config.json",
        "-jar",
        "notifications/allure-notifications-4.11.0.jar"
    )
}
