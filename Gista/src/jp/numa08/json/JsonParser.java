package jp.numa08.json;

import org.json.JSONObject;

public interface JsonParser<T> {

	T toObject(JSONObject json);
}
