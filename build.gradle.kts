plugins {
    `java-library`
    `maven-publish`
    signing
    checkstyle
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()  // was jcenter() which is dying
    google()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/releases/")
}

dependencies {

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    //api(libs.commons.math3)

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation(libs.jsr305)
    implementation(libs.slf4j.api)
    implementation(libs.bundles.jackson)
    implementation(libs.bundles.testing)

    testImplementation(libs.immutable.annotations)
    implementation(libs.jackson.annotations)
    testImplementation(libs.jackson.annotations)
    annotationProcessor(libs.immutable)
    testAnnotationProcessor(libs.immutable)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

group = "com.codeheadsystems"
//version = "1.0.12-SNAPSHOT"
version = "1.0.12"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "codehead-test"
            from(components["java"])
            pom {
                name = "Codehead-Test"
                description = "Testing utilities"
                url = "https://github.com/wolpert/codehead-test"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "wolpert"
                        name = "Ned Wolpert"
                        email = "ned.wolpert@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/wolpert/codehead-test.git"
                    developerConnection = "scm:git:ssh://github.com/wolpert/codehead-test.git"
                    url = "https://github.com/wolpert/codehead-test/"
                }
            }

        }
    }
    repositories {
        maven {
            val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            name = "ossrh"
            credentials(PasswordCredentials::class)
        }
    }
}
signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}
tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

// git checkout main 
// git pull
// make changes
// git checkout -b verxion_1_0_12 && gradle clean build test && git add . && git commit -m "Updated version" && git push origin `git branch --show-current` && gh pr create --title "auto-approve check" --body "auto-approve"
// gradle publishToSonatype closeAndReleaseSonatypeStagingRepository
nexusPublishing {
    repositories {
        sonatype()
    }
}
