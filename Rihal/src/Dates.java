//This class is not finished & not tested 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {
	protected int day;
	protected int month;
	protected int year;
	
	public Dates() {
		
	}
	
	public Dates(String d, String m, String y) {
		this.month = Integer.parseInt(m);
		this.day = Integer.parseInt(d);
		this.year = Integer.parseInt(y);
	}
	
	public Date Date(String s) throws ParseException {
		 
//		ArrayList<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();
//		knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
//		knownPatterns.add(new SimpleDateFormat("MM-dd-yyyy"));
//		knownPatterns.add(new SimpleDateFormat("yyyy/MM/dd"));
//		knownPatterns.add(new SimpleDateFormat("MM/ddyyyy"));
//		knownPatterns.add(new SimpleDateFormat("dd-MM-yyy"));
//		knownPatterns.add(new SimpleDateFormat("dd/MM/yyy"));
		
		SimpleDateFormat newFormat = new SimpleDateFormat("MM/DD/YYYY");

		Date d2 = newFormat.parse(s);
		return d2;
		
		//return newFormat.format(inFormat.parse(s));

	}
	
	
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	
//	public void setDay(String n) {
//		day = Integer.parseInt(n);
//	}
//	
//	public void setMonth(String j) {
//		month = Integer.parseInt(j);
//	}
//	
//	public void setYear(String a) {
//		year = Integer.parseInt(a);
//	}
	
}
