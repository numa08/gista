package github;

public enum GithubURL {
	SCHEME("https"), AUTHORITY("api.github.com"), GIST("/gists"), PUBLIC_GIST(
			"/gists/public"), ;
	private final String s;

	private GithubURL(final String s) {
		this.s = s;
	}

	public String s() {
		return this.s;
	}
}
