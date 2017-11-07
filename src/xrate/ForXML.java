package xrate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ForXML {
		
		/**
		 * Get a XML file then find the exchange rate
		 * @param currencyCode is the currency code 
		 * @param xml is the XML file
		 * @throws ParserConfigurationException
		 * @throws SAXException
		 * @throws IOException
		 * @return the returned value should be currencyCode to the euro
		 */
	    public float getExchangeRate(String currencyCode, InputStream xml) throws ParserConfigurationException, SAXException, IOException {
			Document document = createDocument(xml);
			NodeList nodes = document.getElementsByTagName("fx");
			float result = 0;
			for (int i = 0; i < nodes.getLength(); i++) {
				Element node = (Element) nodes.item(i);
				NodeList codes = node.getElementsByTagName("currency_code");
				Element firstElement = (Element) codes.item(0);
				NodeList children = firstElement.getChildNodes();
				String value = children.item(0).getNodeValue();

				if (value.equals(currencyCode)) {
					codes = node.getElementsByTagName("rate");
					firstElement = (Element) codes.item(0);
					children = firstElement.getChildNodes();
					result = Float.parseFloat(children.item(0).getNodeValue());
					break;
				}
			}

			return result;

		}
		
		/**
		 * Get a XML file then find the exchange rate (two currency input)
		 * @param fromCurrency 
		 * @param toCurrency 
		 * @param xml
		 * @throws ParserConfigurationException
		 * @throws SAXException
		 * @throws IOException
		 * @return the result should be fromCurrency/toCurrency
		 */
		public float getExchangeRate(String fromCurrency, String toCurrency, InputStream XMLStream) throws ParserConfigurationException, SAXException, IOException {
			Document document = createDocument(XMLStream);
			NodeList nodes = document.getElementsByTagName("fx");
			float ratefrom = -1;
			float rateto = -1;
			for (int i = 0; i < nodes.getLength(); i++) {

				if (ratefrom != -1 && rateto != -1) {
					break;
				}

				Element node = (Element) nodes.item(i);
				NodeList codes = node.getElementsByTagName("currency_code");
				Element firstElement = (Element) codes.item(0);
				NodeList children = firstElement.getChildNodes();
				String value = children.item(0).getNodeValue();

				if (value.equals(fromCurrency)) {
					codes = node.getElementsByTagName("rate");
					firstElement = (Element) codes.item(0);
					children = firstElement.getChildNodes();
					ratefrom = Float.parseFloat(children.item(0).getNodeValue());
				}

				if (value.equals(toCurrency)) {
					codes = node.getElementsByTagName("rate");
					firstElement = (Element) codes.item(0);
					children = firstElement.getChildNodes();
					rateto = Float.parseFloat(children.item(0).getNodeValue());
				}
			}
			return ratefrom / rateto;
		}

		public Document createDocument(InputStream xml)
				throws ParserConfigurationException, SAXException, IOException {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			return doc;
		}

		
	}
