package org.ameet.nosql.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
/**
 * generic exception class which wraps other exceptions into this
 * and uses enums specific to various areas.
 *
 */
public class RTSException extends RuntimeException {
	private static final long serialVersionUID = 2577405312882888246L;

	public static RTSException wrap(Throwable exception, ErrorCode errorCode) {
        if (exception instanceof RTSException) {
            RTSException se = (RTSException)exception;
        	if (errorCode != null && errorCode != se.getErrorCode()) {
                return new RTSException(exception.getMessage(), exception, errorCode);
			}
			return se;
        } else {
            return new RTSException(exception.getMessage(), exception, errorCode);
        }
    }
    
    public static RTSException wrap(Throwable exception) {
    	return wrap(exception, null);
    }
    
    private ErrorCode errorCode;
    private final Map<String,Object> properties = new TreeMap<String,Object>();
    
    public RTSException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public RTSException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public RTSException(Throwable cause, ErrorCode errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public RTSException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
        return errorCode;
    }
	
	public RTSException setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }
	
	public Map<String, Object> getProperties() {
		return properties;
	}
	
    @SuppressWarnings("unchecked")
	public <T> T get(String name) {
        return (T)properties.get(name);
    }
	
    public RTSException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }
    
    public void printStackTrace(PrintStream s) {
        synchronized (s) {
            printStackTrace(new PrintWriter(s));
        }
    }

    public void printStackTrace(PrintWriter s) { 
        synchronized (s) {
            s.println(this);
            s.println("\t-------------------------------");
            if (errorCode != null) {
	        	s.println("\t" + errorCode + ":   " + errorCode.getClass().getSimpleName()); 
			}
            for (String key : properties.keySet()) {
            	s.println("\t" + key + "=[" + properties.get(key) + "]"); 
            }
            s.println("\t-------------------------------");
            StackTraceElement[] trace = getStackTrace();
            for (int i=0; i < trace.length; i++) {
                s.println("\tat " + trace[i]);
            }

            Throwable ourCause = getCause();
            if (ourCause != null) {
                ourCause.printStackTrace(s);
            }
            s.flush();
        }
    }    
}