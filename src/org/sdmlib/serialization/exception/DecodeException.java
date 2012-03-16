/*
 * @author Stefan Lindel
 */
package org.sdmlib.serialization.exception;

/**
 * The Class DecodeException.
 */
public class DecodeException extends Exception{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The length. */
	private int length;
	
	/** The reason. */
	private String reason;

	/**
	 * Instantiates a new decode exception.
	 *
	 * @param length the length
	 */
	public DecodeException(int length){
		this.length=length;
	}
	
	/**
	 * Instantiates a new decode exception.
	 *
	 * @param length the length
	 * @param reason the reason
	 */
	public DecodeException(int length, String reason){
		this.length=length;
		this.reason=reason;
	}
	
	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

}
