package kr.hs.dgsw.service;

import java.util.Calendar;

public class People {
	
	private String name;
	private int birthYear;
	
	public People(String name, int birthYear) {
		this.name = name;
		this.birthYear = birthYear;
	}
	
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBirthYear() {
		return birthYear;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		
		return thisYear - birthYear + 1;
	}
}
