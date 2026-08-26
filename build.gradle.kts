plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("com.codeborne:selenide:7.17.0")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")
    testImplementation("net.datafaker:datafaker:2.7.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
