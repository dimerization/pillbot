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
	private int intervalTaken;
	private IntervalUnit intervalTakenUnit;
	private boolean isAsNeeded;

	public Medication(String name, String dosage, int intervalTaken,
			IntervalUnit intervalTakenUnit, boolean isAsNeeded) {
		this.name = name;
		this.dosage = dosage;
		this.intervalTaken = intervalTaken;
		this.intervalTakenUnit = intervalTakenUnit;
		this.isAsNeeded = isAsNeeded;
	}

	public Medication(String jsonStr) throws JSONException, ParseException {
		JSONObject jsonObj = new JSONObject(jsonStr);

		this.name = jsonObj.getString("name");
		this.dosage = jsonObj.getString("dosage");
		this.intervalTakenUnit = IntervalUnit.getString(jsonObj.getString("intervalTakenUnit"));
		this.isAsNeeded = jsonObj.getBoolean("isAsNeeded");
	}
	
	public String toJSON() throws JSONException {
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("name", name);
		jsonObj.put("dosage", dosage);
		jsonObj.put("intervalTaken", intervalTaken);
		jsonObj.put("intervalTakenUnit", intervalTakenUnit.toString());
		jsonObj.put("isAsNeeded", isAsNeeded);

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
