package com.technicjelle.twitredirect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// If the device is running Android 12 or newer, show a toast explaining how to manually register redirect links.
		// This is necessary because the app won't be able to handle links until the user manually registers them.
		// This is not needed on older versions of Android, Android will offer a screen of apps that can open this type of link,
		//  and you can set it as default.
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
			Toast.makeText(this, "Tap \"+ Add link\" and add every link you want to redirect.", Toast.LENGTH_LONG).show();
			Uri packageURI = Uri.parse("package:" + getPackageName());
			Intent linkSettingsScreen = new Intent(Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS, packageURI);
			linkSettingsScreen.addCategory(Intent.CATEGORY_DEFAULT);
			linkSettingsScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(linkSettingsScreen);
		}
	}
}
