package com.anz.securities.currency.loader;

import java.io.IOException;
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
import org.xml.sax.SAXException;

import com.anz.securities.common.Constants;
import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.currency.api.CurrencyLoader;
import com.anz.securities.currency.dto.SupportedCurrencies;

/**
 * 
 * @author xanakat
 *
 */
public class XMLCurrencyLoader implements CurrencyLoader {

	private static Logger logger = LoggerFactory.getLogger(XMLCurrencyLoader.class);

	/**
	 * 
	 */
	public SupportedCurrencies loadSupportedCurrencies() throws DataLoaderException {
		SupportedCurrencies supCurrencies = null;

		Map<String, String> supportedCurrencies = loadSupportedCurrencies(Constants.SUPPORTED_CURRENCIES_RESOURCE_FILE);
		supCurrencies = new SupportedCurrencies(supportedCurrencies);
		return supCurrencies;
	}

	private Map<String, String> loadSupportedCurrencies(final String fileName) throws DataLoaderException {
		try {
			Map<String, String> supportedCurrencies;
			InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			if (input == null) {
				throw new DataLoaderException("NULL Input stream while loading currencies");
			}
			Document doc = dBuilder.parse(input);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName(Constants.CURRENCY);

			supportedCurrencies = new HashMap<String, String>();
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String currencyId = eElement.getAttribute(Constants.ID);
					String decimalDisplay = eElement.getAttribute(Constants.SUPPORTED_DECIMAL);
					supportedCurrencies.put(currencyId, decimalDisplay);
				}
			}
			return supportedCurrencies;
		} catch (SAXException exParse) {
			logger.error("Error parsing supported currencies XML" + exParse);
			throw new DataLoaderException("XMLParse exception" + exParse.getMessage());
		} catch (IOException exIO) {
			logger.error("IO Exception loading supported currencies" + exIO);
			throw new DataLoaderException("IO exception" + exIO.getMessage());
		} catch (Exception ex) {
			logger.error("Generic Exception loading Currencies" + ex);
			throw new DataLoaderException("Generic Exception  Loading Currencies" + ex.getMessage());
		}
	}

}
