plugins {
    base
    kotlin("jvm") version "1.3.21" apply false
}

allprojects {
    group = "xyz.byxor.nfa-bot"
    version = "0.1"

    repositories {
        jcenter()
        mavenCentral()
    }
}

dependencies {
    subprojects.forEach { project -> archives(project) }
}
