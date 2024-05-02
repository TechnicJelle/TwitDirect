package com.technicjelle.twitdirect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RedirectActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ATTENTION: This was auto-generated to handle app links.
		Intent appLinkIntent = getIntent();
		String appLinkAction = appLinkIntent.getAction();
		Uri appLinkData = appLinkIntent.getData();
		// END ATTENTION

		// If appLinkData is null, the app was probably opened directly, without a link, so cancel the activity
		if (appLinkData == null) {
			Log.d("TwitDirect", "appLinkData is null");
			finish();
			return;
		}

		Log.d("TwitDirect", "appLinkAction: " + appLinkAction);
		Log.d("TwitDirect", "appLinkData: " + appLinkData);

		Toast.makeText(this, "Redirecting to Twitter...", Toast.LENGTH_SHORT).show();

		String path = appLinkData.toString().replaceFirst("^https?://[^/]+", ""); // Remove scheme and domain/host
		Log.d("TwitDirect", "path: " + path);

		Uri redirectURI = Uri.parse(appLinkData.getScheme() + "://twitter.com/" +
				path.replaceFirst("^/", "")); // Remove leading slash
		Log.d("TwitDirect", "redirectURI: " + redirectURI);

		Intent intent = new Intent(appLinkAction, redirectURI);
		startActivity(intent);

		// Finish the activity so it doesn't stay in the back stack
		finish();
	}
}
