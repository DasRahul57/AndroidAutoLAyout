import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.math.sign

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.troxy.autolayout"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }


}

val versionName = "2.1.0"



dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}


afterEvaluate {

    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components.findByName("release"))
                groupId = "io.github.TroxyRahul"
                artifactId = "AndroidAutoLayout"
                version = versionName

                pom {
                    name.value("AndroidAutoLayout")
                    description.value("Powerful and flexible RecyclerAdapter")
                    url.value("https://github.com/TroxyRahul/AndroidAutoLayout")

                    licenses {
                        license {
                            //协议类型
                            name.value("The MIT License")
                            url.value("https://github.com/TroxyRahul/AndroidAutoLayout/blob/master/LICENSE")
                        }
                    }

                    developers {
                        developer {
                            id.value("TroxyRahul")
                            name.value("Troxy")
                            email.value("gamingparsha@gmail.com")
                        }
                    }

                    scm {
                        connection.value("scm:git@github.com/CymChad/TroxyRahul/AndroidAutoLayout.git")
                        developerConnection.value("scm:git@github.com/TroxyRahul/AndroidAutoLayout.git")
                        url.value("https://github.com/TroxyRahul/AndroidAutoLayout")
                    }
                }
            }

        }

        repositories {
            maven {
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
//                credentials {
//                    username = ossrhUsername
//                    password = ossrhPassword
//                }
            }
        }

    }

}