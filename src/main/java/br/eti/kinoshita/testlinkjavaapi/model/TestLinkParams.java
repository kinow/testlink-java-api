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
 * 
 * <p>This enum contains a list of the parameters passed to TestLink.</p>
 * 
 * <p>
 * <ul>
 * <li>20101129 - BUGID: 3122360 - kinow - 
 * 		Wrong execution type parameter name</li>
 * </ul>
 * </p>
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum TestLinkParams
{
	actionOnDuplicatedName("actionOnDuplicatedName"), 
	actions("actions"), 
	action("action"), 
	active("active"), 
	assignedTo("assignedto"), 
	authorLogin("authorlogin"), 
	bugId("bugid"), 
	buildId("buildid"), 
	buildName("buildname"), 
	buildNotes("buildnotes"), 
	checkDuplicatedName("checkDuplicatedName"), 
	content("content"), 
	customFieldName("customfieldname"),
	customFields("customfields"), 
	deep("deep"), 
	description("description"), 
	details("details"), 
	devKey("devKey"), 
	enableAutomation("automationEnabled"), 
	enableInventory("inventoryEnabled "), 
	enableRequirements("requirementsEnabled"), 
	enableTestPriority("testPriorityEnabled"), 
	executed("executed"), 
	executeStatus("executestatus"),
	execution("execution"), 
	executionId("executionid"), 
	executionType("executiontype"),
	stepExecutionType("execution_type"), 
	expectedResults("expected_results"), 
	fileName("filename"), 
	fileType("filetype"), 
	fkId("fkid"), 
	fkTable("fktable"), 
	getStepInfo("getstepinfo"), 
	guess("guess"), 
	importance("importance"), 
	internalId("internalId"), 
	keywordId("keywordid"), 
	keywords("keywords"), 
	nodeId("nodeid"), 
	notes("notes"), 
	options("options"), 
	order("order"), 
	overwrite("overwrite"), 
	parentId("parentid"), 
	platformId("platformid"), 
	platformName("platformname"), 
	preconditions("preconditions"), 
	public_("public"), 
	reqSpecId("reqspecid"),
	requirementId("requirementid"), 
	requirements("requirements"), 
	summary("summary"), 
	status("status"), // TBD: what is the difference between status and executestatus?
	stepNumber("step_number"), 
	steps("steps"), 
	str("str"), 
	testProjectName("testprojectname"),
	testCaseExternalId("testcaseexternalid"),
	testCaseId("testcaseid"), 
	testCaseName("testcasename"), 
	testCasePathName("testcasepathname"), 
	testCasePrefix("testcaseprefix"), 
	testMode("testmode"), 
	testPlanId("testplanid"), 
	testPlanName("testplanname"), 
	testProjectId("testprojectid"), 
	testSuiteId("testsuiteid"), 
	testSuiteName("testsuitename"), 
	title("title"), 
	urgency("urgency"), 
	user("user"), 
	version("version")
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
