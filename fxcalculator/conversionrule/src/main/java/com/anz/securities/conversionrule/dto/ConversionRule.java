package com.anz.securities.conversionrule.dto;

import java.util.Comparator;
import java.util.Objects;

public class ConversionRule implements Comparable<ConversionRule>, Comparator<ConversionRule> {
	private String currency;
	private String linkedTo;
	private String conversionType;

	public ConversionRule() {

	}

	public ConversionRule(String currency) {
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public String getConversionType() {
		return conversionType;
	}

	public void setConversionType(String conversionType) {
		this.conversionType = conversionType;
	}

	public int compareTo(ConversionRule o) {
		return this.currency.compareTo(o.getCurrency());
	}

	public int compare(ConversionRule o1, ConversionRule o2) {

		return o1.getCurrency().compareToIgnoreCase(o2.getCurrency());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		ConversionRule temp = (ConversionRule) obj;

		return Objects.equals(currency, temp.getCurrency());
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency);
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Curreny - ");
		strBuilder.append(getCurrency());
		strBuilder.append("- Linked To- ");
		strBuilder.append(getLinkedTo());
		strBuilder.append("- Conversion Type- ");
		strBuilder.append(getConversionType());
		return strBuilder.toString();
	}
}