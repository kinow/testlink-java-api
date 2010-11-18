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
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
public class TestReportTCResult 
extends BaseTest
{
	
	@DataProvider(name="buildData")
	public Object[][] createData()
	{
		return new Object[][] 
        {
			{
				4, 
				10, 
				1, 
				"Sample build", 
				"Build notes.", 
				2, 
				"Post"
			}
        };
	}
	
	@Test(dataProvider="buildData")
	public void testReportTCResult(
		Integer testCaseId, 
		Integer testPlanId, 
		Integer buildId, 
		String buildName, 
		String notes, 
		Integer platformId, 
		String platformName
	)
	{
		try {
			this.api.reportTCResult(
					testCaseId, 
					null, 
					testPlanId, 
					ExecutionStatus.FAILED, 
					buildId, 
					buildName, 
					notes, 
					true, 
					null, 
					platformId, 
					platformName,  
					null, // TODO: Test custom fields! 
					true);
		}
		catch (TestLinkAPIException e) 
		{
			fail(e.getMessage(), e);
		}
	}
	
	@Test(dataProvider="buildData")
	public void testSetTestCaseExecutionResult(
		Integer testCaseId, 
		Integer testPlanId, 
		Integer buildId, 
		String buildName, 
		String notes, 
		Integer platformId, 
		String platformName
	)
	{
		try {
			this.api.setTestCaseExecutionResult(
					testCaseId, 
					null, 
					testPlanId, 
					ExecutionStatus.PASSED, 
					buildId, 
					buildName, 
					notes, 
					true, 
					null, 
					platformId, 
					platformName, 
					null, // TODO: Test custom fields! 
					true);
		}
		catch (TestLinkAPIException e) 
		{
			fail(e.getMessage(), e);
		}
	}

}
