package com.anz.securities.conversionrate.dto;

import java.util.Comparator;
import java.util.Objects;

/**
 * 
 * @author xanakat
 *
 */
public class ConversionRate implements Comparable<ConversionRate> {

	private String sourceCurrency;
	private String destinationCurrency;
	private String rate;
	private String conversionType;

	public ConversionRate() {
	}

	public ConversionRate(final String src, final String dest) {
		this.sourceCurrency = src;
		this.destinationCurrency = dest;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(final String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getDestinationCurrency() {
		return destinationCurrency;
	}

	public void setDestinationCurrency(final String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}

	public String getConversionRate() {
		return rate;
	}

	public void setConversionRate(final String conversionRate) {
		this.rate = conversionRate;
	}

	public String getConversionType() {
		return conversionType;
	}

	public void setConversionType(final String conversionType) {
		this.conversionType = conversionType;
	}

	/**
	 * 
	 */
	public int compareTo(final ConversionRate o) {
		int temp = this.sourceCurrency.compareToIgnoreCase(o.getSourceCurrency());
		if (temp != 0)
			return temp;
		return this.destinationCurrency.compareToIgnoreCase(o.getDestinationCurrency());
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

		ConversionRate temp = (ConversionRate) obj;

		return this.sourceCurrency.equalsIgnoreCase(temp.getSourceCurrency())
				&& this.destinationCurrency.equalsIgnoreCase(temp.getDestinationCurrency());
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.sourceCurrency, this.destinationCurrency);
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Source Currency - ");
		builder.append(getSourceCurrency());
		builder.append(" == Destination Currency - ");
		builder.append(getDestinationCurrency());
		builder.append(" == Converstion Rate - ");
		builder.append(getConversionRate());
		builder.append(" == Converstion Type - ");
		builder.append(getConversionType());

		return builder.toString();
	}
}
