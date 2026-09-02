buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Cập nhật AGP lên bản tương thích JDK 17
        classpath 'com.android.tools.build:gradle:8.2.2'
        // Cập nhật Kotlin lên phiên bản đã xóa bỏ HasConvention
        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22'
    }
}
