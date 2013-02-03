package jp.numa08.models.github;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBOpenHelper extends SQLiteOpenHelper {

	public UserDBOpenHelper(Context context) {
		super(context, UserDB.NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + UserDB.Scheme.user + " ( "
				+ UserDB.Scheme.id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ UserDB.Scheme.level + " INTEGER, " + UserDB.Scheme.exp
				+ " INTEGER);");
		ContentValues values = new ContentValues();
		values.put(UserDB.Scheme.level.name(), 1);
		values.put(UserDB.Scheme.exp.name(), 1);
		db.insert(UserDB.Scheme.user.name(), null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
