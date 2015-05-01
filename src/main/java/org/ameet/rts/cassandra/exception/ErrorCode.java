package org.ameet.rts.cassandra.exception;

public interface ErrorCode {
	int getNumber();
	String getDescription();
}