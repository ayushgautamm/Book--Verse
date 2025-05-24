plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false // Add this line
}
