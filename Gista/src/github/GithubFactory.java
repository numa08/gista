package github;

public class GithubFactory {

	public static Github instance() {
		return new NonAuthGithub();
	}
}
