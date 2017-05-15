package com.anz.securities.entities.apii;

/**
 * 
 * @author ANAGOV
 *
 */
public interface ConversionRule extends Comparable<ConversionRule> {

	/**
	 * 
	 * @return
	 */
	public String getCurrencyName();
	
	/**
	 * 
	 * @return
	 */
	public String getPointer();
}
