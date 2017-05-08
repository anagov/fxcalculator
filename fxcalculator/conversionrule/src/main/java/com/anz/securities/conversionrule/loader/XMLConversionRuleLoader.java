package com.anz.securities.conversionrule.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.anz.securities.common.exception.CurrencyLoaderException;
import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.conversionrule.api.ConversionRuleLoader;
import com.anz.securities.conversionrule.dto.ConversionRule;
import com.anz.securities.conversionrule.dto.ConversionRules;

public class XMLConversionRuleLoader implements ConversionRuleLoader {
	private static final String RESOURCE_FILE_NAME = "ConversionRules.xml";
	private static Logger logger = LoggerFactory.getLogger(XMLConversionRuleLoader.class);

	public ConversionRules loadConversionRules() throws DataLoaderException {
		ConversionRules convRules = new ConversionRules();
		try {
			Map<String, List<ConversionRule>> convMap = loadSupportedCurrencies(RESOURCE_FILE_NAME);
			convRules.setRules(convMap);
		} catch (Exception ex) {
			logger.error("Error loading Currencies" + ex);
			throw new DataLoaderException("Error While Loading currencies");
		}
		return convRules;
	}

	private Map<String, List<ConversionRule>> loadSupportedCurrencies(String fileName) throws CurrencyLoaderException {
		Map<String, List<ConversionRule>> conversionMap = new HashMap<String, List<ConversionRule>>();
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("sourcecurrency");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String currencyId = eElement.getAttribute("id");
				logger.info("Currency Element" + currencyId);
				NodeList childNodes = eElement.getElementsByTagName("destinationcurrency");
				List<ConversionRule> listConversionRules = new ArrayList<ConversionRule>();
				for (int temp1 = 0; temp1 < childNodes.getLength(); temp1++) {
					Node nNode1 = childNodes.item(temp1);
					Element eElement1 = (Element) nNode1;
					String destCurrencyId = eElement1.getAttribute("id");
					String linkedTo = eElement1.getElementsByTagName("linkedto").item(0).getTextContent();
					String conversiontype = eElement1.getElementsByTagName("conversiontype").item(0).getTextContent();
					ConversionRule converRule = new ConversionRule();

					converRule.setConversionType(conversiontype);
					converRule.setCurrency(destCurrencyId);
					converRule.setLinkedTo(linkedTo);

					listConversionRules.add(converRule);

					logger.info("Destination Currency Element" + destCurrencyId + linkedTo + conversiontype);
				}
				conversionMap.put(currencyId, listConversionRules);
			}
		} catch (Exception ex) {
			logger.error("Error loading Currencies" + ex);
			throw new CurrencyLoaderException("Error Loading Currencies");
		}
		return conversionMap;
	}

}
