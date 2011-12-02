/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.ResponseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStepAction;
import br.eti.kinoshita.testlinkjavaapi.model.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkTables;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * 
 * <p>Class responsible for Test Case services.</p>
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
class TestCaseService 
extends BaseService
{

	/**
	 * @param xmlRpcClient XML RPC Client.
	 * @param devKey TestLink User DevKey.
	 */
	public TestCaseService( XmlRpcClient xmlRpcClient, String devKey ) 
	{
		super( xmlRpcClient, devKey );
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
	protected TestCase createTestCase(
			String testCaseName, 
			Integer testSuiteId,
			Integer testProjectId, 
			String authorLogin, 
			String summary,
			List<TestCaseStep> steps, 
			String preconditions,
			TestImportance importance, 
			ExecutionType execution, 
			Integer order,
			Integer internalId, 
			Boolean checkDuplicatedName,
			String actionOnDuplicatedName) 
	throws TestLinkAPIException
	{
		TestCase testCase = null;
		
		Integer id = null;
		
		testCase = new TestCase(
			id, 
			testCaseName, 
			testSuiteId, 
			testProjectId, 
			authorLogin, 
			summary, 
			steps, 
			preconditions, 
			importance, 
			execution, 
			null, 
			order, 
			internalId, 
			checkDuplicatedName, 
			actionOnDuplicatedName, 
			null, 
			null,
			null, 
			null, 
			null);
		
		try
		{
			Map<String, Object> executionData = Util.getTestCaseMap(testCase);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.createTestCase.toString(), executionData);
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			testCase.setId( id );
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error creating test plan: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return testCase;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> createTestCaseSteps(
		String testCaseExternalId,
		Integer version,
		TestCaseStepAction action,
		List<TestCaseStep> testCaseSteps
	)
	throws TestLinkAPIException
	{
		Map<String, Object> responseMap = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			
			executionData.put(TestLinkParams.testCaseExternalId.toString(), testCaseExternalId);
			executionData.put(TestLinkParams.version.toString(), version);
			executionData.put(TestLinkParams.action.toString(), action.toString());
			
			List<Map<String, Object>> steps = Util.getTestCaseStepsMap(testCaseSteps);
			executionData.put(TestLinkParams.steps.toString(), steps);
			
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.createTestCaseSteps.toString(), executionData);
			responseMap = (Map<String, Object>) response;
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error adding steps to test case: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return responseMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> deleteTestCaseSteps(
		String testCaseExternalId,
		Integer version,
		List<TestCaseStep> testCaseSteps
	)
	throws TestLinkAPIException
	{
		Map<String, Object> responseMap = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			
			executionData.put(TestLinkParams.testCaseExternalId.toString(), testCaseExternalId);
			executionData.put(TestLinkParams.version.toString(), version);
			
			List<Integer> steps = Util.getTestCaseStepsIdList(testCaseSteps);
			executionData.put(TestLinkParams.steps.toString(), steps);
			
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.deleteTestCaseSteps.toString(), executionData);
			responseMap = (Map<String, Object>) response;
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error deleting steps from test case: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return responseMap;
	}

	@SuppressWarnings("unchecked")
	protected Integer addTestCaseToTestPlan(
		Integer testProjectId, 
		Integer testPlanId, 
		Integer testCaseId, 
		Integer version, 
		Integer platformId, 
		Integer order, 
		Integer urgency
	) 
	throws TestLinkAPIException
	{
		Integer featureId = 0;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			
			executionData.put(TestLinkParams.testProjectId.toString(), testProjectId);
			executionData.put(TestLinkParams.testPlanId.toString(), testPlanId);
			executionData.put(TestLinkParams.testCaseId.toString(), testCaseId);
			executionData.put(TestLinkParams.version.toString(), version);
			executionData.put(TestLinkParams.platformId.toString(), platformId);
			executionData.put(TestLinkParams.order.toString(), order);
			executionData.put(TestLinkParams.urgency.toString(), testCaseId);
			
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.addTestCaseToTestPlan.toString(), executionData);
			Map<String, Object> responseMap = (Map<String, Object>)response;
			
			featureId = Util.getInteger(responseMap, TestLinkResponseParams.featureId.toString());
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error adding test case to test plan: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return featureId;
	}

	/**
	 * @param testSuiteId
	 * @param deep
	 * @param details
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected TestCase[] getTestCasesForTestSuite(
		Integer testSuiteId,
		Boolean deep, 
		String details)
	throws TestLinkAPIException
	{
		
		TestCase[] testCases = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testSuiteId.toString(), testSuiteId);
			executionData.put(TestLinkParams.deep.toString(), deep);
			executionData.put(TestLinkParams.details.toString(), details);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestCasesForTestSuite.toString(), executionData);
			Object[] responseArray = (Object[])response;
			
			testCases = new TestCase[ responseArray.length ];
			
			for (int i = 0; i < responseArray.length; i++) 
			{
				Map<String, Object> responseMap = (Map<String, Object>)responseArray[i];
				testCases[i] = Util.getTestCase( responseMap );
			}
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving test cases for test suite: " + xmlrpcex.getMessage(), xmlrpcex);
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
	@SuppressWarnings({ "unchecked"})
	protected TestCase[] getTestCasesForTestPlan(
		Integer testPlanId,
		List<Integer> testCasesIds, 
		Integer buildId,
		List<Integer> keywordsIds, 
		String keywords, 
		Boolean executed,
		List<Integer> assignedTo, 
		String executeStatus,
		ExecutionType executionType, 
		Boolean getStepInfo)
	throws TestLinkAPIException
	{
		TestCase[] testCases = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testPlanId.toString(), testPlanId);
			executionData.put(TestLinkParams.testCaseId.toString(), testCasesIds);
			executionData.put(TestLinkParams.buildId.toString(), buildId);
			executionData.put(TestLinkParams.keywordId.toString(), keywordsIds);
			executionData.put(TestLinkParams.keywords.toString(), keywords); 
			executionData.put(TestLinkParams.executed.toString(), executed);
			executionData.put(TestLinkParams.assignedTo.toString(), assignedTo);
			executionData.put(TestLinkParams.executeStatus.toString(), Util.getStringValueOrNull( executeStatus ));
			executionData.put(TestLinkParams.executionType.toString(), Util.getStringValueOrNull( executionType ) );
			executionData.put(TestLinkParams.getStepInfo.toString(), getStepInfo);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestCasesForTestPlan.toString(), executionData);
			
			if ( response instanceof String )
			{
				throw new TestLinkAPIException( "The test plan you requested does not contain Test Cases." );
			}
			
			Map<String, Object> responseMap = (Map<String, Object>)response;
			Set<Entry<String, Object>> entrySet = responseMap.entrySet();
			testCases = new TestCase[ entrySet.size() ];
			int index = 0;
			for (Entry<String, Object> entry : entrySet) 
			{
				String key = entry.getKey();
				Map<String, Object> testCaseMap = null;
				
				if(entry.getValue() instanceof Object[])
				{
					Object[] responseArray = (Object[])entry.getValue();
					testCaseMap = (Map<String, Object>) responseArray[0];
				}
				else if(entry.getValue() instanceof  Map<?, ?>)
				{
					testCaseMap = (Map<String, Object>) entry.getValue();
					if ( testCaseMap.size() > 0 )
					{
						Set<String> keys = testCaseMap.keySet();
						Object o = testCaseMap.get(keys.iterator().next());
						if ( o instanceof Map<?, ?>)
						{
							testCaseMap = (Map<String, Object>)o;
						}
					}
				}
				testCaseMap.put(TestLinkResponseParams.id.toString(), key);
				testCases[index] = Util.getTestCase( testCaseMap );
				index += 1;
			}
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving test cases for test plan: " + xmlrpcex.getMessage(), xmlrpcex);
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
								   Integer testCaseExternalId, 
								   Integer version) 
	throws TestLinkAPIException
	{
		TestCase testCase = null;
		
		try 
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			
			executionData.put(TestLinkParams.testCaseId.toString(), testCaseId);
			executionData.put(TestLinkParams.testCaseExternalId.toString(), testCaseExternalId);
			executionData.put(TestLinkParams.version.toString(), version);
			
			Object response = this.executeXmlRpcCall( TestLinkMethods.getTestCase.toString(), executionData );
			
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			
			testCase = Util.getTestCase( responseMap );
		}
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException( "Error getting test case info : " + xmlrpcex.getMessage(), xmlrpcex );
		}
		
		return testCase;
	}
	
	
	/**
	 * 
	 * @param devKey
	 * @param testCaseName
	 * @param testSuiteName
	 * @param testProjectName
	 * @param testCasePathName
	 * @return
	 * @throws TestLinkAPIException
	 */
	@SuppressWarnings("unchecked")
	protected Integer getTestCaseIDByName(
			String testCaseName, 
			String testSuiteName, 
			String testProjectName, 
			String testCasePathName
	)
	throws TestLinkAPIException
	{
		Integer testCaseID = null;
		
		try {
			Map<String, Object> executionData = new HashMap<String, Object>();
			
			executionData.put(TestLinkParams.testCaseName.toString(), testCaseName);
			executionData.put(TestLinkParams.testSuiteName.toString(), testSuiteName);
			executionData.put(TestLinkParams.testProjectName.toString(), testProjectName);
			executionData.put(TestLinkParams.testCasePathName.toString(), testCasePathName);
			
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestCaseIDByName.toString(), executionData);
			
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			
			testCaseID = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
		} 
		catch (XmlRpcException xmlrpcex ) 
		{
			throw new TestLinkAPIException( "Error getting test case ID : " + xmlrpcex.getMessage(), xmlrpcex );
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
	@SuppressWarnings("unchecked")
	protected Attachment uploadTestCaseAttachment( 
		Integer testCaseId,
		String title, 
		String description, 
		String fileName, 
		String fileType,
		String content 
	) 
	throws TestLinkAPIException
	{
		Attachment attachment = null;
		
		Integer id = 0;
		
		attachment = new Attachment(
			id, 
			testCaseId, 
			TestLinkTables.nodesHierarchy.toString(), 
			title, 
			description, 
			fileName, 
			null, 
			fileType, 
			content
		);
		
		try
		{
			Map<String, Object> executionData = Util.getTestCaseAttachmentMap(attachment);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.uploadTestCaseAttachment.toString(), executionData);
			Map<String, Object> responseMap = (Map<String, Object>)response;
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			attachment.setId(id);
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error uploading attachment for test case: " + xmlrpcex.getMessage(), xmlrpcex);
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
	protected Attachment[] getTestCaseAttachments( 
		Integer testCaseId,
		Integer testCaseExternalId )
	throws TestLinkAPIException
	{
		Attachment[] attachments = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testCaseId.toString(), testCaseId);
			executionData.put(TestLinkParams.testCaseExternalId.toString(), testCaseExternalId);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestCaseAttachments.toString(), executionData);
			if ( response instanceof Map<?, ?> )
			{
				Map<String, Object> responseMap = (Map<String, Object>)response;
				Set<Entry<String, Object>> entrySet = responseMap.entrySet();
				
				attachments = new Attachment[ entrySet.size() ];
						
				int index = 0;
				for (Entry<String, Object> entry : entrySet) 
				{
					String key = entry.getKey();
					Map<String, Object> attachmentMap = (Map<String, Object>) entry.getValue();
					attachmentMap.put(TestLinkResponseParams.id.toString(), key);
					attachments[index] = Util.getAttachment( attachmentMap );
					index += 1;
				}
			} 
			else
			{
				attachments = new Attachment[0];
			}
			
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving test case's attachments: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return attachments;
	}

	
	@SuppressWarnings("unchecked")
	protected Attachment uploadExecutionAttachment(
			Integer executionId, 
			String title, 
			String description, 
			String fileName, 
			String fileType, 
			String content
	) 
	throws TestLinkAPIException
	{
		Attachment attachment = null;
		
		Integer id = 0;
		
		attachment = new Attachment(
			id, 
			executionId, 
			TestLinkTables.executions.toString(), 
			title, 
			description, 
			fileName, 
			null, 
			fileType, 
			content
		);
		
		
		try
		{
			Map<String, Object> executionData = Util.getExecutionAttachmentMap(attachment);
			
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.uploadExecutionAttachment.toString(), executionData);
			Map<String, Object> responseMap = (Map<String, Object>)response;
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			attachment.setId(id);
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error uploading attachment for execution: " + xmlrpcex.getMessage(), xmlrpcex);
		}		
		
		return attachment;
	}
	
	
	/**
	 * @param executionId
	 * @return
	 * @te
	 */
	protected void deleteExecution( Integer executionId ) 
	throws TestLinkAPIException
	{
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.executionId.toString(), executionId);
			this.executeXmlRpcCall(
					TestLinkMethods.deleteExecution.toString(), executionData);
			// the error verification routine is called inside super.executeXml...
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error deleting execution: " + xmlrpcex.getMessage(), xmlrpcex);
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
	protected ReportTCResultResponse reportTCResult(
		Integer testCaseId, 
		Integer testCaseExternalId,
		Integer testPlanId, 
		ExecutionStatus status, 
		Integer buildId,
		String buildName, 
		String notes, 
		Boolean guess, 
		String bugId,
		Integer platformId, 
		String platformName,
		Map<String, String> customFields, // TODO: change for a list of custom fields. After implementing method getTestCaseCustomFieldDesignValue this entities properties will become much more clear
		Boolean overwrite
	) 
	throws TestLinkAPIException
	{
		
		ReportTCResultResponse reportTCResultResponse = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testCaseId.toString(), testCaseId);
			executionData.put(TestLinkParams.testCaseExternalId.toString(), testCaseExternalId);
			executionData.put(TestLinkParams.testPlanId.toString(), testPlanId);
			executionData.put(TestLinkParams.status.toString(), status.toString());
			executionData.put(TestLinkParams.buildId.toString(), buildId);
			executionData.put(TestLinkParams.buildName.toString(), buildName);
			executionData.put(TestLinkParams.notes.toString(), notes);
			executionData.put(TestLinkParams.guess.toString(), guess);
			executionData.put(TestLinkParams.bugId.toString(), bugId);
			executionData.put(TestLinkParams.platformId.toString(), platformId);
			executionData.put(TestLinkParams.platformName.toString(), platformName);
			executionData.put(TestLinkParams.customFields.toString(), customFields);
			executionData.put(TestLinkParams.overwrite.toString(), overwrite);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.reportTCResult.toString(), executionData);
			// the error verification routine is called inside super.executeXml...
			if ( response instanceof Object[] )
			{
				Object[] responseArray = (Object[])response;
				Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
				
				reportTCResultResponse = Util.getReportTCResultResponse( responseMap );
			}
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error reporting TC result: " + xmlrpcex.getMessage(), xmlrpcex);
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
	@SuppressWarnings("unchecked")
	protected CustomField getTestCaseCustomFieldDesignValue( 
		Integer testCaseId,
		Integer testCaseExternalId, 
		Integer versionNumber,
		Integer testProjectId, 
		String customFieldName, 
		ResponseDetails details
	) 
	throws TestLinkAPIException
	{
		CustomField customField = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put( TestLinkParams.testCaseId.toString(), testCaseId );
			executionData.put( TestLinkParams.testCaseExternalId.toString(), testCaseExternalId );
			executionData.put( TestLinkParams.version.toString(), versionNumber );
			executionData.put( TestLinkParams.testProjectId.toString(), testProjectId );
			executionData.put( TestLinkParams.customFieldName.toString(), customFieldName );
			executionData.put( TestLinkParams.details.toString(), Util.getStringValueOrNull( details ) );
			
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestCaseCustomFieldDesignValue.toString(), executionData);
			
			if ( response instanceof String )
			{
				customField = new CustomField();
				customField.setValue( response.toString() );
			}
			else if ( response instanceof Map<?, ?> )
			{
				Map<String, Object> responseMap = (Map<String, Object>)response;
				customField = Util.getCustomField( responseMap );
			}
				
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving test case custom field value: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return customField;
	}
}
