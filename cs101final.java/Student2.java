/**
 * @(#)Student.java
 *
 *
 * @author 
 * @version 1.00 2021/3/23
 */

import java.util.Calendar;

public class Student2 {
	
	private String stuName;
	private String stuSurname;
	private int startYear;
	private int stuId;
	private double tuition;
	
	//default constructor
	public Student2(){
		
	}
	//overloaded constructor
	public Student2( String name, String lastname ){
		stuName = name;
		stuSurname = lastname;
		stuId = -99;
		startYear = getYear();
	}
	//overloaded constructor
    public Student2( String name, String lastname, int id, int year, double fee ) {
    	stuName = name;
    	stuSurname = lastname;
    	startYear = year;
    	stuId = id;    	
    	tuition = fee;
    }
    //support method - not meant to be invoked from outside the class
	private int getYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	//service method
	public double calculateTuition(){
		if(startYear < getYear() ){
			return tuition * 0.85;
		}
		return tuition;
		
	}
	//overloaded service method
	public double calculateTuition( double discount ){
		if(startYear < getYear() ){
			return tuition * (1 - discount);
		}
		return tuition;
	}
	public String fullName(){
		return stuSurname + "," + stuName;
	}
    public String toString(){
    	return fullName() + "(" + startYear +") " + "Tuition: " + calculateTuition(); 
    }
    
}