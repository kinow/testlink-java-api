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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.ResponseDetails;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStepAction;
import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkTables;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * 
 * <p>
 * Class responsible for Test Case services.
 * </p>
 * 
 * <p>
 * <ul>
 * <li>20101130 - BUGID: 3123764 - kinow - reportTCresult not returning
 * execution data</li>
 * </ul>
 * </p>
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
class TestCaseService extends BaseService {

    /**
     * @param xmlRpcClient
     *            XML RPC Client.
     * @param devKey
     *            TestLink User DevKey.
     */
    public TestCaseService(XmlRpcClient xmlRpcClient, String devKey) {
	super(xmlRpcClient, devKey);
    }

    /**
     * Creates a Test Case.
     * 
     * @param testCaseName
     * @param testSuiteId
     * @param testProjectId
     * @param authorLogin
     * @param summary
     * @param steps
     * @param preconditions
     * @param importance
     * @param execution
     * @param order
     * @param internalId
     * @param checkDuplicatedName
     * @param actionOnDuplicatedName
     * @return Test Case.
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected TestCase createTestCase(String testCaseName, Integer testSuiteId,
	    Integer testProjectId, String authorLogin, String summary,
	    List<TestCaseStep> steps, String preconditions,
	    TestImportance importance, ExecutionType execution, Integer order,
	    Integer internalId, Boolean checkDuplicatedName,
	    ActionOnDuplicate actionOnDuplicatedName) throws TestLinkAPIException {
	TestCase testCase = null;

	Integer id = null;

	testCase = new TestCase(id, testCaseName, testSuiteId, testProjectId,
		authorLogin, summary, steps, preconditions, importance,
		execution, null, order, internalId, checkDuplicatedName,
		actionOnDuplicatedName, null, null, null, null, null);

	try {
	    Map<String, Object> executionData = Util.getTestCaseMap(testCase);
	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.CREATE_TEST_CASE.toString(), executionData);
	    Object[] responseArray = Util.castToArray(response);
	    Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

	    id = Util.getInteger(responseMap,
		    TestLinkResponseParams.ID.toString());
	    testCase.setId(id);
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException("Error creating test plan: "
		    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return testCase;
    }

    /**
     * 
     * @param testCaseFullExternalId
     * @param version
     * @param name
     * @param summary
     * @param preconditions
     * @param importance
     * @param executionType
     * @param status
     * @param estimatedExecutionDuration
     * @throws TestLinkAPIException
     */
    public void updateTestCase(String testCaseFullExternalId, String version,
	    String name, String summary, String preconditions,
	    TestImportance importance, ExecutionType executionType,
	    TestCaseStatus status, String estimatedExecutionDuration)
	    throws TestLinkAPIException {
	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();
	    executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(),
		    testCaseFullExternalId);
	    executionData.put(TestLinkParams.VERSION.toString(),
		    Util.getStringValueOrNull(version));
	    executionData.put(TestLinkParams.NAME.toString(),
		    Util.getStringValueOrNull(name));
	    executionData.put(TestLinkParams.SUMMARY.toString(),
		    Util.getStringValueOrNull(summary));
	    executionData.put(TestLinkParams.PRECONDITIONS.toString(),
		    Util.getStringValueOrNull(preconditions));
	    executionData.put(TestLinkParams.IMPORTANCE.toString(),
		    importance.getValue());
	    executionData.put(TestLinkParams.STEP_EXECUTION_TYPE.toString(),
		    executionType.getValue());
	    executionData.put(TestLinkParams.STATUS.toString(),
		    status.getValue());
	    executionData.put(
		    TestLinkParams.ESTIMATED_EXECUTION_DURATION.toString(),
		    Util.getStringValueOrNull(estimatedExecutionDuration));

	    this.executeXmlRpcCall(TestLinkMethods.UPDATE_TEST_CASE.toString(),
		    executionData);

	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException("Error adding steps to test case: "
		    + xmlrpcex.getMessage(), xmlrpcex);
	}
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> createTestCaseSteps(String testCaseExternalId,
	    Integer version, TestCaseStepAction action,
	    List<TestCaseStep> testCaseSteps) throws TestLinkAPIException {
	Map<String, Object> responseMap = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();

	    executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(),
		    testCaseExternalId);
	    executionData.put(TestLinkParams.VERSION.toString(), version);
	    executionData.put(TestLinkParams.ACTION.toString(),
		    action.toString());

	    List<Map<String, Object>> steps = Util
		    .getTestCaseStepsMap(testCaseSteps);
	    executionData.put(TestLinkParams.STEPS.toString(), steps);

	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.CREATE_TEST_CASE_STEPS.toString(),
		    executionData);
	    responseMap = (Map<String, Object>) response;
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException("Error adding steps to test case: "
		    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return responseMap;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> deleteTestCaseSteps(String testCaseExternalId,
	    Integer version, List<TestCaseStep> testCaseSteps)
	    throws TestLinkAPIException {
	Map<String, Object> responseMap = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();

	    executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(),
		    testCaseExternalId);
	    executionData.put(TestLinkParams.VERSION.toString(), version);

	    List<Integer> steps = Util.getTestCaseStepsIdList(testCaseSteps);
	    executionData.put(TestLinkParams.STEPS.toString(), steps);

	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.DELETE_TEST_CASE_STEPS.toString(),
		    executionData);
	    responseMap = (Map<String, Object>) response;
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException(
		    "Error deleting steps from test case: "
			    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return responseMap;
    }

    protected Integer addTestCaseToTestPlan(Integer testProjectId,
	    Integer testPlanId, Integer testCaseId, Integer version,
	    Integer platformId, Integer order, Integer urgency)
	    throws TestLinkAPIException {
	Integer featureId = 0;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();

	    executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(),
		    testProjectId);
	    executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
	    executionData.put(TestLinkParams.TEST_CASE_ID.toString(), testCaseId);
	    executionData.put(TestLinkParams.VERSION.toString(), version);
	    executionData.put(TestLinkParams.PLATFORM_ID.toString(), platformId);
	    executionData.put(TestLinkParams.ORDER.toString(), order);
	    executionData.put(TestLinkParams.URGENCY.toString(), testCaseId);

	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.ADD_TEST_CASE_TO_TEST_PLAN.toString(),
		    executionData);
	    Map<String, Object> responseMap = Util.castToMap(response);

	    featureId = Util.getInteger(responseMap,
		    TestLinkResponseParams.FEATURE_ID.toString());
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException(
		    "Error adding test case to test plan: "
			    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return featureId;
    }

    /**
     * @param testSuiteId
     * @param deep
     * @param DETAILS
     * @return
     */
    @SuppressWarnings("unchecked")
    protected TestCase[] getTestCasesForTestSuite(Integer testSuiteId,
	    Boolean deep, TestCaseDetails detail) throws TestLinkAPIException {

	TestCase[] testCases = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();
	    executionData.put(TestLinkParams.TEST_SUITE_ID.toString(),
		    testSuiteId);
	    executionData.put(TestLinkParams.DEEP.toString(), deep);
	    executionData.put(TestLinkParams.DETAILS.toString(),
		    Util.getStringValueOrNull(detail));
	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.GET_TEST_CASES_FOR_TEST_SUITE.toString(),
		    executionData);
	    Object[] responseArray = Util.castToArray(response);

	    testCases = new TestCase[responseArray.length];

	    for (int i = 0; i < responseArray.length; i++) {
		Map<String, Object> responseMap = (Map<String, Object>) responseArray[i];
		testCases[i] = Util.getTestCase(responseMap);
	    }
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException(
		    "Error retrieving test cases for test suite: "
			    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return testCases;
    }

    /**
     * @param testPlanId
     * @param testCasesIds
     * @param buildId
     * @param keywordsIds
     * @param keywords
     * @param executed
     * @param assignedTo
     * @param executeStatus
     * @param executionType
     * @param getStepInfo
     * @return
     * @throws TestLinkAPIException
     */
    @SuppressWarnings({ "unchecked" })
    protected TestCase[] getTestCasesForTestPlan(Integer testPlanId,
	    List<Integer> testCasesIds, Integer buildId,
	    List<Integer> keywordsIds, String keywords, Boolean executed,
	    List<Integer> assignedTo, String executeStatus,
	    ExecutionType executionType, Boolean getStepInfo,
	    TestCaseDetails detail) throws TestLinkAPIException {
	TestCase[] testCases = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();
	    executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
	    executionData.put(TestLinkParams.TEST_CASE_ID.toString(),
		    testCasesIds);
	    executionData.put(TestLinkParams.BUILD_ID.toString(), buildId);
	    executionData.put(TestLinkParams.KEYWORD_ID.toString(), keywordsIds);
	    executionData.put(TestLinkParams.KEYWORDS.toString(), keywords);
	    executionData.put(TestLinkParams.EXECUTED.toString(), executed);
	    executionData.put(TestLinkParams.ASSIGNED_TO.toString(), assignedTo);
	    executionData.put(TestLinkParams.EXECUTE_STATUS.toString(),
		    Util.getStringValueOrNull(executeStatus));
	    executionData.put(TestLinkParams.EXECUTION_TYPE.toString(),
		    Util.getStringValueOrNull(executionType));
	    executionData.put(TestLinkParams.GET_STEP_INFO.toString(),
		    getStepInfo);
	    executionData.put(TestLinkParams.DETAILS.toString(),
		    Util.getStringValueOrNull(detail));
	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.GET_TEST_CASES_FOR_TEST_PLAN.toString(),
		    executionData);

	    /*
	     * // The Util.castToMap method will return an empty Map if (
	     * response instanceof String ) { throw new TestLinkAPIException(
	     * "The test plan you requested does not contain Test Cases." ); }
	     */

	    Map<String, Object> responseMap = Util.castToMap(response);
	    Set<Entry<String, Object>> entrySet = responseMap.entrySet();
	    testCases = new TestCase[entrySet.size()];
	    int index = 0;
	    for (Entry<String, Object> entry : entrySet) {
		String key = entry.getKey();
		Map<String, Object> testCaseMap = null;

		if (entry.getValue() instanceof Object[]) {
		    Object[] responseArray = (Object[]) entry.getValue();
		    testCaseMap = (Map<String, Object>) responseArray[0];
		} else if (entry.getValue() instanceof Map<?, ?>) {
		    testCaseMap = (Map<String, Object>) entry.getValue();
		    if (testCaseMap.size() > 0) {
			Set<String> keys = testCaseMap.keySet();
			Object o = testCaseMap.get(keys.iterator().next());
			if (o instanceof Map<?, ?>) {
			    testCaseMap = (Map<String, Object>) o;
			}
		    }
		}
		testCaseMap.put(TestLinkResponseParams.ID.toString(), key);
		testCases[index] = Util.getTestCase(testCaseMap);
		index += 1;
	    }
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException(
		    "Error retrieving test cases for test plan: "
			    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return testCases;
    }

    /**
     * 
     * @param testCaseId
     * @param testCaseExternalId
     * @param version
     * @return
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected TestCase getTestCase(Integer testCaseId,
	    Integer testCaseExternalId, Integer version)
	    throws TestLinkAPIException {
	TestCase testCase = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();

	    executionData.put(TestLinkParams.TEST_CASE_ID.toString(), testCaseId);
	    executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(),
		    testCaseExternalId);
	    executionData.put(TestLinkParams.VERSION.toString(), version);

	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.GET_TEST_CASE.toString(), executionData);

	    Object[] responseArray = Util.castToArray(response);
	    Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

	    testCase = Util.getTestCase(responseMap);
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException("Error getting test case info : "
		    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return testCase;
    }

    /**
     * 
     * @param fullTestCaseExternalId
     *            Full external id: prefix-externalId
     * @param version
     * @return
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected TestCase getTestCaseByExternalId(String fullTestCaseExternalId,
	    Integer version) throws TestLinkAPIException {
	TestCase testCase = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();

	    executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(),
		    fullTestCaseExternalId);
	    executionData.put(TestLinkParams.VERSION.toString(), version);

	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.GET_TEST_CASE.toString(), executionData);

	    Object[] responseArray = Util.castToArray(response);
	    Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

	    testCase = Util.getTestCase(responseMap);
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException("Error getting test case info : "
		    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return testCase;
    }

    /**
     * 
     * @param DEV_KEY
     * @param testCaseName
     * @param testSuiteName
     * @param testProjectName
     * @param testCasePathName
     * @return
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected Integer getTestCaseIDByName(String testCaseName,
	    String testSuiteName, String testProjectName,
	    String testCasePathName) throws TestLinkAPIException {
	Integer testCaseID = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();

	    executionData.put(TestLinkParams.TEST_CASE_NAME.toString(),
		    testCaseName);
	    executionData.put(TestLinkParams.TEST_SUITE_NAME.toString(),
		    testSuiteName);
	    executionData.put(TestLinkParams.TEST_PROJECT_NAME.toString(),
		    testProjectName);
	    executionData.put(TestLinkParams.TEST_CASE_PATH_NAME.toString(),
		    testCasePathName);

	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.GET_TEST_CASE_ID_BY_NAME.toString(),
		    executionData);

	    Object[] responseArray = Util.castToArray(response);
	    Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

	    testCaseID = Util.getInteger(responseMap,
		    TestLinkResponseParams.ID.toString());
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException("Error getting test case ID : "
		    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return testCaseID;
    }

    /**
     * @param testCaseId
     * @param title
     * @param description
     * @param fileName
     * @param fileType
     * @param content
     * @return
     */
    protected Attachment uploadTestCaseAttachment(Integer testCaseId,
	    String title, String description, String fileName, String fileType,
	    String content) throws TestLinkAPIException {
	Attachment attachment = null;

	Integer id = 0;

	attachment = new Attachment(id, testCaseId,
		TestLinkTables.NODES_HIERARCHY.toString(), title, description,
		fileName, null, fileType, content);

	try {
	    Map<String, Object> executionData = Util
		    .getTestCaseAttachmentMap(attachment);
	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.UPLOAD_TEST_CASE_ATTACHMENT.toString(),
		    executionData);
	    Map<String, Object> responseMap = Util.castToMap(response);
	    id = Util.getInteger(responseMap,
		    TestLinkResponseParams.ID.toString());
	    attachment.setId(id);
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException(
		    "Error uploading attachment for test case: "
			    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return attachment;
    }

    /**
     * @param testCaseId
     * @param testCaseExternalId
     * @return
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected Attachment[] getTestCaseAttachments(Integer testCaseId,
	    Integer testCaseExternalId) throws TestLinkAPIException {
	Attachment[] attachments = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();
	    executionData.put(TestLinkParams.TEST_CASE_ID.toString(), testCaseId);
	    executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(),
		    testCaseExternalId);
	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.GET_TEST_CASE_ATTACHMENTS.toString(),
		    executionData);
	    if (response instanceof Map<?, ?>) {
		Map<String, Object> responseMap = Util.castToMap(response);
		Set<Entry<String, Object>> entrySet = responseMap.entrySet();

		attachments = new Attachment[entrySet.size()];

		int index = 0;
		for (Entry<String, Object> entry : entrySet) {
		    String key = entry.getKey();
		    Map<String, Object> attachmentMap = (Map<String, Object>) entry
			    .getValue();
		    attachmentMap
			    .put(TestLinkResponseParams.ID.toString(), key);
		    attachments[index] = Util.getAttachment(attachmentMap);
		    index += 1;
		}
	    } else {
		attachments = new Attachment[0];
	    }

	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException(
		    "Error retrieving test case's attachments: "
			    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return attachments;
    }

    protected Attachment uploadExecutionAttachment(Integer executionId,
	    String title, String description, String fileName, String fileType,
	    String content) throws TestLinkAPIException {
	Attachment attachment = null;

	Integer id = 0;

	attachment = new Attachment(id, executionId,
		TestLinkTables.EXECUTIONS.toString(), title, description,
		fileName, null, fileType, content);

	try {
	    Map<String, Object> executionData = Util
		    .getExecutionAttachmentMap(attachment);

	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.UPLOAD_EXECUTION_ATTACHMENT.toString(),
		    executionData);
	    Map<String, Object> responseMap = Util.castToMap(response);
	    id = Util.getInteger(responseMap,
		    TestLinkResponseParams.ID.toString());
	    attachment.setId(id);
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException(
		    "Error uploading attachment for execution: "
			    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return attachment;
    }

    /**
     * @param executionId
     * @return
     * @te
     */
    protected void deleteExecution(Integer executionId)
	    throws TestLinkAPIException {
	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();
	    executionData.put(TestLinkParams.EXECUTION_ID.toString(),
		    executionId);
	    this.executeXmlRpcCall(TestLinkMethods.DELETE_EXECUTION.toString(),
		    executionData);
	    // the error verification routine is called inside
	    // super.executeXml...
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException("Error deleting execution: "
		    + xmlrpcex.getMessage(), xmlrpcex);
	}
    }

    /**
     * @param testCaseId
     * @param testCaseExternalId
     * @param testPlanId
     * @param status
     * @param buildId
     * @param buildName
     * @param notes
     * @param guess
     * @param bugId
     * @param platformId
     * @param platformName
     * @param customFields
     * @param overwrite
     * @return Response object of reportTCResult method
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected ReportTCResultResponse reportTCResult(Integer testCaseId,
	    Integer testCaseExternalId, Integer testPlanId,
	    ExecutionStatus status, Integer buildId, String buildName,
	    String notes, Boolean guess, String bugId, Integer platformId,
	    String platformName, Map<String, String> customFields, // TODO:
								   // change for
								   // a list of
								   // custom
								   // fields.
								   // After
								   // implementing
								   // method
								   // getTestCaseCustomFieldDesignValue
								   // this
								   // entities
								   // properties
								   // will
								   // become
								   // much more
								   // clear
	    Boolean overwrite) throws TestLinkAPIException {

	ReportTCResultResponse reportTCResultResponse = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();
	    executionData.put(TestLinkParams.TEST_CASE_ID.toString(), testCaseId);
	    executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(),
		    testCaseExternalId);
	    executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
	    executionData.put(TestLinkParams.STATUS.toString(),
		    status.toString());
	    executionData.put(TestLinkParams.BUILD_ID.toString(), buildId);
	    executionData.put(TestLinkParams.BUILD_NAME.toString(), buildName);
	    executionData.put(TestLinkParams.NOTES.toString(), notes);
	    executionData.put(TestLinkParams.GUESS.toString(), guess);
	    executionData.put(TestLinkParams.BUG_ID.toString(), bugId);
	    executionData.put(TestLinkParams.PLATFORM_ID.toString(), platformId);
	    executionData.put(TestLinkParams.PLATFORM_NAME.toString(),
		    platformName);
	    executionData.put(TestLinkParams.CUSTOM_FIELDS.toString(),
		    customFields);
	    executionData.put(TestLinkParams.OVERWRITE.toString(), overwrite);
	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.REPORT_TC_RESULT.toString(), executionData);
	    // the error verification routine is called inside
	    // super.executeXml...
	    if (response instanceof Object[]) {
		Object[] responseArray = Util.castToArray(response);
		Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

		reportTCResultResponse = Util
			.getReportTCResultResponse(responseMap);
	    }
	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException("Error reporting TC result: "
		    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return reportTCResultResponse;

    }

    /**
     * @param testCaseId
     * @param testCaseExternalId
     * @param versionNumber
     * @param testProjectId
     * @param customFieldName
     * @param details
     * @return Custom Field.
     * @throws TestLinkAPIException
     */
    protected CustomField getTestCaseCustomFieldDesignValue(Integer testCaseId,
	    Integer testCaseExternalId, Integer versionNumber,
	    Integer testProjectId, String customFieldName,
	    ResponseDetails details) throws TestLinkAPIException {
	CustomField customField = null;

	try {
	    Map<String, Object> executionData = new HashMap<String, Object>();
	    executionData.put(TestLinkParams.TEST_CASE_ID.toString(), testCaseId);
	    executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(),
		    testCaseExternalId);
	    executionData.put(TestLinkParams.VERSION.toString(), versionNumber);
	    executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(),
		    testProjectId);
	    executionData.put(TestLinkParams.CUSTOM_FIELD_NAME.toString(),
		    customFieldName);
	    executionData.put(TestLinkParams.DETAILS.toString(),
		    Util.getStringValueOrNull(details));

	    Object response = this.executeXmlRpcCall(
		    TestLinkMethods.GET_TEST_CASE_CUSTOM_FIELD_DESIGN_VALUE
			    .toString(), executionData);

	    if (response instanceof String) {
		customField = new CustomField();
		customField.setValue(response.toString());
	    } else if (response instanceof Map<?, ?>) {
		Map<String, Object> responseMap = Util.castToMap(response);
		customField = Util.getCustomField(responseMap);
	    }

	} catch (XmlRpcException xmlrpcex) {
	    throw new TestLinkAPIException(
		    "Error retrieving test case custom field value: "
			    + xmlrpcex.getMessage(), xmlrpcex);
	}

	return customField;
    }
}
