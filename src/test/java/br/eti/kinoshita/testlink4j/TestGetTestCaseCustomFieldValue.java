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
package br.eti.kinoshita.testlink4j;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlink4j.model.CustomField;
import br.eti.kinoshita.testlink4j.util.ResponseDetails;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
public class TestGetTestCaseCustomFieldValue 
extends BaseTest
{

	@DataProvider(name="testCaseWithCustomField")
	public Object[][] createData()
	{
		return new Object[][] 
        {
			{
				4, 
				1, 
				1, 
				"SampleCustomField"
			}, 
			{
				6, 
				1, 
				1, 
				"SampleCustomField"
			}
        };
	}
	
	@Test(dataProvider="testCaseWithCustomField")
	public void testGetTestCaseCustomFieldDesignValueFull(
		Integer testCaseId, 
		Integer versionNumber, 
		Integer testProjectId, 
		String customFieldName
	)
	{
		CustomField customField = null;
		try
		{
			customField = this.api.getTestCaseCustomFieldDesignValue(
				testCaseId, 
				null, 
				versionNumber, 
				testProjectId, 
				customFieldName, 
				ResponseDetails.FULL);
		} 
		catch (TestLinkAPIException e)
		{
			fail(e.getMessage(), e);
		}
		assertNotNull( customField );
		
		assertNotNull( customField.getValue() );
		
		assertTrue( customField.getValue().length() > 0 );
	}
	
	@Test(dataProvider="testCaseWithCustomField")
	public void testGetTestCaseCustomFieldDesignValueSimple(
		Integer testCaseId, 
		Integer versionNumber, 
		Integer testProjectId, 
		String customFieldName
	)
	{
		CustomField customField = null;
		try
		{
			customField = this.api.getTestCaseCustomFieldDesignValue(
				testCaseId, 
				null, 
				versionNumber, 
				testProjectId, 
				customFieldName, 
				ResponseDetails.SIMPLE);
		} 
		catch (TestLinkAPIException e)
		{
			fail(e.getMessage(), e);
		}
		assertNotNull( customField );
		
		assertNotNull( customField.getValue() );
		
		assertTrue( customField.getValue().length() > 0 );
	}
	
	@Test(dataProvider="testCaseWithCustomField")
	public void testGetTestCaseCustomFieldDesignValueValue(
		Integer testCaseId, 
		Integer versionNumber, 
		Integer testProjectId, 
		String customFieldName
	)
	{
		CustomField customField = null;
		try
		{
			customField = this.api.getTestCaseCustomFieldDesignValue(
				testCaseId, 
				null, 
				versionNumber, 
				testProjectId, 
				customFieldName, 
				ResponseDetails.VALUE);
		} 
		catch (TestLinkAPIException e)
		{
			fail(e.getMessage(), e);
		}
		assertNotNull( customField );
		
		assertNotNull( customField.getValue() );
		
		assertTrue( customField.getValue().length() > 0 );
	}
	
}
