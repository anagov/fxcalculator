package conm.anz.securities.conversionrate.factory;

import com.anz.securities.common.dto.LoaderType;
import com.anz.securities.common.exception.LoaderNotSupportedException;
import com.anz.securities.conversionrate.api.ConversionRateLoader;
import com.anz.securities.conversionrate.loader.XMLConversionRateLoader;

/**
 * 
 * @author xanakat
 *
 */
public class ConversionRateLoaderFactory {
	
	private static ConversionRateLoaderFactory factory;
	
	/**
	 * 
	 * @return
	 */
	public static ConversionRateLoaderFactory getInstamce() {
		if ( factory == null ) {
			factory = new ConversionRateLoaderFactory();
		}
		return factory;
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 * @throws LoaderNotSupportedException
	 */
	public ConversionRateLoader getRatesLoader( LoaderType  source) throws LoaderNotSupportedException {
		
		if (source == LoaderType.XML ) {
			return new XMLConversionRateLoader();
			
		} else {
			throw new LoaderNotSupportedException("Loader not supported");
		}
	}

}
