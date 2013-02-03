package jp.numa08.gista;

import jp.numa08.models.github.UserDB;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class AppActivity extends Activity  {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, Menu.FIRST, Menu.NONE, "(*´ڡ`●)");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST:
			final String text = String.format("(*´ڡ`●) レベル%d なう", new UserDB(
					this).me().getLevel());
			Uri tweet = new Uri.Builder().scheme("https")
					.authority("twitter.com").path("intent/tweet")
					.appendQueryParameter("text", text)
					.appendQueryParameter("hashtags", "qiita_hackathon")
					.build();
			Log.d("Gista!", tweet.toString());
			final Intent intent = new Intent(Intent.ACTION_VIEW, tweet);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
