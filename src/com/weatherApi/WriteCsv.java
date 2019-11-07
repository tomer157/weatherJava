package com.weatherApi;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import test.TripletWeather;

public class WriteCsv {
	
	public static  void write(List<TripletWeather> tple) throws IOException {
		try(FileWriter writer = new FileWriter("C:\\Users\\tomer\\Desktop\\test.csv")) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("mintemp");
			sb.append(",");
			sb.append("maxtemp");
			sb.append(",");
			sb.append("rainList");
			sb.append("\n");
			System.out.println(tple);
			for(int i =0;i<tple.size();i++) {
				int index = i;
				System.out.println("index "+i);
				sb.append(tple.get(i).countryMin);
				sb.append(",");
				sb.append(tple.get(i).countryMax);
				sb.append(",");
				sb.append(tple.get(i).list);
				sb.append("\n");
				if(index == 14) {
					break;
				}
			
				
				
				
				
			}
			 writer.write(sb.toString());

		      System.out.println("done!");
			 writer.close();
			
			
			
			
			
			
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		}
	}
}
