package jp.numa08.view;

import java.util.List;

import jp.numa08.gista.R;
import jp.numa08.models.gist.Gist;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GistAdapter extends ArrayAdapter<Gist> {

	private final LayoutInflater mInflater;

	public GistAdapter(Context context) {
		super(context, R.layout.row_gist);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(List<Gist> data) {
		clear();
		if (data != null) {
			addAll(data);
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row_gist, null);
		}

		Gist gist = getItem(position);
		((TextView) convertView.findViewById(R.id.filne_name)).setText(gist
				.getFileName());
		((TextView) convertView.findViewById(R.id.description)).setText(gist
				.getDescription());
		((TextView) convertView.findViewById(R.id.user_name)).setText(gist
				.getUserName());
		int languageImage = R.drawable.ic_launcher;
		switch (gist.getLanguage()) {
		case java:
			languageImage = R.drawable.java;
			break;
		case javascript:
			languageImage = R.drawable.javascritp;
			break;
		case scala:
			languageImage = R.drawable.scala;
			break;
		default:
			languageImage = R.drawable.ic_launcher;
			break;
		}
		((ImageView) convertView.findViewById(R.id.language_image))
				.setImageResource(languageImage);
		return convertView;
	}

}
