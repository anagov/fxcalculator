package com.anz.securities.conversionrule.dto;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConversionRules {

	private Map<String, List<ConversionRule>> rules;

	public ConversionRules() {
		rules = new HashMap<String, List<ConversionRule>>();
	}

	public Map<String, List<ConversionRule>> getRules() {
		return rules;
	}

	public void setRules(Map<String, List<ConversionRule>> rules) {
		this.rules = rules;
	}

	public void addRule(String currency, List<ConversionRule> rule) {
		rules.put(currency, rule);
	}
	
	public List<ConversionRule> getRule( String key ) {
		return rules.get(key);
	}

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

