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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
public class TestIndependentTests 
extends BaseTest
{
	
	@DataProvider(name="validUsers")
	public Object[][] createData()
	{
		return new Object[][] 
        {
			{
				"admin"
			}
        };
	}
	
	@Test
	public void testCheckValidDevKey()
	{
		
		String validDevKey = this.getDevKey();
		
		Boolean validKey = null;
		
		try
		{
			validKey = api.checkDevKey(validDevKey);
		} 
		catch (TestLinkAPIException e)
		{
			fail(e.getMessage(), e);
		}
		
		assertTrue( validKey );
	}
	
	@Test(expectedExceptions={TestLinkAPIException.class})
	public void testCheckInvalidDevKey() 
	throws TestLinkAPIException
	{
		api.checkDevKey( ""+System.currentTimeMillis() );
	}
	
	@Test(dataProvider="validUsers")
	public void testDoesUserExist(String user)
	{
		Boolean exists = false;
		
		try 
		{
			exists = api.doesUserExist(user);
		}
		catch (TestLinkAPIException e)
		{
			fail(e.getMessage(), e);
		}
		
		assertTrue( exists );
	}
	
	@Test
	public void testPingAndSayHello()
	{
		String pingMessage = null;
		String sayHelloMessage = null;
		
		try 
		{
			pingMessage = this.api.ping();
			sayHelloMessage = this.api.sayHello();
		} 
		catch (TestLinkAPIException e) 
		{
			fail(e.getMessage(), e);
		}
		
		assertNotNull( pingMessage );
		assertNotNull( sayHelloMessage );
		
		assertEquals( pingMessage, sayHelloMessage );
		
		assertTrue( pingMessage.equals( "Hello!" ) );
	}
	
	@Test
	public void testAbout()
	{
		String aboutMessage = null;
		
		try 
		{
			aboutMessage = this.api.about();
		} 
		catch (TestLinkAPIException e) 
		{
			fail(e.getMessage(), e);
		}
		
		assertNotNull( aboutMessage );
		
		assertTrue( aboutMessage.contains("Testlink API Version:") );
	}
	 
	@Test
	public void testSetTestMode( )
	{
		Boolean expectedToBeTrue = false;
		
		try 
		{
			expectedToBeTrue = this.api.setTestMode( Boolean.TRUE );
		} 
		catch (TestLinkAPIException e) 
		{
			fail(e.getMessage(), e);
		}
		
		assertTrue( expectedToBeTrue );
	}
	
	@Test
	public void testRepeat()
	{
		String token = ""+System.currentTimeMillis();
		
		String strRepeated = null;
		
		try 
		{
			strRepeated = this.api.repeat( token );
		} 
		catch (TestLinkAPIException e) 
		{
			fail(e.getMessage(), e);
		}
		
		assertNotNull( strRepeated );
		
		assertEquals(strRepeated, "You said: " + token );
	}

}
