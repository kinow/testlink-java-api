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
package br.eti.kinoshita.testlinkjavaapi.testcase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
public class TestGetTestCasesForTestPlan 
extends BaseTest
{
	
	@DataProvider(name="testPlanData")
	public Object[][] createTestPlanData()
	{
		return new Object[][] 
        {
			{
				10
			}
        };
	}
	
	@DataProvider(name="testPlanWithoutTestCaseData")
	public Object[][] createTestPlanWithoutTestCaseData()
	{
		return new Object[][] 
        {
			{
				16
			}
        };
	}
	
	@Test(dataProvider="testPlanData")
	public void testGetTestCasesForTestPlan(Integer testPlanId)
	{
		this.loadXMLRPCMockData("tl.getTestCasesForTestPlan.xml");
		
		TestCase[] testCases = null;
		
		try 
		{
			testCases = this.api.getTestCasesForTestPlan(
				testPlanId, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null
			);
		} 
		catch (TestLinkAPIException e) 
		{
			Assert.fail(e.getMessage(), e);
		}
		
		Assert.assertNotNull( testCases );
		
		Assert.assertTrue( testCases.length > 0  );
	}
	
//	@Test(dataProvider="testPlanWithoutTestCaseData", 
//			expectedExceptions=TestLinkAPIException.class)
//	public void testGetTestCasesForTestPlanWithoutTestCases(Integer testPlanId) 
//	throws TestLinkAPIException
//	{
//		this.loadXMLRPCMockData("tl.getTestCase.xml");
//		
//		this.api.getTestCasesForTestPlan(
//			testPlanId, 
//			null, 
//			null, 
//			null, 
//			null, 
//			null, 
//			null, 
//			null, 
//			null, 
//			null
//		);
//		
//		Assert.fail( "Not supposed to get here." );
//	}
	
	@Test(dataProvider="testPlanData")
	public void testGetAutomatedTestCasesForTestPlan(Integer testPlanId)
	{
		this.loadXMLRPCMockData("tl.getTestCasesForTestPlan.xml");
		
		TestCase[] testCases = null;
		
		try 
		{
			testCases = this.api.getTestCasesForTestPlan(
				testPlanId, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				ExecutionType.AUTOMATED, 
				null
			);
		} 
		catch (TestLinkAPIException e) 
		{
			Assert.fail(e.getMessage(), e);
		}
		
		Assert.assertNotNull( testCases );
		
		Assert.assertTrue( testCases.length == 2 );
	}

}
