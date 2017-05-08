package conversionrate;

import com.anz.securities.conversionrate.dto.ConversionRates;
import com.anz.securities.conversionrate.loader.XMLConversionRateLoader;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		XMLConversionRateLoader loader = new XMLConversionRateLoader();
		ConversionRates rates = loader.loadConversionRates();
	}
}
