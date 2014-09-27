package com.zafcoding.android.watchcraft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
 
import org.json.JSONException;
import org.json.JSONObject;
 
public class MinecraftAuthClass {
 
	static int httpreturn = 0;
	String respone = null;
              
		public int getError(){
			return httpreturn;
		}
	
		public String getResponse(){
			return respone;
		}
		
		public MinecraftAuthClass(String username, String password){
			String string = null;
            try {
                    string = Jake(username, password);
                    try {
						respone = httpRequest(new URL("https://authserver.mojang.com/authenticate"),
						                string);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
		}
	
        private static String Jake(String username, String password)
                        throws JSONException {
                JSONObject json1 = new JSONObject();
                json1.put("name", "Minecraft");
                json1.put("version", 1);
                JSONObject json = new JSONObject();
                json.put("agent", json1);
                json.put("username", username);
                json.put("password", password);
 
                return json.toString();
        }
 
        public static String httpRequest(URL url, String content) throws Exception {
                byte[] contentBytes = content.getBytes("UTF-8");
 
                URLConnection connection = url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                ((HttpURLConnection) connection).setRequestMethod("POST");
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Content-Length",
                                Integer.toString(contentBytes.length));
 
                OutputStream requestStream = connection.getOutputStream();
 
                requestStream.write(contentBytes, 0, contentBytes.length);
                requestStream.close();
 
                String response = "";
                BufferedReader responseStream;
                if (((HttpURLConnection) connection).getResponseCode() == 200) {
                        responseStream = new BufferedReader(new InputStreamReader(
                                        connection.getInputStream(), "UTF-8"));
                } else {
                        responseStream = new BufferedReader(new InputStreamReader(
                                        ((HttpURLConnection) connection).getErrorStream(), "UTF-8"));
                }
 
                response = responseStream.readLine();
                responseStream.close();
               
                if (((HttpURLConnection) connection).getResponseCode() != 200
                                && ((HttpURLConnection) connection).getResponseCode() != 403) {
                        System.out.println(((HttpURLConnection) connection)
                                        .getResponseCode() + "");
                        httpreturn = ((HttpURLConnection) connection)
                                .getResponseCode();
                        // Failed to login (Invalid Credentials or whatever)
                }
                if (((HttpURLConnection) connection).getResponseCode() == 403) {
                        System.out.println(((HttpURLConnection) connection)
                                        .getResponseCode() + "");
                        httpreturn = ((HttpURLConnection) connection)
                                .getResponseCode();
                }if (((HttpURLConnection) connection).getResponseCode() == 200){
                	 httpreturn = ((HttpURLConnection) connection)
                             .getResponseCode();
                }
//10069c5b38f543f789124cccc8cb8e5d
                return response;
        }
 
}