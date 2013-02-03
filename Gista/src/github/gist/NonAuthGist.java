package github.gist;

import github.GithubURL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.numa08.json.Json;
import jp.numa08.models.gist.Gist;
import jp.numa08.models.gist.GistJsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.util.Log;

public class NonAuthGist implements GistApi {

	public List<Gist> publicGists() throws ClientProtocolException,
			IOException, JSONException {
		Log.d("Gista!", "get public gists");
		final String uri = new Uri.Builder().scheme(GithubURL.SCHEME.s())
				.authority(GithubURL.AUTHORITY.s())
				.path(GithubURL.PUBLIC_GIST.s()).build().toString();
		final AndroidHttpClient client = AndroidHttpClient
				.newInstance("Gista!");
		try {
			final HttpGet request = new HttpGet(uri);
			final HttpResponse resp = client.execute(request);
			final String content = EntityUtils.toString(resp.getEntity(),
					"UTF-8");
			final JSONArray json = new JSONArray(content);
			final List<Gist> data = new ArrayList<Gist>();
			for (int i = 0; i < json.length(); i++) {
				Gist gist = Json.toObject(new GistJsonParser(),
						json.getJSONObject(i));
				data.add(gist);
			}
			return data;
		} finally {
			client.close();
		}
	}
}
