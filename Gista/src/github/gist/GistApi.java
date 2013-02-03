package github.gist;

import java.io.IOException;
import java.util.List;

import jp.numa08.models.gist.Gist;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public interface GistApi {
	public List<Gist> publicGists() throws ClientProtocolException,
			IOException, JSONException;
}
