plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ms-core"))
    compileOnly(libs.spring.boot.starter.web)
    compileOnly(libs.spring.boot.starter.data.jpa)

    testImplementation(libs.spring.boot.starter.test)

    annotationProcessor(libs.lombok)
    compileOnly(libs.lombok)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
}