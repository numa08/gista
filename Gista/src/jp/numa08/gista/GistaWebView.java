package jp.numa08.gista;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class GistaWebView extends AppActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gista_webview);
		
		Intent intent = getIntent();
		String uri = intent.getStringExtra("uri");
		WebView webview = (WebView) findViewById(R.id.gista_webview);
		webview.loadUrl(uri);
	}
}
