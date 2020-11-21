package entity;

import java.util.ArrayList;

import enumeration.SessionType;

public class Timeslot {
	
	private SessionType sessionType; //e.g. LAB, TUT, LEC
	private String venue;
	private ArrayList<Integer> weeks;
	private int day;
	private int startTime;
	private int endTime;


	public Timeslot(SessionType sessionType, String venue, ArrayList<Integer> weeks, int day, int startTime,
			int endTime) {
		this.sessionType = sessionType;
		this.venue = venue;
		this.weeks = weeks;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	

}
