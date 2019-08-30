package com.kse.slp.modules.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class APICaller {

	public static void callAPI(String jsonFilename) {
		try {
			String json = "";
			Scanner inJSON = new Scanner(new File(jsonFilename));
			while(inJSON.hasNext()){
				json += inJSON.nextLine();
			}
			inJSON.close();
			
			HttpClient httpClient = new DefaultHttpClient();
			//HttpPost request = new HttpPost(
			//		"http://103.18.4.32:8080/ezRoutingAPI/compute-route");
			 HttpPost request = new
			 HttpPost("http://172.16.20.67:9898/ezRoutingAPI/compute-route");

			StringEntity params = new StringEntity(json);

			request.addHeader("content-type", "application/json");
			request.addHeader("Accept", "application/json");
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);

			InputStream in = response.getEntity().getContent();

			BufferedReader buf = new BufferedReader(new InputStreamReader(in));
			String str = buf.readLine();
			System.out.println(str);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		APICaller.callAPI("pickup-delivery-input.json");
	}

}
