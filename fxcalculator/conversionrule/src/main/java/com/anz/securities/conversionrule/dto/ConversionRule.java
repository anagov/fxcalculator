package com.anz.securities.conversionrule.dto;

import java.util.Objects;

/**
 * 
 * @author xanakat
 *
 */
public class ConversionRule implements Comparable<ConversionRule> {
	private String currency;
	private String linkedTo;

	public ConversionRule() {

	}

	/**
	 * 
	 * @param currency
	 */
	public ConversionRule(final String currency) {
		this.currency = currency;
	}

	/**
	 * 
	 * @return
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * @param currency
	 */
	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	/**
	 * 
	 * @return
	 */
	public String getLinkedTo() {
		return linkedTo;
	}

	/**
	 * 
	 * @param linkedTo
	 */
	public void setLinkedTo(final String linkedTo) {
		this.linkedTo = linkedTo;
	}

	/**
	 * 
	 */
	public int compareTo(final ConversionRule o) {
		return this.currency.compareTo(o.getCurrency());
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		ConversionRule temp = (ConversionRule) obj;

		return Objects.equals(currency, temp.getCurrency());
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(currency);
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Curreny - ");
		strBuilder.append(getCurrency());
		strBuilder.append("- Linked To- ");
		strBuilder.append(getLinkedTo());
		return strBuilder.toString();
	}
}