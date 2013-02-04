package jp.numa08.models.gist;

import jp.numa08.models.gist.Gist.Language;
import junit.framework.TestCase;

public class GistTest extends TestCase {

	String userName = "numa08";
	String fileName = "github.java";
	String rowUrl = "http://github.java";
	String description = "this simple code operates github!!";
	Language language = Language.java;
	String id = "A113";

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testユーザー名取得ができる() throws Exception {
		Gist gist = new Gist(userName, fileName, rowUrl, description, language,
				id);
		String expected = userName;
		String actual = gist.getUserName();
		assertEquals("ユーザー名が取れる。", expected, actual);
	}

	public void testユーザー名の取得ができない() throws Exception {
		Gist gist = new Gist("", fileName, rowUrl, description, language, id);

		String expected = "";
		String actual = gist.getUserName();

		assertEquals("空文字が帰ってくる", expected, actual);

		gist = new Gist(null, fileName, rowUrl, description, language, id);

		expected = "";
		actual = gist.getUserName();

		assertEquals("空文字が帰ってくる", expected, actual);
	}
}
