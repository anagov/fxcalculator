package com.anz.securities.currency.loader;

import java.io.InputStream;
import java.util.HashMap;
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
import com.anz.securities.currency.api.CurrencyLoader;
import com.anz.securities.currency.dto.SupportedCurrencies;

public class XMLCurrencyLoader implements CurrencyLoader {
	private static final String RESOURCE_FILE_NAME = "SupportedCurrencies.xml";
	private static Logger logger = LoggerFactory.getLogger(XMLCurrencyLoader.class);
	private Map<String, String> supportedCurrencies;

	public SupportedCurrencies loadSupportedCurrencies() throws CurrencyLoaderException {
		SupportedCurrencies supCurrencies = null;
		try {
			loadSupportedCurrencies(RESOURCE_FILE_NAME);
			supCurrencies = new SupportedCurrencies(supportedCurrencies);
		} catch (Exception ex) {
			logger.error("Error loading Currencies" + ex);
			throw new CurrencyLoaderException("Error While Loading currencies");
		}
		return supCurrencies;
	}

	private void loadSupportedCurrencies(String fileName) throws CurrencyLoaderException {
		try {

			InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("currency");

			supportedCurrencies = new HashMap<String, String>();
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String currencyId = eElement.getAttribute("id");
					String decimalDisplay = eElement.getElementsByTagName("decimal").item(0).getTextContent();
					supportedCurrencies.put(currencyId, decimalDisplay);
				}
			}
		} catch (Exception ex) {
			logger.error("Error loading Currencies" + ex);
			throw new CurrencyLoaderException("Error Loading Currencies");
		}
	}

}
