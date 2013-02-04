package jp.numa08.models.gist;

import android.text.TextUtils;

public class Gist {
	private String userName;
	private String fileName;
	private String rowUrl;
	private String description;
	private Language language;

	public enum Language {
		other, javascript, scala, java, ruby, ;
	}

	public Gist(String userName, String fileName, String rowUrl,
			String description, Language language) {
		super();
		this.userName = userName;
		this.fileName = fileName;
		this.rowUrl = rowUrl;
		this.description = description;
		this.language = language;
	}

	public static Language selectLanguage(String language) {
		if (language.equalsIgnoreCase("javascript")) {
			return Language.javascript;
		} else if (language.equalsIgnoreCase("java")) {
			return Language.java;
		} else if (language.equalsIgnoreCase("scala")) {
			return Language.scala;
		} else if (language.equalsIgnoreCase("ruby")) {
			return Language.ruby;
		}
		return Language.other;
	}

	public String getUserName() {
		return returnContent(userName);
	}

	public String getFileName() {
		return returnContent(fileName);
	}

	public String getRowUrl() {
		return returnContent(rowUrl);
	}

	public String getDescription() {
		return returnContent(description);
	}

	private String returnContent(final String content) {
		return TextUtils.isEmpty(content) ? "" : content;
	}

	public Language getLanguage() {
		return language;
	}

}
