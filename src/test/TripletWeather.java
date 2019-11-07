package test;

import java.util.ArrayList;
import java.util.List;

public class TripletWeather {
	public String countryMin;
	public String countryMax;
	public List<String>list = new ArrayList<String>();
	public TripletWeather(String countryMin, String countryMax, List<String> list) {
		super();
		this.countryMin = countryMin;
		this.countryMax = countryMax;
		this.list = list;
	}
	public String getCountryMin() {
		return countryMin;
	}
	public void setCountryMin(String countryMin) {
		this.countryMin = countryMin;
	}
	public String getCountryMax() {
		return countryMax;
	}
	public void setCountryMax(String countryMax) {
		this.countryMax = countryMax;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
	
	
	
	
	

}
