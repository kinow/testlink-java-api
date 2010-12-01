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
package br.eti.kinoshita.testlinkjavaapi.model;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum TestLinkMethods
{

	createTestProject("tl.createTestProject"), 
	getTestProjectByName("tl.getTestProjectByName"), 
	checkDevKey("tl.checkDevKey"), 
	getProjects("tl.getProjects"), 
	createTestPlan("tl.createTestPlan"), 
	getTestPlanByName("tl.getTestPlanByName"), 
	getProjectTestPlans("tl.getProjectTestPlans"), 
	getTestPlanPlatforms("tl.getTestPlanPlatforms"), 
	createTestCase("tl.createTestCase"), 
	createTestSuite("tl.createTestSuite"), 
	addTestCaseToTestPlan("tl.addTestCaseToTestPlan"), 
	doesUserExist("tl.doesUserExist"), 
	sayHello("tl.sayHello"), 
	about("tl.about"), 
	getTestSuiteByID("tl.getTestSuiteByID"), 
	setTestMode("tl.setTestMode"), 
	repeat("tl.repeat"),
	getTestCase("tl.getTestCase"),
	getTestCasesForTestSuite("tl.getTestCasesForTestSuite"), 
	createBuild("tl.createBuild"), 
	getTestCasesForTestPlan("tl.getTestCasesForTestPlan"),
	getTestCaseIDByName("tl.getTestCaseIDByName"),
	getTestSuitesForTestPlan("tl.getTestSuitesForTestPlan"), 
	uploadAttachment("tl.uploadAttachment"), 
	uploadTestCaseAttachment("tl.uploadTestCaseAttachment"), 
	uploadTestSuiteAttachment("tl.uploadTestSuiteAttachment"), 
	uploadTestProjectAttachment("tl.uploadTestProjectAttachment"), 
	uploadRequirementAttachment("tl.uploadRequirementAttachment"), 
	uploadRequirementSpecificationAttachment("tl.uploadRequirementSpecificationAttachment"), 
	getTestCaseAttachments("tl.getTestCaseAttachments"), 
	deleteExecution("tl.deleteExecution"), 
	getFullPath("tl.getFullPath"), 
	assignRequirements("tl.assignRequirements"),
	getTestSuitesForTestSuite("tl.getTestSuitesForTestSuite"),
	getFirstLevelTestSuitesForTestProject("tl.getFirstLevelTestSuitesForTestProject"), 
	reportTCResult("tl.reportTCResult"), 
	getLastExecutionResult("tl.getLastExecutionResult"), 
	getBuildsForTestPlan("tl.getBuildsForTestPlan"), 
	getLatestBuildForTestPlan("tl.getLatestBuildForTestPlan"), 
	getTestCaseCustomFieldDesignValue("tl.getTestCaseCustomFieldDesignValue"), 
	getTotalsForTestPlan("tl.getTotalsForTestPlan")
	;
	
	private String value;
	
	TestLinkMethods(String value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return this.value;
	}
	
}
