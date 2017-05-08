package conversionrule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.conversionrule.dto.ConversionRules;
import com.anz.securities.conversionrule.loader.XMLConversionRuleLoader;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LoggerFactory.getLogger(XMLConversionRuleLoader.class);

	public static void main(String[] args) throws Exception {
		XMLConversionRuleLoader ld = new XMLConversionRuleLoader();
		ConversionRules rules = ld.loadConversionRules();
		logger.info(rules.toString());
	}
}
