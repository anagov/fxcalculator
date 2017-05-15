package com.anz.securities.currency.loader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.anz.securities.currency.api.CurrencyLoader;
import com.anz.securities.currency.dto.SupportedCurrencies;
import com.anz.securities.currency.factory.CurrencyLoaderFactory;

@RunWith(MockitoJUnitRunner.class)
public class XMLCurrencyLoaderTest {
	CurrencyLoader instance;
	SupportedCurrencies supCur;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		instance = CurrencyLoaderFactory.getInstance().getCurrencyLoader(LoaderType.XML);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Success scenario
	 * 
	 * @throws DataLoaderException
	 */
	@Test
	public void testLoadSupportedCurrencies() throws DataLoaderException {
		supCur = instance.loadSupportedCurrencies();
		Assert.assertNotNull(supCur);
	}

	/**
	 * Failure scenario, move the file to a different name
	 * 
	 * @throws DataLoaderException
	 */

	@Test
	public void testLoadSupportedCurrenciesFailure() throws Exception {
		thrown.expect(DataLoaderException.class);
		thrown.expectMessage("");
		XMLCurrencyLoader instance = Mockito.mock(XMLCurrencyLoader.class);

		Mockito.when(instance.loadSupportedCurrencies()).thenThrow( new DataLoaderException(""));
	}

}
