import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
}

// JVM TARGET ---------------------------------------------------

val JVM_TARGET = "1.8"

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions { jvmTarget = JVM_TARGET }

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions { jvmTarget = JVM_TARGET }

// SOURCE SETS ---------------------------------------------------

sourceSets["main"].withConvention(KotlinSourceSet::class) {
    kotlin.srcDir("src/main/kotlin")
}

sourceSets["test"].withConvention(KotlinSourceSet::class) {
    kotlin.srcDir("src/test/kotlin")
}

// DEPENDENCIES ---------------------------------------------------
dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("junit", "junit", "4.12")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}