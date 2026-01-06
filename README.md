<h1 align="center">
Todoist Multi-Layer Test Automation Framework<br>
(API, Web UI, Android)
<br><br>
<a href="https://www.todoist.com/" target="_blank" rel="noopener noreferrer"><img width="50%" src="media/logos/todoist.png" alt="Todoist banner" title="Todoist"/></a>
</h1>

<div align="center">

[ 🇬🇧 **English** | 🇷🇺 [Русский](docs/README_RU.md) ]

</div>

<div align="center">

[![CodeFactor](https://www.codefactor.io/repository/github/vikindor/todoist-test-automation/badge)](https://www.codefactor.io/repository/github/vikindor/todoist-test-automation)

</div>

Multi-layer test automation framework for Todoist app, covering **API**, **Web UI**, and **Android** layers.

The repository is designed as a **multi-module Gradle project** where each module represents an isolated test layer, while sharing common architectural principles, configuration patterns, and reporting approach.


# 📌 Table of Contents

- [🛠 Tech stack](#-tech-stack)
- [✨ Features](#-features)
- [🧩 Repository structure](#-repository-structure)
- [🚀 Running Tests](#-running-tests)
- [🔌 Integrations](#-integrations)


# 🛠 Tech stack

<p align="center">
  <a href="https://www.jetbrains.com/idea/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/intellij_idea.png" alt="IntelliJ IDEA logo" title="IntelliJ IDEA"/></a>
  <a href="https://developer.android.com/studio" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/android_studio.png" alt="Android Studio logo" title="Android Studio"/></a>
  <a href="https://gradle.org/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/gradle.png" alt="Gradle logo" title="Gradle"/></a>
  <a href="https://www.java.com/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/java.png" alt="Java logo" title="Java"/></a>
  <a href="https://selenide.org/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/selenide.png" alt="Selenide logo" title="Selenide"/></a>
  <a href="https://rest-assured.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/rest_assured.png" alt="REST Assured logo" title="REST Assured"/></a>
  <a href="https://junit.org/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/junit.png" alt="JUnit logo" title="JUnit 5"/></a>  
  <a href="https://appium.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/appium.png" alt="Appium logo" title="Appium"/></a>
  <a href="https://appium.github.io/appium-inspector/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/appium_inspector.png" alt="Appium Inspector logo" title="Appium Inspector"/></a>
  <a href="https://git-scm.com/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/git.png" alt="Git logo" title="Git"/></a>
  <a href="https://github.com/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/github.png" alt="GitHub logo" title="GitHub"/></a>
  <a href="https://www.jenkins.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/jenkins.png" alt="Jenkins logo" title="Jenkins"/></a>
  <a href="https://aerokube.com/selenoid/latest/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/selenoid.png" alt="Selenoid logo" title="Selenoid"/></a>  
  <a href="https://www.browserstack.com/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/browserstack.png" alt="BrowserStack logo" title="BrowserStack"/></a>
  <a href="https://qameta.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/allure_report.png" alt="Allure Report logo" title="Allure Report"/></a>
  <a href="https://telegram.org/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/telegram.png" alt="Telegram logo" title="Telegram"/></a>
  <a href="https://qameta.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/allure_testops.png" alt="Allure TestOps logo" title="Allure TestOps"/></a>
  <a href="https://www.atlassian.com/software/jira" target="_blank" rel="noopener noreferrer"><img width="6%" src="media/logos/atlassian_jira.png" alt="Atlassian Jira logo" title="Atlassian Jira"/></a>
</p>

`+` <a href="https://github.com/matteobaccan/owner" target="_blank" rel="noopener noreferrer">OWNER LIBRARY</a>  
`+` <a href="https://github.com/Vikindor/allure-report-templates" target="_blank" rel="noopener noreferrer">ALLURE REPORT - HTTP TEMPLATES</a>    
`+` <a href="https://github.com/qa-guru/allure-notifications" target="_blank" rel="noopener noreferrer">ALLURE NOTIFICATIONS</a>  


# ✨ Features

- **Multi-layer test architecture**  
  The same product is covered consistently across **API**, **Web UI**, and **Android** layers, with each layer implemented as an isolated Gradle module and evolving independently.


- **Strict layer isolation**  
  API, Web, and Mobile test layers do not depend on each other at code level.  
  Shared concepts are aligned by convention, not by shared implementation, preventing cross-layer coupling.


- **Infrastructure-first design**  
  Each module is built around a dedicated infrastructure layer responsible for initialization, configuration resolution, lifecycle handling, and diagnostics.  
  Test classes focus on scenarios rather than setup logic.


- **Unified project structure across layers**  
  Despite platform differences, all modules follow the same internal structure (configs, helpers, base setup, domain-oriented tests), reducing cognitive overhead when switching between layers.


- **Environment-agnostic test logic**  
  All platform- and environment-specific behavior (local vs remote, emulator vs real device, credentials) is resolved via configuration.  
  Test scenarios contain no branching logic based on execution environment.


- **Typed configuration management**  
  Configuration is handled via typed interfaces instead of raw property access, making misconfiguration visible early and minimizing runtime surprises.


- **Consistent reporting and diagnostics**  
  All modules follow the same Allure reporting strategy and lifecycle, producing predictable diagnostics (screenshots, logs, artifacts) regardless of platform.


- **Unified Allure aggregation across modules**  
  All test layers write raw Allure results into a shared root directory, enabling a single aggregated report for API, Web, and Mobile runs without post-processing or result merging.


- **Automated report publishing and notifications**  
  Aggregated Allure reports are generated as static artifacts and can be automatically published or sent via Telegram notifications, making test results accessible outside the CI environment.


- **Explicit execution model**  
  Each module exposes a single, explicit execution entry point, allowing local runs and CI pipelines to compose test executions without special-case handling.


# 🧩 Repository structure

```
.
├── api/ # Backend API test module
├── web/ # Web UI test module
├── mobile-android/ # Android UI test module
│
├── notifications/ # Allure report notification configs (Telegram)
│
├── build.gradle.kts # Root Gradle orchestrator:
│ # - unified Allure aggregation
│ # - report / serve tasks
│ # - notification integration
└── settings.gradle.kts
```

All modules:
- use the same build conventions
- expose a single execution entry point
- encapsulate platform-specific logic inside the module boundary

The root project acts as an orchestration layer and does not contain tests.


# 🚀 Running Tests

Each test layer can be executed independently or composed into a combined run using Gradle tasks.

---

## Run all tests (API + Web + Android)

    ./gradlew clean test

⚠️ With VM option for Android:  
`-Dplatform=emul-and` — for emulator  
`-Dplatform=real-and` — for real device  

Runs all test modules and produces a single aggregated Allure results set.

---

## Run a specific test layer

**API tests**

    ./gradlew clean :api:test

**Web UI tests**

    ./gradlew clean :web:test

**Android UI tests**

**Emulator**

    ./gradlew clean :mobile-android:test -Dplatform=emul-and

**Real connected device**

    ./gradlew clean :mobile-android:test -Dplatform=real-and

**BrowserStack**

The project is BrowserStack-ready.  
To run tests on BrowserStack Android:

- A properly signed `.apk` must be uploaded to the service before execution.
- The application ID must be specified in `bs-and.properties`: `browserstack.app=bs://<app-id>`


    ./gradlew :mobile-android:test -Dplatform=bs-and -DBROWSERSTACK_USER=<your_userName> -DBROWSERSTACK_KEY=<your_accessKey>

Each module resolves its own configuration and execution environment independently.

---

## Generate aggregated Allure report

    ./gradlew allureReport

Generates a static aggregated Allure report from shared raw results.

Report output directory:

    build/reports/allure-report/allureReport

This report can be archived, published, or used for external notifications.

---

## View results locally in browser

    ./gradlew allureServe

Starts a local HTTP server and opens the aggregated Allure report in the browser.

---

## Send Allure report to Telegram

    ./gradlew sendAllureTelegram

Sends a summary notification with key metrics to a configured Telegram channel.

## Remote run in Jenkins

When executing the following commands, the tests will be run remotely using `Selenoid`.

```
clean :api:${TASK_API} :web:${TASK_WEB}
-DremoteUrl=<selenoid_url>
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
allureReport
```

Build parameters:

`SELENOID_URL` — Selenoid endpoint URL. Default: `https://(username):(password)@selenoid.autotests.cloud/wd/hub`  
`BROWSER` — Browser used for test execution. Default: `chrome`  
`BROWSER_VERSION` — Browser version used for test execution. Default: `128.0`  
`BROWSER_SIZE` — Browser window size. Default: `1920x1080`  


# 🔌 Integrations

## <img width="4%" src="media/logos/jenkins.png" alt="Jenkins logo" title="Jenkins"/> Jenkins

The project is executed via a Jenkins job, which serves as the primary CI entry point for running automated tests.

The job is responsible for triggering test execution, passing runtime parameters, and orchestrating reporting and post-processing steps.

<p align="center">
<img width="100%" src="media/screenshots/jenkins_1.jpg" alt="Jenkins Job screenshot" title="Jenkins Job"/>
</p>

## <img width="4%" src="media/logos/allure_report.png" alt="Allure Report logo" title="Allure Report"/> Allure Report

A unified Allure report is generated after test execution and aggregates results from all test layers.

The report provides a consolidated view of API, Web, and Mobile test executions, including detailed steps, attachments, and diagnostics.

### Overview

The overview section displays overall execution statistics and test distribution across layers.

<p align="center">
<img width="100%" src="media/screenshots/allure_report_1.jpg" alt="Allure Report tests overview screenshot" title="Allure Report tests overview"/>
</p>

### Mobile test

Mobile test details contain step-level execution information and attachments (including video).

<p align="center">
<img width="100%" src="media/screenshots/allure_report_2.jpg" alt="Allure Report Mobile test details screenshot" title="Allure Report mobile test details"/>
</p>

### API test

API test details provide request/response visibility and execution context for backend validation based on <a href="https://github.com/Vikindor/allure-report-templates" target="_blank" rel="noopener noreferrer">custom HTTP templates</a>.

<p align="center">
<img width="100%" src="media/screenshots/allure_report_3.jpg" alt="Allure Report API test details screenshot" title="Allure Report API test details"/>
</p>

## <img width="4%" src="media/logos/allure_testops.png" alt="Allure TestOps logo" title="Allure TestOps"/> Allure TestOps integration

The Jenkins build pipeline is integrated with Allure TestOps for centralized test execution analysis and reporting.

Automated test execution results are published to Allure TestOps, where they are aggregated and visualized.
The Allure TestOps dashboard provides execution statistics, historical trends, and overall test run visibility.

### Dashboard

The dashboard presents a high-level view of test execution status, trends, and quality metrics across multiple runs.

<p align="center">
<img width="100%" src="media/screenshots/testops_1.jpg" alt="Allure TestOps dashboard screenshot" title="Allure TestOps dashboard"/>
</p>

### Run details

Run details provide granular information about a specific execution, including individual test results and related metadata.

<p align="center">
<img width="100%" src="media/screenshots/testops_2.jpg" alt="Allure TestOps run details screenshot" title="Allure TestOps run details"/>
</p>

## <img width="4%" src="media/logos/atlassian_jira.png" alt="Atlassian Jira logo" title="Atlassian Jira"/> Jira integration

Allure TestOps is integrated with Jira to establish traceability between automated tests and tracked work items.

Test cases and execution results are linked to Jira issues, enabling correlation between test coverage, execution status, and requirements.

<p align="center">
<img width="100%" src="media/screenshots/jira_1.jpg" alt="Jira task screenshot" title="Jira task"/>
</p>

## <img width="4%" src="media/logos/telegram.png" alt="Telegram logo" title="Telegram"/> Telegram report

After test execution is completed, the generated Allure report is processed by the Allure Notifications tool and sent to a configured Telegram channel.

The notification contains a summary of the test run and provides access to the generated Allure report, making execution results available outside the CI environment.

<p align="center">
<img src="media/screenshots/telegram_report.jpg" alt="Telegram report screenshot" title="Telegram report"/>
</p>

## <img width="4%" src="media/logos/selenoid.png" alt="Selenoid logo" title="Selenoid"/> Selenoid video

Each UI test execution is accompanied by a video recording captured by Selenoid.

The video is attached to the corresponding test case in the report and can be used for failure analysis and execution review.

<p align="center">
<img width="100%" src="media/videos/selenoid.gif" alt="Selenoid recording gif" title="Selenoid recording"/>
</p>

## <img width="4%" src="media/logos/appium.png" alt="Selenoid logo" title="Selenoid"/> Appium video

Each Mobile UI test execution includes a video recording captured during the Appium session.

The recording is attached to the test report and can be used to analyze application behavior and UI interactions during execution.

<p align="center">
<img width="50%" src="media/videos/appium.gif" alt="Appium recording gif" title="Appium recording"/>
</p>
