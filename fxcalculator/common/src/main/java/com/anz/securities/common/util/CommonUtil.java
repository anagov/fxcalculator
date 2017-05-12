package com.anz.securities.common.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.anz.securities.common.exception.DataLoaderException;

public class CommonUtil {
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static Document getXMLDocument(final String resource) throws DataLoaderException {
		try {
			InputStream input = CommonUtil.class.getClassLoader().getResourceAsStream(resource);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			if (input == null) {
				throw new DataLoaderException("NULL Input stream while loading currencies");
			}
			Document doc = dBuilder.parse(input);

			doc.getDocumentElement().normalize();
			return doc;
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
