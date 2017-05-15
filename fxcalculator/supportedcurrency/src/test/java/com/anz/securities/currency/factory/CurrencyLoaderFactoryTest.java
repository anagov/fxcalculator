/**
 * 
 */
package com.anz.securities.currency.factory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anz.securities.common.dto.LoaderType;
import com.anz.securities.common.exception.LoaderNotSupportedException;
import com.anz.securities.currency.api.CurrencyLoader;
import com.anz.securities.currency.loader.XMLCurrencyLoader;

import junit.framework.Assert;

/**
 * @author ANAGOV
 *
 */
public class CurrencyLoaderFactoryTest {

	private CurrencyLoaderFactory instance;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		instance = CurrencyLoaderFactory.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test to check if the instance is not null
	 * Test method for {@link com.anz.securities.currency.factory.CurrencyLoaderFactory#getInstance()}.
	 */
	@Test
	public void testGetInstanceNotNull() {
		Assert.assertNotNull(instance);
	}

	/**
	 * Test to check if the instance is not null
	 * Test method for {@link com.anz.securities.currency.factory.CurrencyLoaderFactory#getInstance()}.
	 */
	@Test
	public void testGetInstanceSingleInstance() {
		CurrencyLoaderFactory instance1 = CurrencyLoaderFactory.getInstance();	
		Assert.assertEquals(instance, instance1);
	}

	
	/**
	 * Test method for {@link com.anz.securities.currency.factory.CurrencyLoaderFactory#getCurrencyLoader(com.anz.securities.common.dto.LoaderType)}.
	 */
	@Test
	public void testGetCurrencyLoader() throws LoaderNotSupportedException {
		LoaderType source = LoaderType.XML;
		CurrencyLoader loader = instance.getCurrencyLoader(source);
		Assert.assertTrue(loader instanceof XMLCurrencyLoader);
	}

	/**
	 * Test method for {@link com.anz.securities.currency.factory.CurrencyLoaderFactory#getCurrencyLoader(com.anz.securities.common.dto.LoaderType)}.
	 */
	@Test(expected=LoaderNotSupportedException.class)
	public void testGetCurrencyLoaderWithLoaderTypeJSON() throws LoaderNotSupportedException {
		LoaderType source = LoaderType.JSON;
		CurrencyLoader loader = instance.getCurrencyLoader(source);
		Assert.assertTrue(loader instanceof XMLCurrencyLoader);
	}


	/**
	 * Test method for {@link com.anz.securities.currency.factory.CurrencyLoaderFactory#getCurrencyLoader(com.anz.securities.common.dto.LoaderType)}.
	 */
	@Test(expected=LoaderNotSupportedException.class)
	public void testGetCurrencyLoaderWithLoaderTypeDB() throws LoaderNotSupportedException {
		LoaderType source = LoaderType.DB;
		CurrencyLoader loader = instance.getCurrencyLoader(source);
		Assert.assertTrue(loader instanceof XMLCurrencyLoader);
	}

}
