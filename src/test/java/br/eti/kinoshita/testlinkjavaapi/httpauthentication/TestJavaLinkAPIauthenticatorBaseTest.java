package br.eti.kinoshita.testlinkjavaapi.httpauthentication;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;



import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;


/**
 * @author Ryo hang - xiaot_Tag@hotmail.com
 * In order to test it you have to turn on the http authentication.
 */
public class TestJavaLinkAPIauthenticatorBaseTest extends Assert {


	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DEVKEY = "devkey";
	
	/**
	 * Http authentication username property name.
	 */
	private static final String PROPERTY_PUSER = "pusername";
	
	/**
	 * Http authentication password property name.
	 */
	private static final String PROPERTY_PPASSWORD = "ppassword";
	private Properties testlinkProperties = null;
	protected TestLinkAPI api;
	protected String url;
	protected String devKey;
	
	/**
	 * TestLink http authentication Username.
	 */
	protected String Puser;
	/**
	 * TestLink http authentication Password.
	 */
	protected String Ppassword;
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
		String Puser;
		String Ppassword;
		
		try 
		{
			this.testlinkProperties = new Properties();
			this.testlinkProperties.load(BaseTest.class.getClassLoader().
					getResourceAsStream("testlink.properties"));
			
			url = this.testlinkProperties.getProperty(PROPERTY_URL);
			devKey = this.testlinkProperties.getProperty(PROPERTY_DEVKEY);
			Puser=this.testlinkProperties.getProperty(PROPERTY_PUSER);
			Ppassword=this.testlinkProperties.getProperty(PROPERTY_PPASSWORD);
		}
		catch (Exception e) 
		{
			throw new RuntimeException("Failed to read testlink.properties: " + e.getMessage(), e);
		}
		try
		{
			api = new TestLinkAPI(new URL(url), devKey,Puser,Ppassword);
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
