package jp.numa08.models.gist;

import github.Github;
import github.GithubFactory;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

public class GistLoader extends AsyncTaskLoader<List<Gist>> {

    private List<Gist> mData;

    public GistLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mData != null) {
            deliverResult(mData);
        } else {
            forceLoad();
        }

    }

    @Override
    public List<Gist> loadInBackground() {
        final Github github = GithubFactory.instance();
        try {
            mData = github.publicGists();
            return mData;
        } catch (ClientProtocolException e) {
            Log.e("Gista!", "network error");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Gista!", "content error");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("Gista!", "content error");
            e.printStackTrace();
        }
        return null;
    }

}