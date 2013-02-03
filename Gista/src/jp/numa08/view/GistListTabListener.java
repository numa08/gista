package jp.numa08.view;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class GistListTabListener<T extends Fragment> implements TabListener {

	private final Activity mActivity;
	private final String mTag;
	private final Class<T> mClass;

	public GistListTabListener(Activity mActivity, String mTag, Class<T> mClass) {
		super();
		this.mActivity = mActivity;
		this.mTag = mTag;
		this.mClass = mClass;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	protected Activity getActivity() {
		return mActivity;
	}

	protected String getTag() {
		return mTag;
	}

	protected Class<T> getmClass() {
		return mClass;
	}

}
