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
 * @since 
 */
public enum TestLinkParams
{
	devKey("devKey"), 
	testProjectName("testprojectname"),
	testCasePrefix("testcaseprefix"), 
	notes("notes"), 
	enableRequirements("requirementsEnabled"), 
	enableTestPriority("testPriorityEnabled"), 
	enableAutomation("automationEnabled"), 
	enableInventory("inventoryEnabled "), 
	active("active"), 
	public_("public"), 
	testPlanName("testplanname"), 
	options("options"), 
	testProjectId("testprojectid"), 
	testPlanId("testplanid"), 
	testCaseName("testcasename"), 
	testSuiteId("testsuiteid"), 
	authorLogin("authorlogin"), 
	summary("summary"), 
	steps("steps"), 
	preconditions("preconditions"), 
	importance("importance"), 
	execution("execution"), 
	order("order"), 
	internalId("internalId"), 
	checkDuplicatedName("checkDuplicatedName"), 
	actionOnDuplicatedName("actionOnDuplicatedName"), 
	stepNumber("step_number"), 
	actions("actions"), 
	expectedResults("expected_results"), 
	executionType("execution_type"), 
	testSuiteName("testsuitename"), 
	parentId("parentid"), 
	details("details"), 
	testCaseId("testcaseid"), 
	version("version"), 
	platformId("platformid"), 
	urgency("urgency"), 
	user("user"), 
	testMode("testmode"), 
	testCaseExternalId("testcaseexternalid"),
	str("str"), 
	deep("deep"), 
	buildName("buildname"), 
	buildNotes("buildnotes"), 
	getStepInfo("getstepinfo"), 
	buildId("buildid"), 
	keywordId("keywordid"), 
	keywords("keywords"), 
	executed("executed"), 
	assignedTo("assignedto"), 
	executeStatus("executestatus"),
	testCasePathName("testcasepathname"), 
	fkId("fkid"), 
	fkTable("fktable"), 
	title("title"), 
	description("description"), 
	fileName("filename"), 
	fileType("filetype"), 
	content("content"), 
	requirementId("requirementid"), 
	reqSpecId("reqspecid"), 
	executionId("executionid"), 
	nodeId("nodeid"), 
	requirements("requirements"), 
	status("status"), // TBD: what is the difference between status and executestatus?
	guess("guess"), 
	bugId("bugid"), 
	platformName("platformname"), 
	customFields("customfields"), 
	overwrite("overwrite"), 
	customFieldName("customfieldname")
	;
	
	private String value;
	
	TestLinkParams(String value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return this.value;
	}

}
