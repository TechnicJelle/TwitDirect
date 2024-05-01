plugins {
	alias(libs.plugins.android.application)
}

android {
	namespace = "com.technicjelle.twitredirect"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.technicjelle.twitredirect"
		minSdk = 26
		targetSdk = 34
		versionCode = 1
		versionName = "0.1"

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
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
}

dependencies {
	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.activity)
}
