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
	
	private static final ConversionRateLoaderFactory factory = new ConversionRateLoaderFactory();
	
	private ConversionRateLoaderFactory() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static ConversionRateLoaderFactory getInstamce() {
		return factory;
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 * @throws LoaderNotSupportedException
	 */
	public ConversionRateLoader getRatesLoader( final LoaderType  source) throws LoaderNotSupportedException {
		
		if (source == LoaderType.XML ) {
			return new XMLConversionRateLoader();
			
		} else {
			throw new LoaderNotSupportedException("Loader not supported");
		}
	}

}
