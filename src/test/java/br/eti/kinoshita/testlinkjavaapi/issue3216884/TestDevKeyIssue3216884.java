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
package br.eti.kinoshita.testlinkjavaapi.issue3216884;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
public class TestDevKeyIssue3216884 
extends BaseTest
{

	@Test()
	public void testCheckValidKey()
	{
		try
		{
			this.api.checkDevKey(api.getDevKey());
		} 
		catch (TestLinkAPIException e)
		{
			Assert.fail( "Error checking the devKey ["+devKey+"]: " + e.getMessage(), e );
		}
	}
	
	@Test(expectedExceptions={TestLinkAPIException.class})
	public void testCheckInvalidKey() 
	throws TestLinkAPIException
	{
		try
		{
			this.api = new TestLinkAPI(this.api.getUrl(), "Haruki Murakami");
		} 
		catch (MalformedURLException e)
		{
			Assert.fail("Invalid TestLink URL ["+this.api.getUrl()+"]: " + e.getMessage(), e);
		}
	}
	
}
