package jp.numa08.gista;

import java.util.Random;

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
		
		Random rnd = new Random();
		int exp = rnd.nextInt(100) + 5;
		now.appendExp(exp);
		
		if (now.getLevel() > old.getLevel()) {
			Toast.makeText(context,
					String.format("レベルが%dに上がった！！", now.getLevel()),
					Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(context,
					String.format("%d経験値を取得した！！", exp),
					Toast.LENGTH_SHORT).show();
		}
		new UserDB(context).update(now);
	}

}
