/*
 * The MIT License
 *
 * Copyright (c) <2010> <Bruno P. Kinoshita>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.eti.kinoshita.testlinkjavaapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

/**
 * TestLink tests base class. Extends TestNG Assert class for conveninence.
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
public class BaseTest 
extends Assert 
{

	/**
	 * URL property name.
	 */
	private static final String PROPERTY_URL = "url";
	
	/**
	 * Developer key property name.
	 */
	private static final String PROPERTY_DEVKEY = "devkey";

	/**
	 * TestLink properties.
	 */
	private Properties testlinkProperties = null;
	
	/**
	 * TestLink API.
	 */
	protected TestLinkAPI api;
	
	/**
	 * TestLink URL.
	 */
	protected String url;
	
	/**
	 * TestLink Developer Key.
	 */
	protected String devKey;
	
	/**
	 * @return the api
	 */
	public TestLinkAPI getTestLinkApi() {
		return api;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the devKey
	 */
	public String getDevKey() {
		return devKey;
	}

	/**
	 * Set up method that creates the instance of the TestLink API.
	 */
	@BeforeClass
	protected void setUp()
	{
		
		String url;
		String devKey;
		
		try 
		{
			this.testlinkProperties = new Properties();
			this.testlinkProperties.load(BaseTest.class.getClassLoader().
					getResourceAsStream("testlink.properties"));
			
			url = this.testlinkProperties.getProperty(PROPERTY_URL);
			devKey = this.testlinkProperties.getProperty(PROPERTY_DEVKEY);
		}
		catch (Exception e) 
		{
			throw new RuntimeException("Failed to read testlink.properties: " + e.getMessage(), e);
		}
		
		try
		{
			api = new TestLinkAPI(new URL(url), devKey);
		} 
		catch (MalformedURLException e)
		{
			fail(e.getMessage(), e);
		} 
		catch (TestLinkAPIException e)
		{
			fail(e.getMessage(), e);
		}
	}
	
}
