/*
 * The MIT License
 *
 * Copyright (c) <2011> <Mario Fuentes>
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
import br.eti.kinoshita.testlinkjavaapi.model.ImportanceLevel;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStatus;

/**
 * @author Mario Fuentes - http://www.rhiscom.com
 * @since 1.9.4-1
 */
public class TestUpdateTestCase 
extends BaseTest
{

	@DataProvider(name="testCaseData")
	public Object[][] createData()
	{
		return new Object[][] 
        {
			{
				"tl-100", 
				"1.0.0", 
				"Sample test case",
				"No summary.", 
				"No preconditions.",
				ImportanceLevel.Medium,
				ExecutionType.AUTOMATED,
				TestCaseStatus.Final,
				"0"
			}
        };
	}
	
	@Test(dataProvider="testCaseData")
	public void testUpdateTestCase(
			String testCaseFullExternalId,
			String version,
			String name,
			String summary,
			String preconditions,
			ImportanceLevel importance,
			ExecutionType executionType,
			TestCaseStatus status,
			String estimatedExecutionDuration)
	{
		this.loadXMLRPCMockData("tl.updateTestCase.xml");
		
		try
		{
			api.updateTestCase(
				testCaseFullExternalId,
				version,
				name,
				summary,
				preconditions,
				importance,
				executionType,
				status,
				estimatedExecutionDuration
			);
		} catch (TestLinkAPIException e)
		{
			Assert.fail(e.getMessage(), e);
		}
		
		//Assert.assertNotNull( testCase );
		
		//Assert.assertTrue( testCase.getId() > 0 );
	}
	
}
