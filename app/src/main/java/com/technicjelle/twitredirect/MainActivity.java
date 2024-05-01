package com.technicjelle.twitredirect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ATTENTION: This was auto-generated to handle app links.
		Intent appLinkIntent = getIntent();
		String appLinkAction = appLinkIntent.getAction();
		Uri appLinkData = appLinkIntent.getData();
		// END ATTENTION

		// If appLinkData is null, the app was probably opened directly, without a link
		if (appLinkData == null) {
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
			return;
		}

		Log.d("TwitRedirect", "appLinkAction: " + appLinkAction);
		Log.d("TwitRedirect", "appLinkData: " + appLinkData);

		Toast.makeText(this, "Redirecting to Twitter...", Toast.LENGTH_SHORT).show();

		String path = appLinkData.toString().replaceFirst("^https?://[^/]+", ""); // Remove scheme and domain/host
		Log.d("TwitRedirect", "path: " + path);

		Uri redirectURI = Uri.parse(appLinkData.getScheme() + "://twitter.com/" +
				path.replaceFirst("^/", "")); // Remove leading slash
		Log.d("TwitRedirect", "redirectURI: " + redirectURI);

		Intent intent = new Intent(appLinkAction, redirectURI);
		startActivity(intent);

		// Finish the activity so it doesn't stay in the back stack
		finish();
	}
}
