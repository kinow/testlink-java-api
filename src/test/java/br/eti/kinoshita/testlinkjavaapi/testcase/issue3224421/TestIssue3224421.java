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
package br.eti.kinoshita.testlinkjavaapi.testcase.issue3224421;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.Execution;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.1-1
 */
public class TestIssue3224421 
extends BaseTest
{

	@Test
	public void testRetrieveTestCaseForBuild()
	{
		try
		{
			this.loadXMLRPCMockData("tl.getTestPlanByName.xml");
			TestPlan plan = this.api.getTestPlanByName("Sample plan", "Sample project");
			
			this.loadXMLRPCMockData("tl.getLatestBuildForTestPlan.xml");
			Build build = this.api.getLatestBuildForTestPlan(plan.getId());
			
			Assert.assertNotNull( build );
			
			this.loadXMLRPCMockData("tl.getTestCasesForTestPlan.xml");
			TestCase[] tcs = this.api.getTestCasesForTestPlan(
					plan.getId(), 
					null, 
					null, 
					null, 
					null,  
					Boolean.TRUE, 
					null, 
					null, 
					null, 
					null,
					null);
			
			for( TestCase tc : tcs )
			{
				Assert.assertNotNull(tc.getExecutionStatus());
				if ( tc.getExecutionStatus() != ExecutionStatus.NOT_RUN )
				{
					this.loadXMLRPCMockData("tl.getLastExecutionResult.xml");
					Execution execution = this.api.getLastExecutionResult(plan.getId(), tc.getId(), null);
					Assert.assertNotNull(execution);
				}
			}
		} 
		catch ( TestLinkAPIException e )
		{
			Assert.fail(""+e.getMessage(), e);
		}
	}
	
}
