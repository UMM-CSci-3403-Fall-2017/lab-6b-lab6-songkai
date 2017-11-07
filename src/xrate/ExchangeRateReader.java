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


/**
 * Provide access to basic currency exchange rate services.
 * 
 * @author PUT YOUR TEAM NAME HERE
 * Lab6b-songkai
 */
public class ExchangeRateReader {
	 String stringUrl;
	 URL url;
	 InputStream xml;

    /**
     * Construct an exchange rate reader using the given base URL. All requests
     * will then be relative to that URL. If, for example, your source is Xavier
     * Finance, the base URL is http://api.finance.xaviermedia.com/api/ Rates
     * for specific days will be constructed from that URL by appending the
     * year, month, and day; the URL for 25 June 2010, for example, would be
     * http://api.finance.xaviermedia.com/api/2010/06/25.xml
     * 
     * @param baseURL
     *            the base URL for requests
     */
    public ExchangeRateReader(String baseURL) throws IOException{
       stringUrl = baseURL;
        
    }

    /**
     * Get the exchange rate for the specified currency against the base
     * currency (the Euro) on the specified date.
     * 
     * @param currencyCode
     *            the currency code for the desired currency
     * @param year
     *            the year as a four digit integer
     * @param month
     *            the month as an integer (1=Jan, 12=Dec)
     * @param day
     *            the day of the month as an integer
     * @return the desired exchange rate
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public float getExchangeRate(String currencyCode, int year, int month, int day){
        try{
        	String monthString;
        	String dayString;
        	monthString = month + "";
        	dayString = day + "";
        	
        	if(month < 10){
        		monthString = "0" + monthString;
        	}
        	if (day < 10){
        		dayString = "0" + dayString;
        	}
        	
        	stringUrl = stringUrl + year + "/" + monthString + "/" + dayString + ".xml";
        	url = new URL(stringUrl);
        	xml = url.openStream();
        	ForXML forXML = new ForXML();
        			
        	return 	forXML.getExchangeRate(currencyCode, xml);
        }catch(UnsupportedOperationException | IOException | ParserConfigurationException | SAXException e){
        	throw new UnsupportedOperationException();
        }
    	
    	
        
    }

    /**
     * Get the exchange rate of the first specified currency against the second
     * on the specified date.
     * 
     * @param currencyCode
     *            the currency code for the desired currency
     * @param year
     *            the year as a four digit integer
     * @param month
     *            the month as an integer (1=Jan, 12=Dec)
     * @param day
     *            the day of the month as an integer
     * @return the desired exchange rate
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public float getExchangeRate(String fromCurrency, String toCurrency,int year, int month, int day) {
    	try{
        	String monthString;
        	String dayString;
        	monthString = month + "";
        	dayString = day + "";
        	
        	if(month < 10){
        		monthString = "0" + monthString;
        	}
        	if (day < 10){
        		dayString = "0" + dayString;
        	}
        	
        	stringUrl = stringUrl + year + "/" + monthString + "/" + dayString + ".xml";
        	url = new URL(stringUrl);
        	xml = url.openStream();
        	ForXML forXML = new ForXML();
        			
        	return 	forXML.getExchangeRate(fromCurrency, toCurrency, xml);
        }catch(UnsupportedOperationException | IOException | ParserConfigurationException | SAXException e){
        	throw new UnsupportedOperationException();
        }
    	
        
    }
}
