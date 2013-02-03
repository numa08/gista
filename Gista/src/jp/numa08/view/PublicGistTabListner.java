package jp.numa08.view;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class PublicGistTabListner extends GistListTabListener<GistListFragment> {

	private Fragment mFragment;

	public PublicGistTabListner(Activity mActivity, String mTag,
			Class<GistListFragment> mClass) {
		super(mActivity, mTag, mClass);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		super.onTabSelected(tab, ft);
		if (mFragment != null) {
			ft.attach(mFragment);
			return;
		}

		final Bundle args = new Bundle();
		args.putBoolean("fav", false);
		mFragment = Fragment.instantiate(getActivity(), getmClass().getName(),
				args);
		ft.add(android.R.id.content, mFragment, getTag());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		super.onTabUnselected(tab, ft);
		if (mFragment != null) {
			ft.detach(mFragment);
		}
	}

}
