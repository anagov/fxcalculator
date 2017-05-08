package com.anz.securities.conversionrate.dto;

import java.util.Comparator;
import java.util.Objects;

public class ConversionRate implements Comparable<ConversionRate>, Comparator<ConversionRate> {

	private String sourceCurrency;
	private String destinationCurrency;
	private String conversionRate;

	public ConversionRate() {
	}

	public ConversionRate(String src, String dest) {
		this.sourceCurrency = src;
		this.destinationCurrency = dest;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getDestinationCurrency() {
		return destinationCurrency;
	}

	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}

	public String getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
	}

	public int compare(ConversionRate o1, ConversionRate o2) {
		int temp = o1.getSourceCurrency().compareToIgnoreCase(o2.getSourceCurrency());
		if (temp != 0)
			return temp;
		return o1.getDestinationCurrency().compareToIgnoreCase(o2.getDestinationCurrency());
	}

	public int compareTo(ConversionRate o) {
		int temp = this.sourceCurrency.compareToIgnoreCase(o.getSourceCurrency());
		if (temp != 0)
			return temp;
		return this.destinationCurrency.compareToIgnoreCase(o.getDestinationCurrency());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		ConversionRate temp = (ConversionRate) obj;

		return Objects.equals(this.sourceCurrency, temp.getSourceCurrency())
				&& Objects.equals(this.destinationCurrency, temp.getDestinationCurrency());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.sourceCurrency) * Objects.hash(this.destinationCurrency);
	}

}
