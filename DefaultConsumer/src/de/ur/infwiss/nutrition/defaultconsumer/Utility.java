package de.ur.infwiss.nutrition.defaultconsumer;

public class Utility {
	
	public static String extractParam(String name,String url) {
	 String search=name+"=";
	 int i=url.indexOf(search);
	 search=search.substring(i+search.length());
	 i=search.indexOf("&");
	 if (i==-1) {i=search.length();}
	 return search.substring(0,i);
	}

}
