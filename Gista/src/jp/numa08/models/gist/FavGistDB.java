package jp.numa08.models.gist;

import java.util.ArrayList;
import java.util.List;

import jp.numa08.models.gist.Gist.Language;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FavGistDB {
	public static final String NAME = "favgist.db";

	public enum Scheme {
		id, file_name, description, user_name, row_url, favgist, language, gist_id;
	}

	final FavGistDBHelper helper;

	public FavGistDB(final Context context) {
		helper = new FavGistDBHelper(context);
	}

	public void insert(Gist gist) {
		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Scheme.user_name.name(), gist.getUserName());
		values.put(Scheme.file_name.name(), gist.getFileName());
		values.put(Scheme.description.name(), gist.getDescription());
		values.put(Scheme.row_url.name(), gist.getRowUrl());
		values.put(Scheme.language.name(), gist.getLanguage().name());
		values.put(Scheme.gist_id.name(), gist.getId());
		db.insert(Scheme.favgist.name(), "", values);
		db.close();
	}

	public Gist select(Gist gist) {
		final SQLiteDatabase db = helper.getReadableDatabase();
		final Cursor cursor = db.query(Scheme.favgist.name(), null,
				Scheme.gist_id.name() + " = ? ", new String[] { gist.getId() },
				null, null, null);
		boolean hasNext = cursor.moveToFirst();
		Gist g = null;
		while (hasNext) {
			final String userName = cursor.getString(cursor
					.getColumnIndex(Scheme.user_name.name()));
			final String description = cursor.getString(cursor
					.getColumnIndex(Scheme.description.name()));
			final String fileName = cursor.getString(cursor
					.getColumnIndex(Scheme.file_name.name()));
			final String rowUrl = cursor.getString(cursor
					.getColumnIndex(Scheme.row_url.name()));
			final Language language = Gist.selectLanguage(cursor
					.getString(cursor.getColumnIndex(Scheme.language.name())));
			final String gistId = cursor.getString(cursor
					.getColumnIndex(Scheme.gist_id.name()));
			hasNext = cursor.moveToNext();
			g = new Gist(userName, fileName, rowUrl, description, language,
					gistId);
		}
		cursor.close();
		db.close();
		return g;
	}

	public List<Gist> all() {
		final SQLiteDatabase db = helper.getReadableDatabase();
		final List<Gist> gists = new ArrayList<Gist>();
		final Cursor cursor = db.query(Scheme.favgist.name(), null, null, null,
				null, null, null);
		boolean hasNext = cursor.moveToFirst();
		while (hasNext) {
			final String userName = cursor.getString(cursor
					.getColumnIndex(Scheme.user_name.name()));
			final String description = cursor.getString(cursor
					.getColumnIndex(Scheme.description.name()));
			final String fileName = cursor.getString(cursor
					.getColumnIndex(Scheme.file_name.name()));
			final String rowUrl = cursor.getString(cursor
					.getColumnIndex(Scheme.row_url.name()));
			final Language language = Gist.selectLanguage(cursor
					.getString(cursor.getColumnIndex(Scheme.language.name())));
			final String gistId = cursor.getString(cursor
					.getColumnIndex(Scheme.gist_id.name()));
			hasNext = cursor.moveToNext();
			final Gist gist = new Gist(userName, fileName, rowUrl, description,
					language, gistId);
			gists.add(gist);
		}
		cursor.close();
		db.close();
		return gists;
	}

}
