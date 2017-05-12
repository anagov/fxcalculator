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

	private Map<String, String> loadSupportedCurrencies(final String resource) throws DataLoaderException {
		try {
			Document doc = CommonUtil.getXMLDocument(resource);
			NodeList nList = doc.getElementsByTagName(Constants.CURRENCY);

			Map<String, String> supportedCurrencies = new HashMap<String, String>();
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
		} catch (Exception ex) {
			logger.error("Generic Exception loading Currencies" + ex);
			throw new DataLoaderException("Generic Exception  Loading Currencies" + ex.getMessage());
		}
	}

}
