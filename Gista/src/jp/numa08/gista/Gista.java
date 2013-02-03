package jp.numa08.gista;

import jp.numa08.models.gist.Gist;
import jp.numa08.models.github.UserDB;
import jp.numa08.view.FavGistTabListener;
import jp.numa08.view.GistListFragment;
import jp.numa08.view.PublicGistTabListner;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class Gista extends Activity implements OnItemClickListener,
		OnItemLongClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		final Tab publicTab = actionBar.newTab();
		publicTab.setText("public gist");
		publicTab.setTabListener(new PublicGistTabListner(this, "public",
				GistListFragment.class));
		actionBar.addTab(publicTab);

		final Tab favTab = actionBar.newTab();
		favTab.setText("favorite");
		favTab.setTabListener(new FavGistTabListener(this, "favorite",
				GistListFragment.class));
		actionBar.addTab(favTab);
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

	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View arg1,
			int position, long arg3) {
		final Gist gist = (Gist) adapter.getItemAtPosition(position);
		GistaFav gistafav = new GistaFav(this);
		gistafav.fav(gist);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position,
			long arg3) {
		final Gist gist = (Gist) adapter.getItemAtPosition(position);
		final Uri uri = Uri.parse(gist.getRowUrl());
		final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

}
