package com.zafcoding.android.watchcraft;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ServersActity extends Activity {

	String id = null;
	String username = null;
	ArrayList<String> array = null;
	ArrayList<Bitmap> draw = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstview);
		Intent i = getIntent();
		id = i.getStringExtra("uuid");
		username = i.getStringExtra("username");
		array = i.getStringArrayListExtra("ids");
		Loading loading = new Loading();
		draw = loading.getDraws();
		TextView tx = (TextView) findViewById(R.id.welcomeuptext);
	}

	/*public static Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
		} catch (Exception e) {
			return null;
		}
	}*/
}
