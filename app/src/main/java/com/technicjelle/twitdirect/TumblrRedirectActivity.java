package com.technicjelle.twitdirect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TumblrRedirectActivity extends AppCompatActivity {
	static final Pattern blogPostPattern = Pattern.compile("^(?:www\\.)?([^/]+?)\\.(.*)/post/(.*)$", Pattern.CASE_INSENSITIVE);

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

		String baseLink = appLinkData.toString().replaceFirst("^https?://", ""); // Remove scheme

		final String path;
		if (baseLink.contains("/post/")) {
			//this is an actual blog URL link
			//blogname.tumblr.com/post/1234567890

			Matcher blogPostMatcher = blogPostPattern.matcher(baseLink);
			if(!blogPostMatcher.find()) {
				Toast.makeText(this, "⚠️ Failed to match blog post URL", Toast.LENGTH_SHORT).show();
				finish();
				return;
			}
			String blogName = blogPostMatcher.group(1);
			String postID = blogPostMatcher.group(3);
			path = blogName + "/" + postID;
		} else {
			//this is a dashboard link
			//www.tumblr.com/blogname/1234567890

			path = baseLink.replaceFirst("^[^/]+/", ""); // Remove everything until the first slash
		}

		Uri redirectURI = Uri.parse(appLinkData.getScheme() + "://www.tumblr.com/" + path);

		Intent intent = new Intent(appLinkAction, redirectURI);
		Toast.makeText(this, "Redirecting to Tumblr...", Toast.LENGTH_SHORT).show();
		startActivity(intent);

		// Finish the activity so it doesn't stay in the back stack
		finish();
	}
}
