import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "5.1.0"
    application
}

dependencies {
    compile(project(":core"))
    compile(kotlin("stdlib"))
    compile("net.dv8tion:JDA:4.0.0_50")
}

application {
    mainClassName = "xyz.byxor.nfabot.ApplicationKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    baseName = "NFA_Bot_Discord"
    classifier = ""
    version = ""
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}