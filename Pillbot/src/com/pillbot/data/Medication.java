package com.pillbot.data;

import java.text.ParseException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.icu.text.SimpleDateFormat;
import android.icu.util.GregorianCalendar;

public class Medication {

	private String name;
	private String dosage;
	private GregorianCalendar startTime;
	private int intervalTaken;
	private IntervalUnit intervalTakenUnit;
	private boolean isAsNeeded;

	public Medication(String name, String dosage, GregorianCalendar startTime,
			int intervalTaken, IntervalUnit intervalTakenUnit,
			boolean isAsNeeded) {
		this.name = name;
		this.dosage = dosage;
		this.startTime = startTime;
		this.intervalTaken = intervalTaken;
		this.intervalTakenUnit = intervalTakenUnit;
		this.isAsNeeded = isAsNeeded;
	}

	@SuppressWarnings("deprecation")
	public Medication(String jsonStr) throws JSONException, ParseException {
		JSONObject jsonObj = new JSONObject(jsonStr);

		this.name = jsonObj.getString("name");
		this.dosage = jsonObj.getString("dosage");
		this.intervalTakenUnit = IntervalUnit.getString(jsonObj.getString("intervalTakenUnit"));
		this.isAsNeeded = jsonObj.getBoolean("isAsNeeded");

		// Parse date
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		Date dateObj = dateFormat.parse(jsonObj.getString("startTime"));
		this.startTime = new GregorianCalendar(dateObj.getYear(),
				dateObj.getMonth(), dateObj.getDay(), dateObj.getHours(),
				dateObj.getMinutes(), dateObj.getSeconds());
	}

	public String toJSON() throws JSONException {
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("name", name);
		jsonObj.put("dosage", dosage);
		jsonObj.put("intervalTaken", intervalTaken);
		jsonObj.put("intervalTakenUnit", intervalTakenUnit.toString());
		jsonObj.put("isAsNeeded", isAsNeeded);

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		jsonObj.put("startTime", dateFormat.format(startTime));

		return jsonObj.toString();
	}

	public IntervalUnit getIntervalTakenUnit() {
		return intervalTakenUnit;
	}

	public void setIntervalTakenUnit(IntervalUnit intervalTakenUnit) {
		this.intervalTakenUnit = intervalTakenUnit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public GregorianCalendar getStartTime() {
		return startTime;
	}

	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}

	public int getIntervalTaken() {
		return intervalTaken;
	}

	public void setIntervalTaken(int intervalTaken) {
		this.intervalTaken = intervalTaken;
	}

	public boolean isAsNeeded() {
		return isAsNeeded;
	}

	public void setAsNeeded(boolean isAsNeeded) {
		this.isAsNeeded = isAsNeeded;
	}

}
