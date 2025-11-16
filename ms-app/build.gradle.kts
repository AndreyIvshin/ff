plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ms-core"))
    implementation(project(":ms-impl"))
    implementation(project(":shared-impl"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.h2)
    implementation(libs.postgres)

    testImplementation(libs.spring.boot.starter.test)

    annotationProcessor(libs.lombok)
    compileOnly(libs.lombok)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "com.epam.ff.ms.App"
}
