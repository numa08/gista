package jp.numa08.models.gist;

import java.util.Iterator;

import jp.numa08.json.JsonParser;
import jp.numa08.models.gist.Gist.Language;

import org.json.JSONException;
import org.json.JSONObject;

public class GistJsonParser implements JsonParser<Gist> {

	private enum GistJson {
		DESCRIPTION("description"), FILE("files"), FILE_NAME("filename"), ROW_URL(
				"raw_url"), USER("user"), LOGIN("login"), LANGUAGE("language");
		private final String key;

		private GistJson(final String key) {
			this.key = key;
		}

		private String key() {
			return this.key;
		}
	}

	@Override
	public Gist toObject(JSONObject json) {
		String userName, description, fileName, rowUrl;
		Language language = Language.other;
		userName = description = fileName = rowUrl = "";
		try {
			userName = accureireUserName(json);
			description = accureireDescription(json);
			final JSONObject files = json.getJSONObject(GistJson.FILE.key());
			final Iterator<String> keys = files.keys();
			while (keys.hasNext()) {
				JSONObject file = files.getJSONObject(keys.next());
				fileName = accureireFileName(file);
				rowUrl = accureireRowUrl(file);
				language = accurireLanguage(file);
			}
			return new Gist(userName, fileName, rowUrl, description, language);
		} catch (JSONException e) {
			e.printStackTrace();
			return new Gist(userName, fileName, rowUrl, description, language);
		}
	}

	private String accureireUserName(JSONObject root) throws JSONException {
		if (root.isNull(GistJson.USER.key())) {
			return "";
		}
		JSONObject user = root.getJSONObject(GistJson.USER.key());
		return user.getString(GistJson.LOGIN.key());
	}

	private String accureireFileName(JSONObject file) throws JSONException {
		if (file.isNull(GistJson.FILE_NAME.key())) {
			return "";
		}
		return file.getString(GistJson.FILE_NAME.key());
	}

	private String accureireRowUrl(JSONObject file) throws JSONException {
		if (file.isNull(GistJson.ROW_URL.key())) {
			return "";
		}
		return file.getString(GistJson.ROW_URL.key());
	}

	private String accureireDescription(JSONObject root) throws JSONException {
		if (root.isNull(GistJson.DESCRIPTION.key())) {
			return "";
		}
		return root.getString(GistJson.DESCRIPTION.key());
	}

	private Language accurireLanguage(JSONObject file) throws JSONException {
		if (file.isNull(GistJson.LANGUAGE.key())) {
			return Language.other;
		}
		final String language = file.getString(GistJson.LANGUAGE.key());
		return Gist.selectLanguage(language);
	}

}
