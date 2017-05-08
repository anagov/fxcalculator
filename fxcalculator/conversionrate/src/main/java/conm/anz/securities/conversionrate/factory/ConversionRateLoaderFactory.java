package conm.anz.securities.conversionrate.factory;

import com.anz.securities.common.dto.LoaderType;
import com.anz.securities.common.exception.LoaderNotSupportedException;
import com.anz.securities.conversionrate.api.ConversionRateLoader;
import com.anz.securities.conversionrate.loader.XMLConversionRateLoader;

public class ConversionRateLoaderFactory {
	
	private static ConversionRateLoaderFactory factory;
	 
	public static ConversionRateLoaderFactory getInstamce() {
		if ( factory == null ) {
			factory = new ConversionRateLoaderFactory();
		}
		return factory;
	}
	
	public ConversionRateLoader getRatesLoader( LoaderType  source) throws LoaderNotSupportedException {
		
		if (source == LoaderType.XML ) {
			return new XMLConversionRateLoader();
			
		} else {
			throw new LoaderNotSupportedException("Loader not supported");
		}
	}

}
