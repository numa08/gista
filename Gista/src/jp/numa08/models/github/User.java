package jp.numa08.models.github;

public class User {
	private int level;
	private int exp;
	private final int id;

	public User(int level, int exp, int id) {
		super();
		this.level = level;
		this.exp = exp;
		this.id = id;
	}

	public User(User user) {
		this.level = user.getLevel();
		this.exp = user.getExp();
		this.id = user.getId();
	}

	public boolean appendExp(int expr) {
		this.exp += expr;
		if(this.exp > level * 10) {
			level++;
			this.exp -= level * 10;
		}
		return exp > 0;
	}

	public int getLevel() {
		return level;
	}

	public int getExp() {
		return exp;
	}

	public int getId() {
		return id;
	}

}
