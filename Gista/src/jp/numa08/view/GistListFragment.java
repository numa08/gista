package jp.numa08.view;

import java.util.List;

import jp.numa08.gista.Gista;
import jp.numa08.models.gist.FavGistLoader;
import jp.numa08.models.gist.Gist;
import jp.numa08.models.gist.GistLoader;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;

public class GistListFragment extends ListFragment implements
		LoaderCallbacks<List<Gist>> {

	GistAdapter mAdapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter = new GistAdapter(getActivity());
		setListAdapter(mAdapter);
		setListShown(false);
		getLoaderManager().initLoader(0, null, this);

		if (getActivity() instanceof Gista) {
			Gista gista = (Gista) getActivity();
			getListView().setOnItemClickListener(gista);
			getListView().setOnItemLongClickListener(gista);
		}
	}

	@Override
	public Loader<List<Gist>> onCreateLoader(int id, Bundle args) {
		Bundle fragmentArg = getArguments();
		boolean isFavriteList = fragmentArg.getBoolean("fav");
		if (isFavriteList) {
			return new FavGistLoader(getActivity());
		}
		return new GistLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<Gist>> arg0, List<Gist> data) {
		mAdapter.setData(data);
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Gist>> arg0) {
		mAdapter.setData(null);
	}

}
