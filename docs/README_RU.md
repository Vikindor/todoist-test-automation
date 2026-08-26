<h1 align="center">
Фреймворк многоуровневой автоматизации тестирования<br>
сервиса Todoist<br>
(API, Web UI, Android)
<br><br>
<a href="https://www.todoist.com/" target="_blank" rel="noopener noreferrer"><img width="50%" src="../media/logos/todoist.png" alt="Todoist banner" title="Todoist"/></a>
</h1>

<div align="center">

[ 🇬🇧 [English](../README.md) | 🇷🇺 **Русский** ]

</div>

<div align="center">

[![CodeFactor](https://www.codefactor.io/repository/github/vikindor/todoist-test-automation/badge)](https://www.codefactor.io/repository/github/vikindor/todoist-test-automation)

</div>

> ⚠️ **Дисклеймер**
>
> Этот проект — **портфельный / демонстрационный фреймворк автоматизации тестирования**, созданный в учебных и презентационных целях.
> Он **не является официальным проектом Todoist** и не имеет отношения к команде Todoist.
> Покрытие тестами намеренно ограничено и направлено на демонстрацию архитектуры, инструментов и подходов к автоматизации, а не на полное покрытие продукта.

Проект по автоматизации тестирования продукта с покрытием на уровнях **API**, **Web UI** и **Android**.

Репозиторий реализован как **многомодульный Gradle-проект**, где каждый модуль представляет изолированный тестовый слой, при этом все модули следуют единым архитектурным принципам, подходам к конфигурации и стратегии отчётности.


# 📌 Содержание

- [🛠 Технологический стек](#-технологический-стек)
- [✨ Особенности](#-особенности)
- [🧩 Структура репозитория](#-структура-репозитория)
- [🚀 Запуск тестов](#-запуск-тестов)
- [🔌 Интеграции](#-интеграции)


# 🛠 Технологический стек

<p align="center">
  <a href="https://www.jetbrains.com/idea/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/intellij_idea.png" alt="IntelliJ IDEA logo" title="IntelliJ IDEA"/></a>
  <a href="https://developer.android.com/studio" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/android_studio.png" alt="Android Studio logo" title="Android Studio"/></a>
  <a href="https://gradle.org/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/gradle.png" alt="Gradle logo" title="Gradle"/></a>
  <a href="https://www.java.com/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/java.png" alt="Java logo" title="Java"/></a>
  <a href="https://selenide.org/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/selenide.png" alt="Selenide logo" title="Selenide"/></a>
  <a href="https://rest-assured.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/rest_assured.png" alt="REST Assured logo" title="REST Assured"/></a>
  <a href="https://junit.org/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/junit.png" alt="JUnit logo" title="JUnit 5"/></a>  
  <a href="https://appium.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/appium.png" alt="Appium logo" title="Appium"/></a>
  <a href="https://appium.github.io/appium-inspector/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/appium_inspector.png" alt="Appium Inspector logo" title="Appium Inspector"/></a>
  <a href="https://git-scm.com/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/git.png" alt="Git logo" title="Git"/></a>
  <a href="https://github.com/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/github.png" alt="GitHub logo" title="GitHub"/></a>
  <a href="https://www.jenkins.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/jenkins.png" alt="Jenkins logo" title="Jenkins"/></a>
  <a href="https://aerokube.com/selenoid/latest/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/selenoid.png" alt="Selenoid logo" title="Selenoid"/></a>  
  <a href="https://www.browserstack.com/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/browserstack.png" alt="BrowserStack logo" title="BrowserStack"/></a>
  <a href="https://qameta.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/allure_report.png" alt="Allure Report logo" title="Allure Report"/></a>
  <a href="https://telegram.org/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/telegram.png" alt="Telegram logo" title="Telegram"/></a>
  <a href="https://qameta.io/" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/allure_testops.png" alt="Allure TestOps logo" title="Allure TestOps"/></a>
  <a href="https://www.atlassian.com/software/jira" target="_blank" rel="noopener noreferrer"><img width="6%" src="../media/logos/atlassian_jira.png" alt="Atlassian Jira logo" title="Atlassian Jira"/></a>
</p>

`+` <a href="https://github.com/matteobaccan/owner" target="_blank" rel="noopener noreferrer">БИБЛИОТЕКА OWNER</a>  
`+` <a href="https://www.datafaker.net/" target="_blank" rel="noopener noreferrer">БИБЛИОТЕКА DATAFAKER</a>  
`+` <a href="https://github.com/Vikindor/allure-report-templates" target="_blank" rel="noopener noreferrer">ALLURE REPORT - HTTP ШАБЛОНЫ</a> · Собственный репозиторий  
`+` <a href="https://github.com/qa-guru/allure-notifications" target="_blank" rel="noopener noreferrer">ALLURE NOTIFICATIONS</a>  


# ✨ Особенности

- **Многоуровневая архитектура автотестов**  
  Один и тот же продукт последовательно покрыт тестами на уровнях **API**, **Web UI** и **Android**.  
  Каждый уровень реализован как отдельный Gradle-модуль и развивается независимо от остальных.


- **Строгая изоляция тестовых слоёв**  
  API-, Web- и Mobile-слои не зависят друг от друга на уровне кода.  
  Общие концепции синхронизируются через единые принципы, а не через общий код, что исключает жёсткую связность между слоями.


- **Подход «инфраструктура прежде всего»**  
  Каждый модуль построен вокруг собственной инфраструктурной части, отвечающей за инициализацию, загрузку конфигурации, управление жизненным циклом и диагностику.  
  Тестовые классы сфокусированы на сценариях, а не на подготовке окружения.


- **Единая структура модулей независимо от платформы**  
  Несмотря на различия платформ, все модули используют одинаковую внутреннюю структуру (конфигурации, хелперы, базовая инициализация, доменно-ориентированные тесты), что снижает когнитивную нагрузку при переключении между слоями.


- **Независимость тестовой логики от окружения**  
  Все платформенные и окруженческие различия (local / remote, эмулятор / реальное устройство, креды) определяются через конфигурацию.  
  В самих тестовых сценариях отсутствует ветвление логики в зависимости от среды выполнения.


- **Типизированное управление конфигурацией**  
  Конфигурации описываются через типизированные интерфейсы, а не через прямой доступ к свойствам, что позволяет выявлять ошибки конфигурации на раннем этапе и снижает риск неожиданных проблем во время выполнения.


- **Единый подход к отчётности и диагностике**  
  Все модули используют одинаковую стратегию и жизненный цикл Allure-отчётов, обеспечивая предсказуемый набор диагностических данных (скриншоты, логи, артефакты) вне зависимости от платформы.


- **Агрегация Allure-результатов между модулями**  
  Все тестовые слои сохраняют сырые Allure-результаты в общий корневой каталог, что позволяет формировать единый агрегированный отчёт для API, Web и Mobile без дополнительной обработки или слияния результатов.


- **Автоматическая публикация отчётов и уведомления**  
  Агрегированные Allure-отчёты формируются как статические артефакты и могут автоматически публиковаться либо отправляться через уведомления в Telegram, делая результаты доступными вне CI-окружения.


- **Явная модель запуска тестов**  
  Каждый модуль предоставляет единую, явно определённую точку входа для запуска, что позволяет одинаково удобно использовать тесты локально и в CI-пайплайнах без специальных условий и исключений.

# 📁 Структура репозитория

```
.
├── api/ # Модуль автотестов API для backend'а
├── web/ # Модуль автотестов Web UI
├── mobile-android/ # Модуль автотестов Android UI
│
├── notifications/ # Конфигурации уведомлений Allure-отчётов (Telegram)
│
├── build.gradle.kts # Корневой Gradle-оркестратор:
│ # - единая агрегация Allure-результатов
│ # - задачи генерации и публикации отчётов
│ # - интеграция уведомлений
└── settings.gradle.kts
```

Все модули:
- используют единые соглашения сборки
- предоставляют одну явную точку запуска
- инкапсулируют платформо-специфичную логику внутри границ модуля

Корневой проект выступает в роли оркестрационного слоя и не содержит тестов.


# 🚀 Запуск тестов

Каждый тестовый слой может запускаться независимо либо объединяться в общий прогон с помощью Gradle-задач.

---

## Запуск всех тестов (API + Web + Android)

    ./gradlew clean test

⚠️ Для Android необходимо указать VM-параметр:  
`-Dplatform=emul-and` — для эмулятора  
`-Dplatform=real-and` — для реального устройства

Команда запускает все тестовые модули и формирует единый агрегированный набор Allure-результатов.

---

## Запуск отдельного тестового слоя

**API тесты**

    ./gradlew clean :api:test

**Web UI тесты**

    ./gradlew clean :web:test

**Android UI тесты**

**Эмулятор**

    ./gradlew clean :mobile-android:test -Dplatform=emul-and

**Подключённое реальное устройство**

    ./gradlew clean :mobile-android:test -Dplatform=real-and

**BrowserStack**

Проект готов для тестирования на платформе BrowserStack.  
Для выполнения Android-тестов на BrowserStack необходимо:

- предварительно загрузить в сервис корректно подписанный `.apk`;
- указать идентификатор приложения в `bs-and.properties`: `browserstack.app=bs://<app-id>`


    ./gradlew :mobile-android:test -Dplatform=bs-and -DBROWSERSTACK_USER=<your_userName> -DBROWSERSTACK_KEY=<your_accessKey>

Каждый модуль самостоятельно определяет свою конфигурацию и среду выполнения.

---

## Генерация агрегированного Allure-отчёта

    ./gradlew allureReport

Формирует статический агрегированный Allure-отчёт на основе общих сырых результатов.

Каталог с отчётом:

    build/reports/allure-report/allureReport

Полученный отчёт может быть заархивирован, опубликован или использован для внешних уведомлений.

---

## Просмотр результатов локально в браузере

    ./gradlew allureServe

Запускает локальный HTTP-сервер и открывает агрегированный Allure-отчёт в браузере.

---

## Отправка Allure-отчёта в Telegram

    ./gradlew sendAllureTelegram

Отправляет сводное уведомление с ключевыми метриками в настроенный Telegram-канал.

## Удалённый запуск в Jenkins

При выполнении следующих команд тесты будут запускаться удалённо с использованием `Selenoid`.

```
clean :api:${TASK_API} :web:${TASK_WEB}
-DremoteUrl=<selenoid_url>
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
allureReport
```

Параметры сборки:

`SELENOID_URL` — URL эндпоинта Selenoid. По умолчанию: `https://(username):(password)@selenoid.autotests.cloud/wd/hub`  
`BROWSER` — браузер, используемый для выполнения тестов. По умолчанию: `chrome`  
`BROWSER_VERSION` — версия браузера для запуска тестов. По умолчанию: `128.0`  
`BROWSER_SIZE` — размер окна браузера. По умолчанию: `1920x1080`  


# 🔌 Интеграции

## <a href="https://jenkins.autotests.cloud/job/037-vikindor-final_todoist_app/" target="_blank" rel="noopener noreferrer"><img width="4%" src="../media/logos/jenkins.png" alt="Jenkins logo" title="Jenkins"/> Jenkins</a>

Проект запускается через Jenkins-джобу, которая выступает основной CI-точкой входа для выполнения автоматизированных тестов.

Джоба отвечает за запуск тестов, передачу параметров выполнения, а также оркестрацию отчётности и пост-процессинговых шагов.

<p align="center">
<img width="100%" src="../media/screenshots/jenkins_1.jpg" alt="Jenkins Job screenshot" title="Jenkins Job"/>
</p>

## <a href="https://jenkins.autotests.cloud/job/037-vikindor-final_todoist_app/allure" target="_blank" rel="noopener noreferrer"><img width="4%" src="../media/logos/allure_report.png" alt="Allure Report logo" title="Allure Report"/> Allure Report</a>

После выполнения тестов формируется единый Allure-отчёт, агрегирующий результаты всех тестовых слоёв.

Отчёт предоставляет сводную информацию о прогонах API-, Web- и Mobile-тестов, включая детализированные шаги, вложения и диагностическую информацию.

### Обзор

Раздел отображает общую статистику выполнения и распределение тестов по слоям.

<p align="center">
<img width="100%" src="../media/screenshots/allure_report_1.jpg" alt="Allure Report tests overview screenshot" title="Allure Report tests overview"/>
</p>

### Мобильные тесты

Информация по мобильным тестам включает детализированные шаги и приложения (в том числе видео).

<p align="center">
<img width="100%" src="../media/screenshots/allure_report_2.jpg" alt="Allure Report mobile test details screenshot" title="Allure Report mobile test details"/>
</p>

### API тесты

Информация по API-тестам представлена на базе <a href="https://github.com/Vikindor/allure-report-templates" target="_blank" rel="noopener noreferrer">кастомных HTTP шаблонов</a>.

<p align="center">
<img width="100%" src="../media/screenshots/allure_report_3.jpg" alt="Allure Report API test details screenshot" title="Allure Report API test details"/>
</p>

## <a href="https://allure.autotests.cloud/project/5065/" target="_blank" rel="noopener noreferrer"><img width="4%" src="../media/logos/allure_testops.png" alt="Allure TestOps logo" title="Allure TestOps"/> Интеграция с Allure TestOps</a>

Pipeline сборки в Jenkins интегрирован с Allure TestOps для централизованного анализа выполнения тестов и отчётности.

Результаты автоматизированных прогонов публикуются в Allure TestOps, где они агрегируются и визуализируются.  
Дашборд Allure TestOps предоставляет статистику запусков, исторические тренды и общее представление о состоянии тестирования.

### Дашборд

Дашборд отображает высокоуровневое состояние выполнения тестов, динамику и метрики качества по нескольким прогонам.

<p align="center">
<img width="100%" src="../media/screenshots/testops_1.jpg" alt="Allure TestOps dashboard screenshot" title="Allure TestOps dashboard"/>
</p>

### Запуски

Раздел с запусками содержит детализированную информацию о конкретном прогоне, включая результаты отдельных тестов и связанную метаинформацию.

<p align="center">
<img width="100%" src="../media/screenshots/testops_2.jpg" alt="Allure TestOps run details screenshot" title="Allure TestOps run details"/>
</p>

## <a href="https://jira.autotests.cloud/browse/HOMEWORK-1565" target="_blank" rel="noopener noreferrer"><img width="4%" src="../media/logos/atlassian_jira.png" alt="Atlassian Jira logo" title="Atlassian Jira"/> Интеграция с Jira</a>

Allure TestOps интегрирован с Jira для обеспечения трассируемости между автотестами и задачами разработки.

Тест-кейсы и результаты их выполнения связываются с задачами в Jira, что позволяет сопоставлять покрытие тестами, статус выполнения и требования.

<p align="center">
<img width="100%" src="../media/screenshots/jira_1.jpg" alt="Jira task screenshot" title="Jira task"/>
</p>

## <img width="4%" src="../media/logos/telegram.png" alt="Telegram logo" title="Telegram"/> Отчёт в Telegram

После завершения выполнения тестов сгенерированный Allure-отчёт обрабатывается инструментом Allure Notifications и отправляется в настроенный Telegram-канал.

Уведомление содержит краткую сводку по прогону и предоставляет доступ к Allure-отчёту, делая результаты выполнения доступными вне CI-окружения.

<p align="center">
<img src="../media/screenshots/telegram_report.jpg" alt="Telegram report screenshot" title="Telegram report"/>
</p>

## <img width="4%" src="../media/logos/selenoid.png" alt="Selenoid logo" title="Selenoid"/> Видео Selenoid

Каждый прогон UI-тестов сопровождается видеозаписью, сделанной с помощью Selenoid.

Видео прикладывается к соответствующему тесту в отчёте и может использоваться для последующего анализа.

<p align="center">
<img width="100%" src="../media/videos/selenoid.gif" alt="Selenoid recording gif" title="Selenoid recording"/>
</p>

## <img width="4%" src="../media/logos/appium.png" alt="Selenoid logo" title="Selenoid"/> Видео Appium

Каждый прогон UI-тестов сопровождается видеозаписью, сделанной с помощью Appium.

Видео прикладывается к соответствующему тесту в отчёте и может использоваться для последующего анализа.

<p align="center">
<img width="50%" src="../media/videos/appium.gif" alt="Appium recording gif" title="Appium recording"/>
</p>
