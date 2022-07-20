import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
}

sourceSets {
    //main.kotlin.srcDirs = ['src']
    //main.resources.srcDirs = ['resources']
    //test.kotlin.srcDirs = ['test']
}

kotlinDslPluginOptions {

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

dependencies {

}

