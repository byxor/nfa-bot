plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "xyz.byxor.nfabot.ApplicationKt"
}

dependencies {
    compile(project(":core"))
    compile(kotlin("stdlib"))
}

