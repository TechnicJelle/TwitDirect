package com.technicjelle.twitdirect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TwitterRedirectActivity extends AppCompatActivity {

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
			finish();
			return;
		}

		Toast.makeText(this, "Redirecting to Twitter...", Toast.LENGTH_SHORT).show();

		String path = appLinkData.toString().replaceFirst("^https?://[^/]+/", ""); // Remove scheme and domain/host
		Uri redirectURI = Uri.parse(appLinkData.getScheme() + "://twitter.com/" + path);

		Intent intent = new Intent(appLinkAction, redirectURI);
		startActivity(intent);

		// Finish the activity so it doesn't stay in the back stack
		finish();
	}
}
