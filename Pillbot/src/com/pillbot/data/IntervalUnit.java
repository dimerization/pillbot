package com.pillbot.data;

public enum IntervalUnit {
	MINUTES("minutes"), HOURS("hours"), DAYS("days"), WEEKS("weeks");

	private final String fieldValue;

	private IntervalUnit(String value) {
		fieldValue = value;
	}

	public static IntervalUnit getString(String str)
			throws IllegalArgumentException {
		switch (str) {
		case "minutes":
			return MINUTES;
		case "hours":
			return HOURS;
		case "days":
			return DAYS;
		case "weeks":
			return WEEKS;
		default:
			throw new IllegalArgumentException(
					"Invalid interval unit entered: " + str);
		}
	}

	public String toString() {
		return fieldValue;
	}
}
