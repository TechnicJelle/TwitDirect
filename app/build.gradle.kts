plugins {
	alias(libs.plugins.android.application)
}

android {
	namespace = "com.technicjelle.twitdirect"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.technicjelle.twitdirect"
		minSdk = 26
		targetSdk = 34
		versionCode = 2
		versionName = "1.1"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}

dependencies {
	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.activity)
}
