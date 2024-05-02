package com.technicjelle.twitredirect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		Button button = findViewById(R.id.link_settings_open_button);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
			button.setOnClickListener(v -> {
				Uri packageURI = Uri.parse("package:" + getPackageName());
				Intent linkSettingsScreen = new Intent(Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS, packageURI);
				linkSettingsScreen.addCategory(Intent.CATEGORY_DEFAULT);
				linkSettingsScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(linkSettingsScreen);
			});
		} else {
			button.setEnabled(false);
			button.setText(R.string.not_necessary);
		}
	}
}
