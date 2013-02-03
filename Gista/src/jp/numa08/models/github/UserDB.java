package jp.numa08.models.github;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDB {
	public static final String NAME = "user.db";

	public enum Scheme {
		id, level, exp, user, ;

		public String toString() {
			return name();
		};
	}

	final UserDBOpenHelper helper;

	public UserDB(Context context) {
		helper = new UserDBOpenHelper(context);
	}

	public void update(final User user) {
		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Scheme.level.name(), user.getLevel());
		values.put(Scheme.exp.name(), user.getExp());
		db.update(Scheme.user.name(), values, Scheme.id + " = " + user.getId(),
				null);
	}

	public User me() {
		final SQLiteDatabase db = helper.getReadableDatabase();
		final List<User> users = new ArrayList<User>();
		final Cursor cursor = db.query(Scheme.user.name(), null, null, null,
				null, null, null);
		boolean hasNext = cursor.moveToFirst();
		while (hasNext) {
			final int id = cursor
					.getInt(cursor.getColumnIndex(Scheme.id.name()));
			final int level = cursor.getInt(cursor.getColumnIndex(Scheme.level
					.name()));
			final int exp = cursor.getInt(cursor.getColumnIndex(Scheme.exp
					.name()));
			hasNext = cursor.moveToNext();
			final User user = new User(level, exp, id);
			users.add(user);
		}
		cursor.close();
		db.close();
		return users.get(0);

	}

}
