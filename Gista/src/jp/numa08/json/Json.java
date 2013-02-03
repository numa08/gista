package jp.numa08.json;

import org.json.JSONObject;

public class Json<T> {
	public static <T> T toObject(JsonParser<T> parser, JSONObject json) {
		return parser.toObject(json);
	}
}
