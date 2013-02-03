package jp.numa08.models.gist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavGistDBHelper extends SQLiteOpenHelper {

	public FavGistDBHelper(Context context) {
		super(context, FavGistDB.NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + FavGistDB.Scheme.favgist.name() + " ( "
				+ FavGistDB.Scheme.id.name()
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ FavGistDB.Scheme.file_name.name() + " TEXT, "
				+ FavGistDB.Scheme.description.name() + " TEXT, "
				+ FavGistDB.Scheme.user_name.name() + " TEXT, "
				+ FavGistDB.Scheme.row_url.name() + " TEXT, "
				+ FavGistDB.Scheme.language.name() + " TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
