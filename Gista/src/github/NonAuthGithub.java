package github;

import github.gist.NonAuthGist;

import java.io.IOException;
import java.util.List;

import jp.numa08.models.gist.Gist;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public class NonAuthGithub implements Github {

	@Override
	public List<Gist> publicGists() throws ClientProtocolException,
			IOException, JSONException {
		return new NonAuthGist().publicGists();
	}

}
