package com.zafcoding.android.watchcraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class UrlReader {

	public ArrayList<String> getFromUrl(URL url) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		ArrayList<String> returnarray = new ArrayList<String>();
		String line;
		while((line = br.readLine()) != null){
			returnarray.add(line);
		}
		return returnarray;
	}
	
}
