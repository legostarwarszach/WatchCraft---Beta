package com.zafcoding.android.watchcraft;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class Loading extends Activity {

	ArrayList<String> array = null;
	HashMap<String, String> name = null;
	HashMap<String, String> sdes = null;
	HashMap<String, String> ldes = null;
	String username = null;
	String id = null;
	ArrayList<Bitmap> draw = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		Intent i = getIntent();
		new SetArrayTask().execute();
		id = i.getStringExtra("uuid");
		username = i.getStringExtra("username");

	}

	public ArrayList<Bitmap> getDraws() {
		return draw;
	}

	// bmImage.setImageBitmap(result);
	/*
	 * UrlReader urlread = new UrlReader(); try {
	 * System.out.println("Start..."); array = urlread.getFromUrl(new
	 * URL("http://watchcraft.zafcoding.com/api/getservers.php?uuid=" + id));
	 * System.out.println(array!=null); } catch (MalformedURLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } catch (IOException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 */

	private class SetArrayTask extends AsyncTask<String, Void, Void> {

		@SuppressWarnings("null")
		protected Void doInBackground(String... urls) {
			HashMap<String, String> name1 = null;
			HashMap<String, String> sdes1 = null;
			HashMap<String, String> ldes1 = null;
			UrlReader urlread = new UrlReader();
			try {
				System.out.println("Start...");
				array = urlread.getFromUrl(new URL(
						"http://watchcraft.zafcoding.com/api/getservers.php?uuid="
								+ id));
				System.out.println(array.toString() + "");
				for (int i = 0; array.size() > i; i++) {
					String iiii = array.get(i);
					iiii = iiii.substring(0, iiii.length() - 1);
					array.set(i, iiii);
					Bitmap mIcon11 = null;
					try {
						// InputStream in = new java.net.URL(
						// "http://watchcraft.zafcoding.com/api/getimg.php?serverid="
						// + array.get(i)).openStream();
						/*
						 * System.out .println(
						 * "http://watchcraft.zafcoding.com/api/getimg.php?serverid="
						 * + array.get(i)); mIcon11 =
						 * BitmapFactory.decodeStream(in); draw.add(mIcon11);
						 */
						/*
						 * if (in != null) in.close();
						 */
						if (iiii.equalsIgnoreCase("214413")) {
							name1.put("214413", "HyPixel");
							sdes1.put("214413", "A fun minigame server");
							ldes1.put("214413",
									"A fun minigame server on the highest degree of MineCraft Servers.");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// Ion.with(imageView).placeholder(R.drawable.twitter).load(imageUrl);
					// addView(i, array);

				}
				name = name1;
				sdes = sdes1;
				ldes = ldes1;
				switchActivity();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
	}

	public void switchActivity() {
		Intent i = new Intent(getApplicationContext(), ServersActity.class);
		i.putExtra("ids", array);
		i.putExtra("name", name);
		i.putExtra("sdes", sdes);
		i.putExtra("ldes", ldes);
		i.putExtra("username", username);
		i.putExtra("uuid", id);
		startActivity(i);
	}

	/*public void addView(int number, ArrayList<String> arraylist) {
		TableLayout ll = (TableLayout) findViewById(R.id.servertableview);
		TableRow row = new TableRow(this);
		TableRow.LayoutParams lp = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT);
		row.setLayoutParams(lp);
		final TextView tv = new TextView(this);
		Ion.with(this)
				.load("http://zafcoding.com/watchcraft/api/getname.php?serverid="
						+ arraylist.get(number)).asString()
				.setCallback(new FutureCallback<String>() {
					@Override
					public void onCompleted(Exception e, String result) {
						tv.setText(result);
					}
				});
		row.addView(tv);
		ll.addView(row, number);
	}*/

}
