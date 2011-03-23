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
 * <p>
 * <ul>
 * <li>20101130 - BUGID: 3123764 - kinow - 
 * 		reportTCresult not returning execution data</li>
 * </ul>
 * </p>
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum TestLinkResponseParams
{
	
	id("id"), 
	name("name"), 
	prefix("prefix"), 
	notes("notes"), 
	active("active"), 
	isPublic("is_public"), 
	opt("opt"), 
	requirementsEnabled("requirementsEnabled"), 
	testPriorityEnabled("testPriorityEnabled"),
	automationEnabled("automationEnabled"), 
	inventoryEnabled("inventoryEnabled"), 
	projectName("projectname"), 
	featureId("feature_id"), 
	details("details"), 
	parentId("parent_id"), 
	order("node_order"), 
	version("version"), 
	preconditions("preconditions"), 
	testCaseVersionId("tcversion_id"), 
	summary("summary"), 
	executionType("execution_type"), 
	testCaseId("tc_id"), 
	content("content"), 
	fileType("file_type"), 
	title("title"), 
	buildId("build_id"), 
	testerId("tester_id"), 
	status("status"), 
	testPlanId("testplan_id"), 
	testcaseVersionNumber("tcversion_number"), 
	defaultValue("default_value"), 
	displayOrder("display_order"), 
	enableOnDesign("enable_on_design"), 
	enableOnExecution("enable_on_execution"), 
	enableOnTestPlanDesign("enable_on_testplan_design"), 
	label("label"), 
	lengthMax("length_max"), 
	lengthMin("length_min"), 
	location("location"), 
	possibleValues("possible_values"), 
	showOnDesign("show_on_design"), 
	showOnExecution("show_on_execution"), 
	showOnTestPlanDesign("show_on_testplan_design"), 
	type("type"), 
	validRegexp("valid_regexp"), 
	value("value"), 
	customFields("custom_fields"),
	operation("operation"), 
	overwrite("overwrite"), 
	message("message"), 
	bugIDStatus("bugidstatus"), 
	customFieldStatus("customfieldstatus"), 
	execStatus("exec_status"), 
	executionOrder("execution_order")
	;
	
	private String textValue;
	
	TestLinkResponseParams(String textValue)
	{
		this.textValue = textValue;
	}
	
	public String toString()
	{
		return this.textValue;
	}

}
