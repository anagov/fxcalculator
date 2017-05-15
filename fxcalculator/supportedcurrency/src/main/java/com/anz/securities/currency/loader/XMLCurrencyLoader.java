package com.anz.securities.currency.loader;

import java.util.HashMap;
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
import com.anz.securities.currency.api.CurrencyLoader;
import com.anz.securities.currency.dto.SupportedCurrencies;

/**
 * Reads data from the XML source
 * 
 * @author Anand Katti
 *
 */
public class XMLCurrencyLoader implements CurrencyLoader {

	private static Logger logger = LoggerFactory.getLogger(XMLCurrencyLoader.class);

	/**
	 * @see CurrencyLoader.loadSupportedCurrencies
	 */
	public SupportedCurrencies loadSupportedCurrencies() throws DataLoaderException {
		SupportedCurrencies supCurrencies;

		Map<String, String> supCurr = loadCurrenciesFromXML();
		supCurrencies = new SupportedCurrencies(supCurr);
		return supCurrencies;
	}

	/**
	 * Loads supported currencies from XML
	 * 
	 * @param resource
	 * @return supportedCurr
	 * @throws DataLoaderException
	 */
	private Map<String, String> loadCurrenciesFromXML() throws DataLoaderException {
		try {
			Document doc = CommonUtil.getXMLDocument(Constants.SUPPORTED_CURRENCIES_RESOURCE_FILE);
			NodeList nList = doc.getElementsByTagName(Constants.CURRENCY);

			Map<String, String> supportedCurr = new HashMap<>();
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String currencyId = eElement.getAttribute(Constants.ID);
					String decimalDisplay = eElement.getAttribute(Constants.SUPPORTED_DECIMAL);
					supportedCurr.put(currencyId, decimalDisplay);
				}
			}
			return supportedCurr;
		} catch (Exception ex) {
			logger.error("Generic Exception loading Currencies" + ex);
			throw new DataLoaderException("Generic Exception  Loading Currencies" + ex.getMessage());
		}
	}

}
