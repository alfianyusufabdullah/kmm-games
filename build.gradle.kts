buildscript {

    val kotlinVersion = "1.4.0"

    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.0.1")
    }
}
group = "alfianyusufabdullah.exp.games"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
