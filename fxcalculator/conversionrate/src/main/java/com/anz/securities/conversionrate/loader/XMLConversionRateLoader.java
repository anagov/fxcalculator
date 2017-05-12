package com.anz.securities.conversionrate.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import com.anz.securities.conversionrate.api.ConversionRateLoader;
import com.anz.securities.conversionrate.dto.ConversionRate;
import com.anz.securities.conversionrate.dto.ConversionRates;

/**
 * 
 * @author xanakat
 *
 */
public class XMLConversionRateLoader implements ConversionRateLoader {
	private static Logger logger = LoggerFactory.getLogger(XMLConversionRateLoader.class);

	/**
	 * 
	 */
	public ConversionRates loadConversionRates() throws DataLoaderException {
		ConversionRates convRates = new ConversionRates();

		List<ConversionRate> rateList = loadSupportedCurrencies(Constants.CONVERSION_RATE_RESOURCE_FILE);
		logger.info("Rate list populated is " + rateList.toString());
		convRates.setConversionRateList(rateList);
		return convRates;
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws CurrencyLoaderException
	 */
	private List<ConversionRate> loadSupportedCurrencies(final String fileName) throws DataLoaderException {
		try {
			List<ConversionRate> rateList = new ArrayList<ConversionRate>();

			InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
			if (input == null) {
				throw new DataLoaderException("NULL Input stream while loading currencies");
			}
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName(Constants.CONVERTION_RATE);
			String srcCur, destCur, rate;

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				srcCur = eElement.getElementsByTagName(Constants.SOURCE_CURRENCY).item(0).getTextContent();
				destCur = eElement.getElementsByTagName(Constants.DESTINATION_CURRENCY).item(0).getTextContent();
				rate = eElement.getElementsByTagName(Constants.RATE).item(0).getTextContent();
				ConversionRate convRate = new ConversionRate();
				convRate.setConversionRate(rate);
				convRate.setDestinationCurrency(destCur);
				convRate.setSourceCurrency(srcCur);
				rateList.add(convRate);
			}
			return rateList;
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
