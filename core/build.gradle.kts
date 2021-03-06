plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    // Dependencies for THPSVideos feature (Youtube API v3)
    implementation("com.google.api-client:google-api-client:1.23.0")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.23.0")
    implementation("com.google.apis:google-api-services-youtube:v3-rev212-1.25.0")

    testImplementation("junit", "junit", "4.12")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}
