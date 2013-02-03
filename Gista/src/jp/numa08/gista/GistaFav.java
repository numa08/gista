package jp.numa08.gista;

import jp.numa08.models.gist.FavGistDB;
import jp.numa08.models.gist.Gist;
import jp.numa08.models.github.User;
import jp.numa08.models.github.UserDB;
import android.content.Context;
import android.widget.Toast;

public class GistaFav {
	private Context context;
	
	public GistaFav(Context context) {
		this.context = context;
	}
	
	public void fav(Gist gist) {
		new FavGistDB(context).insert(gist);

		User old = new UserDB(context).me();
		User now = new User(old);
		now.appendExp(10);
		if (now.getLevel() > old.getLevel()) {
			Toast.makeText(context,
					String.format("レベルが%dに上がった！！", now.getLevel()),
					Toast.LENGTH_SHORT).show();
			new UserDB(context).update(now);
		}
	}

}
