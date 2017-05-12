package supportedcurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.common.Constants;
import com.anz.securities.currency.dto.SupportedCurrencies;
import com.anz.securities.currency.factory.CurrencyLoaderFactory;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	private App() {
	}

	public static void main(String[] args) {
		try {

			SupportedCurrencies supportedCurrencies = CurrencyLoaderFactory.getInstance().getCurrencyLoader(Constants.XML_LOADER)
					.loadSupportedCurrencies();
			logger.info("Currencies loaded are" + supportedCurrencies.toString());

		} catch (Exception ex) {
			logger.error("Error loading currencies" + ex);
		}
	}
}