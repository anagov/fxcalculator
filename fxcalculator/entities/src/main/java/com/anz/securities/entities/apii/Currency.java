package com.anz.securities.entities.apii;

import java.util.List;

/**
 * 
 * @author ANAGOV
 *
 */
public interface Currency {
	
	/**
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * 
	 * @return
	 */
	public List<ConversionRule> getRules();
	
	/**
	 * 
	 * @return
	 */
	public int getDecimalSupport();

}
