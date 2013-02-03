package jp.numa08.models.gist;

import java.util.List;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class FavGistLoader extends AsyncTaskLoader<List<Gist>> {

	public FavGistLoader(Context context) {
		super(context);
	}

	@Override
	protected void onStartLoading() {
		super.onStartLoading();
		forceLoad();
	}

	@Override
	public List<Gist> loadInBackground() {
		return new FavGistDB(getContext()).all();
	}

}
