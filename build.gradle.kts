val allureVersion = "2.32.0"

plugins {
    id("java")
    id("io.qameta.allure") version "3.0.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("com.codeborne:selenide:7.17.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("net.datafaker:datafaker:2.7.0")
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    testImplementation("io.qameta.allure:allure-selenide:$allureVersion")
    implementation("org.aeonbits.owner:owner:1.0.4")
}

val testSourceSet = sourceSets.test.get()

// Функция для настройки кастомных тестовых тасков
fun Test.configureTestTask(baseTag: String) {
    // Привязываем скомпилированные тесты и их classpath (убирает ошибку NO-SOURCE)
    testClassesDirs = testSourceSet.output.classesDirs
    classpath = testSourceSet.runtimeClasspath

    // Прокидываем системные переменные
    systemProperty("run", System.getProperty("run"))
    systemProperty("browser", System.getProperty("browser", "chrome"))

    // Настройка JUnit 5 и тегов
    val tag = System.getProperty("tag")
    useJUnitPlatform {
        if (!tag.isNullOrBlank()) {
            includeTags("$baseTag & $tag")
        } else {
            includeTags(baseTag)
        }
    }

    // Вывод логов прохождения тегов в консоль
    testLogging {
        events("passed", "failed", "skipped", "standardOut")
    }
}

tasks.test {
    useJUnitPlatform()
    systemProperty("run", System.getProperty("run"))
    systemProperty("browser", System.getProperty("browser", "chrome"))
}

tasks.register<Test>("uiTest") {
    configureTestTask("UI")
}

tasks.register<Test>("apiTest") {
    configureTestTask("API")
}

allure {
    report {
        version.set(allureVersion)
    }
}