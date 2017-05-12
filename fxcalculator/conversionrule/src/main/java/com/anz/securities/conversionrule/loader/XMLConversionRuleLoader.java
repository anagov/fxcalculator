package com.anz.securities.conversionrule.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.anz.securities.common.Constants;
import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.common.util.CommonUtil;
import com.anz.securities.conversionrule.api.ConversionRuleLoader;
import com.anz.securities.conversionrule.dto.ConversionRule;
import com.anz.securities.conversionrule.dto.ConversionRules;

/**
 * 
 * @author xanakat
 *
 */
public class XMLConversionRuleLoader implements ConversionRuleLoader {

	private static Logger logger = LoggerFactory.getLogger(XMLConversionRuleLoader.class);

	/**
	 * 
	 */
	public ConversionRules loadConversionRules() throws DataLoaderException {
		ConversionRules convRules = new ConversionRules();
		Map<String, List<ConversionRule>> convMap = loadSupportedCurrencies(Constants.CONVERSION_RULE_RESOURCE_FILE);
		convRules.setRules(convMap);
		return convRules;
	}

	private Map<String, List<ConversionRule>> loadSupportedCurrencies(final String resource)
			throws DataLoaderException {
		try {
			Map<String, List<ConversionRule>> conversionMap = new HashMap<String, List<ConversionRule>>();

			Document doc = CommonUtil.getXMLDocument(resource);
			NodeList nList = doc.getElementsByTagName(Constants.SOURCE_CURRENCY);
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String currencyId = eElement.getAttribute(Constants.ID);

				NodeList childNodes = eElement.getElementsByTagName(Constants.DESTINATION_CURRENCY);
				List<ConversionRule> listConversionRules = new ArrayList<ConversionRule>();
				for (int temp1 = 0; temp1 < childNodes.getLength(); temp1++) {
					Node nNode1 = childNodes.item(temp1);
					Element eElement1 = (Element) nNode1;
					String destCurrencyId = eElement1.getAttribute(Constants.ID);
					String linkedTo = eElement1.getAttribute(Constants.LINKED_TO);
					ConversionRule converRule = new ConversionRule();

					converRule.setCurrency(destCurrencyId);
					converRule.setLinkedTo(linkedTo);

					listConversionRules.add(converRule);
				}
				conversionMap.put(currencyId, listConversionRules);
			}
			return conversionMap;

		} catch (Exception ex) {
			logger.error("Generic Exception loading Currencies" + ex);
			throw new DataLoaderException("Generic Exception  Loading Currencies" + ex.getMessage());
		}
	}
}
