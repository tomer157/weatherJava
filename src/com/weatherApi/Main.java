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
	public static List<String> list1 = new ArrayList<>();
	public static List<String> list2 = new ArrayList<>();
	public static List<String> list3 = new ArrayList<>();
	public static List<String> list4 = new ArrayList<>();
	public static List<String> list5 = new ArrayList<>();

	public static JSONObject jo = null; 

	////////////////////// min_temp ,max //////cities with rain array
//	public static Triplet<String, String, List<String>> tripletOne = new Triplet<String, String, List<String>>("", "",
//			list);
	static TripletWeather tw1 = new TripletWeather("", "", list1);
	static TripletWeather tw2 = new TripletWeather("", "", list2);
	static TripletWeather tw3 = new TripletWeather("", "", list3);
	static TripletWeather tw4 = new TripletWeather("", "", list4);
	static TripletWeather tw5 = new TripletWeather("", "", list5);
	
	public static List<TripletWeather> tripleList = new ArrayList<TripletWeather>();
	private static double firstMin_temp = 0;
	private static double firstMax_temp = 0;

	public static String city = "New York";
	public static String cityName = null;
//	public static StringBuffer responseContent = new StringBuffer();

	public static void main(String[] args) {
		BufferedReader reader;
		String line;
		String[] cities = { "New York" ,"Berlin","Jerusalem","Dubai","Lisbon","Paris","Seoul","Singapore","Athens","Oslo"};

		try {
			for (int k = 0; k < cities.length; k++) {
				jo = null;
				StringBuffer responseContent = new StringBuffer();
			
				URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + cities[k]
						+ "&cnt=5&appid=a3f20118eead94533603b9665190dd88");
				connection = (HttpURLConnection) url.openConnection();
				System.out.println("cuty " + cities[k]);
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
					// reader.close();
				} else {
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while ((line = reader.readLine()) != null) {
						responseContent.append(line);
					}

					// reader.close();

				}

				System.out.println(responseContent.toString());

				parse(responseContent.toString());
			

				System.out.println("tw1 " + tw1.getCountryMax() + tw1.getCountryMin() + "" + tw1.getList() + "\n");
				System.out.println("tw5 " + tw5.getCountryMax() + tw5.getCountryMin() + "" + tw5.getList() + "\n");
				System.out.println("tw2 " + tw2.getCountryMax() + tw2.getCountryMin() + "" + tw2.getList() + "\n");
				
			
				
				
				
//			
				

			}
			
			System.out.println(tw5.countryMax + " "+tw5.list);
			tripleList.add(tw1);
			tripleList.add(tw2);
			tripleList.add(tw3);
			tripleList.add(tw4);
			tripleList.add(tw5);
			System.out.println((tripleList.size()));
			WriteCsv.write(tripleList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

	}

	public static String parse(String responseBody) throws InterruptedException {

		try {
			System.out.println("res " + responseBody);

			System.out.println("jo1 " + jo);
			jo = new JSONObject(responseBody);

//	  System.out.println("jo "+jo);

//	    System.out.println("jo "+jo);
			JSONObject cityOBJ = jo.getJSONObject("city");
			System.out.println("cityobj " + cityOBJ);
			cityName = cityOBJ.get("name").toString();
			System.out.println(cityName);

			JSONArray listArray = jo.getJSONArray("list");
			for (int i = 0; i < listArray.length(); i++) {
				JSONObject listObject = listArray.getJSONObject(i);
				JSONObject main = listObject.getJSONObject("main");

				JSONObject weather = listObject.getJSONArray("weather").getJSONObject(0);
				String weatherMain = weather.getString("main");
				String weatherDesc = weather.getString("description");
				JSONObject rain=  new JSONObject();
				if(listObject.has("rain")){
					System.out.println("rain exiasts");
				 rain= listObject.getJSONObject("rain");
				}
			
				System.out.println("city name ");
				System.out.println(main.get("temp_min") + "index " + i);
				System.out.println(main.get("temp_max") + "index of max " + i);

				
				
				
				if(cityName.equals("New York")) {
					firstMax_temp = main.getDouble("temp_max");
					firstMin_temp = main.getDouble("temp_min");
					tw1.setCountryMin(cityName);

					tw1.setCountryMax(cityName);

				
					
					tw2.setCountryMin(cityName);

					tw2.setCountryMax(cityName);

//					list2.add(cityName);
					
					tw3.setCountryMin(cityName);

					tw3.setCountryMax(cityName);

//					list3.add(cityName);
					
					tw4.setCountryMin(cityName);

					tw4.setCountryMax(cityName);

//					list4.add(cityName);
					
					
					tw5.setCountryMin(cityName);

					tw5.setCountryMax(cityName);

//					list5.add(cityName);
					
				}
				
				
				
				
				
				
				switch (i) {
				case 0:
					if(firstMin_temp >main.getDouble("temp_min")) {
						tw1.setCountryMin(cityName);

					

				
					}
					
					if(firstMax_temp <main.getDouble("temp_max")) {
						
						tw1.setCountryMax(cityName);
					}
					if(!rain.isEmpty()) {
						list1.add(cityName);
						tw1.setList(list1);
					}
					
					
					
					break;

				case 1:

					if(firstMin_temp >main.getDouble("temp_min")) {
						tw2.setCountryMin(cityName);

					

				
					}
					
					if(firstMax_temp <main.getDouble("temp_max")) {
						
						tw2.setCountryMax(cityName);
					}
					if(!rain.isEmpty()) {
						list2.add(cityName);
						tw2.setList(list2);
					}
					

					break;

				case 2:
					if(firstMin_temp >main.getDouble("temp_min")) {
						tw3.setCountryMin(cityName);

					

				
					}
					
					if(firstMax_temp <main.getDouble("temp_max")) {
						
						tw3.setCountryMax(cityName);
					}
					if(!rain.isEmpty()) {
						list3.add(cityName);
						tw3.setList(list3);
					}
					
					break;

				case 3:
					if(firstMin_temp >main.getDouble("temp_min")) {
						tw4.setCountryMin(cityName);

					

				
					}
					
					if(firstMax_temp <main.getDouble("temp_max")) {
						
						tw4.setCountryMax(cityName);
					}
					if(!rain.isEmpty()) {
						list4.add(cityName);
						tw4.setList(list4);
					}
					
					break;

				case 4:
					if(firstMin_temp >main.getDouble("temp_min")) {
						tw5.setCountryMin(cityName);

					

				
					}
					
					if(firstMax_temp <main.getDouble("temp_max")) {
						
						tw5.setCountryMax(cityName);
					}
					if(!rain.isEmpty()) {
						list5.add(cityName);
						tw5.setList(list5);
					}
					
					break;

				}

			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return responseBody;
	}
}
