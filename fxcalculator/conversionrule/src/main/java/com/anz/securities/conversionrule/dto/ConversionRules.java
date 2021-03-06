package com.anz.securities.conversionrule.dto;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xanakat
 *
 */
public class ConversionRules {

	private Map<String, List<ConversionRule>> rules;

	public ConversionRules() {
		rules = new HashMap<String, List<ConversionRule>>();
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, List<ConversionRule>> getRules() {
		return rules;
	}

	/**
	 * 
	 * @param rules
	 */
	public void setRules(final Map<String, List<ConversionRule>> rules) {
		this.rules = rules;
	}

	/**
	 * 
	 * @param currency
	 * @param rule
	 */
	public void addRule(final String currency, final List<ConversionRule> rule) {
		rules.put(currency, rule);
	}
	
	public List<ConversionRule> getRule( final String key ) {
		return rules.get(key);
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		if (rules != null) {
			for (Map.Entry<String, List<ConversionRule>> entry : rules.entrySet()) {
				strBuilder.append("Curreny Name - ");
				strBuilder.append(entry.getKey());
				List<ConversionRule> tempList =  entry.getValue(); 
				for ( ConversionRule cRule : tempList ) {
					strBuilder.append("Conversion Rule- ");
					strBuilder.append(cRule.toString());
				}
				strBuilder.append("\n");
				
			}
		}
		return strBuilder.toString();	
	}

}

