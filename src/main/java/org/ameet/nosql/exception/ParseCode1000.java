package org.ameet.nosql.exception;


public enum ParseCode1000 implements ErrorCode {

	FILE_TO_STRING_ERROR(1000,"Error reading the file to a string"),
	JSON_MODEL_FIELD_VALUE_GET(1001, "Error getting field value via reflection");
	
	private final int number;
	private final String description;

	private ParseCode1000(int number, String description) {
		this.number = number;
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return number + ": " + description;
	}
}
