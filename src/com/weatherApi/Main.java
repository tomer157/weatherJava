package com.weatherApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Quintet;
import org.javatuples.Triplet;
import org.json.JSONArray;
import org.json.JSONObject;

import test.TripletWeather;

import java.net.*;

public class Main {
	public static HttpURLConnection connection;
	public static List<String> list = new ArrayList<>();
	public static JSONObject jo = null;
	
	////////////////////// min_temp ,max //////cities with rain array
	public static Triplet<String, String, List<String>> tripletOne = new Triplet<String, String, List<String>>("", "",
			list);
	static TripletWeather tw1 = new TripletWeather("", "", list);
	static TripletWeather tw2 = new TripletWeather("", "", list);
	static TripletWeather tw3 = new TripletWeather("", "", list);
	static TripletWeather tw4 = new TripletWeather("", "", list);
	static TripletWeather tw5 = new TripletWeather("", "", list);
	

	private static double firstMin_temp = 293.66;
	private static double firstMax_temp = 292.12;
	
	
	public static String cityName =null;
	public static StringBuffer responseContent = new StringBuffer();
	
	
	public static void main(String[] args) {
		BufferedReader reader;
		String line;
		String[] cities = {"New York","Berlin"};
		try {
			for (String city : cities) {
				jo = null;
				 
				URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + city
						+ "&cnt=5&appid=a3f20118eead94533603b9665190dd88");
				connection = (HttpURLConnection) url.openConnection();
				System.out.println("cuty " + city);
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);

				int status = connection.getResponseCode();
				System.out.println(status);

				if (status > 299) {
					reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
					while ((line = reader.readLine()) != null) {
						responseContent.append(line);

					}
					//reader.close();
				} else {
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while ((line = reader.readLine()) != null) {
						responseContent.append(line);
					}

					//reader.close();

				}

				System.out.println(responseContent.toString());

				parse(responseContent.toString());
				Thread.sleep(1200);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

	}

	public static String parse(String responseBody) throws InterruptedException {
	
		
		try{System.out.println("res "+responseBody);
	
		
		System.out.println("jo1 "+jo);
	  jo = new JSONObject(responseBody);
	
	  System.out.println("jo "+jo);
	  
		
		if(!jo.isEmpty()) {
		  jo.remove(jo.keys().next());}

	  
//	    System.out.println("jo "+jo);
//		JSONObject cityOBJ = jo.getJSONObject("city");
//		System.out.println(cityOBJ);
//		cityName = cityOBJ.get("name").toString();
		
//		JSONArray listArray = jo.getJSONArray("list");
//		for (int i = 0; i < listArray.length(); i++) {
//			JSONObject listObject = listArray.getJSONObject(i);
//			JSONObject main = listObject.getJSONObject("main");
//
//			JSONObject weather = listObject.getJSONArray("weather").getJSONObject(0);
//			String weatherMain = weather.getString("main");
//			String weatherDesc = weather.getString("description");
////		JSONObject rain = listObject.getJSONObject("rain");
//		
//			
//			System.out.println("city name ");
//			System.out.println(main.get("temp_min") + "index " + i);
//			System.out.println(main.get("temp_max") + "index of max " + i);
//
//			switch (i) {
//			case 0:
//
//				if (firstMin_temp > main.getDouble("temp_min")) {
//					firstMin_temp = main.getDouble("temp_min");
//					tw1.setCountryMin(cityName);
//				}
//
//				if (firstMax_temp < main.getDouble("temp_max")) {
//					firstMax_temp = main.getDouble("temp_max");
//					tw1.setCountryMax(cityName);
//				}
//				if (listObject.has("rain")) {
//					list.add(cityName);
//				}
//
//				System.out.println("max "+ tw1.getCountryMin());
//				break;
//
//			}
//
//		}
		
		return null;
		}catch(Exception e ) {
			
		}
		return responseBody;
	}
}
