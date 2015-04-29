package org.ameet.nosql.exception;


public enum Metadata1100 implements ErrorCode {

	TABLE_NOT_EXIST(1100,"table passed does not exist"),
	ILLEGAL_TABLE_NAME(1101,"table name cannot be null or empty"),
	ILLEGAL_WELL_PK(1102,"primary key passed is null or its components are null");
	private final int number;
	private final String description;

	private Metadata1100(int number, String description) {
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
