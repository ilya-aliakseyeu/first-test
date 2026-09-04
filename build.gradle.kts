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
    testImplementation("io.qameta.allure:allure-selenide:${allureVersion}")
}

tasks.test {
    useJUnitPlatform()
}

allure {
    report {
        version.set(allureVersion)
    }
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set(allureVersion)
            }
        }
    }
}