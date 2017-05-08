package com.anz.securities.conversionrate.loader;

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

import com.anz.securities.common.exception.CurrencyLoaderException;
import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.conversionrate.api.ConversionRateLoader;
import com.anz.securities.conversionrate.dto.ConversionRate;
import com.anz.securities.conversionrate.dto.ConversionRates;

public class XMLConversionRateLoader implements ConversionRateLoader {
	private static Logger logger = LoggerFactory.getLogger(XMLConversionRateLoader.class);
	private static final String RESOURCE_FILE_NAME = "ConversionRate.xml";

	public ConversionRates loadConversionRates() throws DataLoaderException {
		ConversionRates convRates = new ConversionRates();
		try {
			List<ConversionRate> rateList = loadSupportedCurrencies(RESOURCE_FILE_NAME);
			logger.info("Rate list populated is " + rateList.toString());
			convRates.setConversionRateList(rateList);
		} catch (Exception ex) {
			logger.error("Error loading Currencies" + ex);
			throw new DataLoaderException("Error While Loading currencies");
		}
		return convRates;
	}

	private List<ConversionRate> loadSupportedCurrencies(String fileName) throws CurrencyLoaderException {
		List<ConversionRate> rateList = new ArrayList<ConversionRate>();
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("conversionrate");
			String srcCur, destCur, rate;

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				srcCur = eElement.getElementsByTagName("fromcurrency").item(0).getTextContent();
				destCur = eElement.getElementsByTagName("tocurrency").item(0).getTextContent();
				rate = eElement.getElementsByTagName("rate").item(0).getTextContent();
				ConversionRate convRate = new ConversionRate();
				convRate.setConversionRate(rate);
				convRate.setDestinationCurrency(destCur);
				convRate.setSourceCurrency(srcCur);
				rateList.add(convRate);
			}
		} catch (Exception ex) {
			logger.error("Error loading Currencies" + ex);
			throw new CurrencyLoaderException("Error Loading Currencies");
		}
		return rateList;
	}

}