package test;

import org.javatuples.Quintet;

public class Main {
	  public static void main(String[] args) 
	    { 
	        Quintet<Integer, String, String, Double, Boolean> quintet 
	            = Quintet.with(Integer.valueOf(1), 
	                           "GeeksforGeeks", 
	                           "A computer portal", 
	                           Double.valueOf(20.18), 
	                           true); 
	  
	        for (Object item : quintet) 
	            System.out.println(item); 
	    } 
	} 

