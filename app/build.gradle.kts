import com.android.build.api.dsl.ApplicationExtension

plugins {
	alias(libs.plugins.android.application)
}

configure<ApplicationExtension> {
	namespace = "com.technicjelle.twitdirect"
	compileSdk = 36

	defaultConfig {
		applicationId = "com.technicjelle.twitdirect"
		minSdk = 26
		targetSdk = 36
		versionCode = 3
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
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
}

dependencies {
	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.activity)
}
