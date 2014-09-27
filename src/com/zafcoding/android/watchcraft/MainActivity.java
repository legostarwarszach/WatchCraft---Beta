package com.zafcoding.android.watchcraft;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	boolean check = false;
	boolean doit = false;
	boolean isopen = false;
	String aa = null;
	String ab = null;
	int errorcode = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		isopen = true;
	}

	
	/*
	 * public void doinMain(int id){ if(id==0){ return; } if(id==100){ TextView
	 * tx = (TextView) findViewById(R.id.errortextloginview);
	 * System.out.print("100"); tx.append("It worked???"); }if(id==101){
	 * TextView tx = (TextView) findViewById(R.id.errortextloginview);
	 * System.out.print("100"); tx.append("Invalid username or password.");
	 * }if(id==200){ System.out.print("102");
	 * setContentView(R.layout.firstview); } }
	 */

	public void clickedLogin(View view) {
		check = true;
		new Connection().execute();
	}

	public void switchActivity(String username, String uuid) {
		Intent i = new Intent(getApplicationContext(), Loading.class);
		i.putExtra("username", username);
		i.putExtra("uuid", uuid);
		startActivity(i);
	}

	@SuppressWarnings("unused")
	private class Connection extends AsyncTask<String, Void, String> implements
			Runnable {
		@Override
		protected String doInBackground(String... params) {
			if (check) {
				check = false;
				EditText username = (EditText) findViewById(R.id.username);
				EditText password = (EditText) findViewById(R.id.password);
				System.out.println(username.getText());
				System.out.println(password.getText());

				MinecraftAuthClass mauth = new MinecraftAuthClass(username
						.getText().toString(), password.getText().toString());
				if (mauth.getError() == 403) {
					System.out.println("Invalid username / password");
				}
				if (mauth.getError() == 200) {
					System.out.println("200");
				}
				System.out.println(mauth.getError());
				System.out.println(mauth.getResponse());
				try {
					JSONObject jsonObj = new JSONObject(mauth.getResponse());
					if (mauth.getError() == 200) {
						JSONObject jsonObj1 = new JSONObject(jsonObj.get(
								"selectedProfile").toString());
						String id = jsonObj1.get("id").toString();
						String name = jsonObj1.getString("name");
						UrlReader ur = new UrlReader();
						try {
							System.out
									.println("http://watchcraft.zafcoding.com/api/checkuser.php?uuid="
											+ id);
							ArrayList<String> urlist = ur.getFromUrl(new URL(
									"http://watchcraft.zafcoding.com/api/checkuser.php?uuid="
											+ id));
							if (urlist.toString().contains("[true<br>]")) {
								System.out.println("true");
								switchActivity(name, id);
							} else {
								ArrayList<String> ur1list = ur
										.getFromUrl(new URL(
												"http://watchcraft.zafcoding.com/api/newuser.php?username="
														+ name + "&uuid=" + id
														+ ""));
								switchActivity(name, id);
							}
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		

		@Override
		public void run() {
			
		}
	}
}
