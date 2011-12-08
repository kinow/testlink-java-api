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

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.model.Execution;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.Requirement;
import br.eti.kinoshita.testlinkjavaapi.model.ResponseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStepAction;
import br.eti.kinoshita.testlinkjavaapi.model.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;

/**
 * <p>TestLink API class.</p>
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
public class TestLinkAPI
{

	/**
	 * TestLink URL.
	 */
	private URL url;
	
	/**
	 * TestLink Developer Key.
	 */
	private String devKey;
	
	private final TestProjectService testProjectService;
	private final TestPlanService testPlanService;
	private final MiscService miscService;
	private final TestCaseService testCaseService;
	private final TestSuiteService testSuiteService;
	private final BuildService buildService;
	private final RequirementService requirementService;
	private final ReqSpecService reqSpecService;
	
	private static final Logger LOG = LoggerFactory.getLogger(TestLinkAPI.class);
	
	/**
	 * XML-RPC client.
	 */
	private XmlRpcClient xmlRpcClient;
	
	// Constants for properties
	private static final String XMLRPC_BASIC_ENCODING 			= "xmlrpc.basicEncoding";
	private static final String XMLRPC_BASIC_PASSWORD 			= "xmlrpc.basicPassword";
	private static final String XMLRPC_BASIC_USERNAME 			= "xmlrpc.basicUsername";
	private static final String XMLRPC_CONNECTION_TIMEOUT 		= "xmlrpc.connectionTimeout";
	private static final String XMLRPC_CONTENT_LENGTH_OPTIONAL 	= "xmlrpc.contentLengthOptional";
	private static final String XMLRPC_ENABLED_FOR_EXCEPTIONS 	= "xmlrpc.enabledForExceptions";
	private static final String XMLRPC_ENCODING 				= "xmlrpc.encoding";
	private static final String XMLRPC_GZIP_COMPRESSION 		= "xmlrpc.gzipCompression";
	private static final String XMLRPC_GZIP_REQUESTING 			= "xmlrpc.gzipRequesting";
	private static final String XMLRPC_REPLY_TIMEOUT 			= "xmlrpc.replyTimeout";
	private static final String XMLRPC_USER_AGENT 				= "xmlrpc.userAgent";
	
	/**
	 * <p>Constructor with parameters.</p>
	 * 
	 * <p>Instantiates TestLink services. It also checks the devKey and 
	 * throws a TestLinkAPIException if it is invalid.</p>
	 * 
	 * @param url The URL to set.
	 * @param devKey The Developer Key to set.
	 * @throws TestLinkAPIException 
	 * @since 1.0
	 */
	public TestLinkAPI( 
			URL url, 
			String devKey )
	throws TestLinkAPIException
	{
		this.url = url;
		this.devKey = devKey;
		
		this.xmlRpcClient = new XmlRpcClient();
		
		// application configuration
		final CompositeConfiguration appConfig = this.createApplicationConfiguration();
		// XML-RPC client specific configuration, using the application configuration
		final XmlRpcClientConfigImpl config = this.createXmlRpcClientConfiguration( url, appConfig );
		this.xmlRpcClient.setConfig(config);
		
		this.testProjectService = new TestProjectService( xmlRpcClient, devKey );
		this.testPlanService = new TestPlanService( xmlRpcClient, devKey );
		this.miscService = new MiscService( xmlRpcClient, devKey );
		this.testCaseService = new TestCaseService( xmlRpcClient, devKey );
		this.testSuiteService = new TestSuiteService( xmlRpcClient, devKey );
		this.buildService = new BuildService( xmlRpcClient, devKey );
		this.requirementService = new RequirementService( xmlRpcClient, devKey );
		this.reqSpecService = new ReqSpecService( xmlRpcClient, devKey );
		
		this.miscService.checkDevKey(devKey);
	}
	
	/**
	 * Creates application composite configuration.
	 * 
	 * @return Application composite configuration.
	 */
	private CompositeConfiguration createApplicationConfiguration()
	{
		CompositeConfiguration appConfig = new CompositeConfiguration();
		appConfig.addConfiguration(new SystemConfiguration());
		try
		{
			appConfig.addConfiguration(new PropertiesConfiguration("testlinkjavaapi.properties"));
		}
		catch ( ConfigurationException ce )
		{
			this.debug( ce);
		}
		return appConfig;
	}
	
	/**
	 * Creates XML-RPC client configuration.
	 * 
	 * By default enabled for extensions is always true. 
	 * 
	 * @param url Application URL.
	 * @param appConfig Application composite configuration.
	 * @return XML-RPC client configuration.
	 */
	private XmlRpcClientConfigImpl createXmlRpcClientConfiguration( URL url, CompositeConfiguration appConfig )
	{
		final XmlRpcClientConfigImpl xmlRpcClientConfig = new XmlRpcClientConfigImpl();
		
		xmlRpcClientConfig.setServerURL( url );
		xmlRpcClientConfig.setEnabledForExtensions( true );
		
		xmlRpcClientConfig.setBasicEncoding( appConfig.getString(XMLRPC_BASIC_ENCODING) );
		xmlRpcClientConfig.setBasicPassword( appConfig.getString(XMLRPC_BASIC_PASSWORD) );
		xmlRpcClientConfig.setBasicUserName( appConfig.getString(XMLRPC_BASIC_USERNAME) );
		
		try
		{
			xmlRpcClientConfig.setConnectionTimeout( appConfig.getInt(XMLRPC_CONNECTION_TIMEOUT) );
		}
		catch ( ConversionException ce )
		{
			this.debug( ce );
		}
		catch ( NoSuchElementException nsee )
		{
			this.debug( nsee );
		}
		
		try
		{
			xmlRpcClientConfig.setContentLengthOptional( appConfig.getBoolean(XMLRPC_CONTENT_LENGTH_OPTIONAL) );
		}
		catch ( ConversionException ce )
		{
			this.debug( ce );
		}
		catch ( NoSuchElementException nsee )
		{
			this.debug( nsee );
		}
		
		try
		{
			xmlRpcClientConfig.setEnabledForExceptions( appConfig.getBoolean(XMLRPC_ENABLED_FOR_EXCEPTIONS) );
		}
		catch ( ConversionException ce )
		{
			this.debug( ce );
		}
		catch ( NoSuchElementException nsee )
		{
			this.debug( nsee );
		}
		
		xmlRpcClientConfig.setEncoding( appConfig.getString(XMLRPC_ENCODING) );
		
		try
		{
			xmlRpcClientConfig.setGzipCompressing( appConfig.getBoolean(XMLRPC_GZIP_COMPRESSION) );
		}
		catch ( ConversionException ce )
		{
			this.debug( ce );
		}
		catch ( NoSuchElementException nsee )
		{
			this.debug( nsee );
		}
		
		try
		{
			xmlRpcClientConfig.setGzipRequesting( appConfig.getBoolean(XMLRPC_GZIP_REQUESTING) );
		}
		catch ( ConversionException ce )
		{
			this.debug( ce);
		}
		catch ( NoSuchElementException nsee )
		{
			this.debug( nsee );
		}
		
		try
		{
			xmlRpcClientConfig.setReplyTimeout( appConfig.getInt(XMLRPC_REPLY_TIMEOUT) );
		}
		catch ( ConversionException ce )
		{
			this.debug( ce );
		}
		catch ( NoSuchElementException nsee )
		{
			this.debug( nsee );
		}
		
		xmlRpcClientConfig.setUserAgent( appConfig.getString(XMLRPC_USER_AGENT) );
		
		return xmlRpcClientConfig;
	}

	/**
	 * Logs a throwable object in debug level. Before outputting the message 
	 * it checks if debug is enabled or not. If it is not enabled the message 
	 * is not displayed and the String object is not created/concatenated, etc.
	 * 
	 * @param throwable Throwable object.
	 */
	private void debug( Throwable throwable )
	{
		if ( LOG.isDebugEnabled() )
		{
			LOG.debug(throwable.getMessage(), throwable );
		}
	}

	/**
	 * @return XML-RPC Client.
	 */
	public XmlRpcClient getXmlRpcClient()
	{
		return this.xmlRpcClient;
	}
	
	/* ------- Utility methods ------- */

	/**
	 * @return the url
	 */
	public URL getUrl()
	{
		return url;
	}

	/**
	 * @return the devKey
	 */
	public String getDevKey()
	{
		return devKey;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TestLinkAPI [url=" + url + ", devKey=********]";
	}
	
	/* ------- TL API methods ------- */
	
	/* XX Misc operations XX */
	
	/**
	 * Checks developer key.
	 * 
	 * @param devKey Developer Key.
	 * @return <code>true</code> if devKey is valid, <code>false</code> otherwise.
	 * @throws TestLinkAPIException
	 * @since 1.0
	 */
	public Boolean checkDevKey(String devKey) 
	throws TestLinkAPIException
	{
		return this.miscService.checkDevKey(devKey);
	}
	
	/**
	 * Checks if the given user exists.
	 * @param user
	 * @return <code>true</code> if the user exists, <code>false</code> otherwise.
	 * @throws TestLinkAPIException
	 */
	public Boolean doesUserExist(String user) 
	throws TestLinkAPIException
	{
		return this.miscService.doesUserExist( user );
	}
	
	/**
	 * ping method is an alias for sayHello.
	 * 
	 * @return Hello message.
	 * @throws TestLinkAPIException
	 */
	public String ping() 
	throws TestLinkAPIException
	{
		return this.sayHello();
	}
	
	/**
	 * Says hello to the user.
	 * 
	 * @return Hello message.
	 * @throws TestLinkAPIException
	 */
	public String sayHello() 
	throws TestLinkAPIException
	{
		return this.miscService.sayHello();
	}
	
	/**
	 * Displays information about TL.
	 * 
	 * @return About text.
	 * @throws TestLinkAPIException
	 */
	public String about()  
	throws TestLinkAPIException
	{
		return this.miscService.about();
	}
	
	/**
	 * 
	 * @param testMode
	 * @return Test mode.
	 * @throws TestLinkAPIException
	 */
	public Boolean setTestMode(Boolean testMode) 
	throws TestLinkAPIException
	{
		return this.miscService.setTestMode( testMode );
	}
	
	/**
	 * 
	 * @param str
	 * @return String repeated.
	 * @throws TestLinkAPIException
	 */
	public String repeat(String str)
	throws TestLinkAPIException
	{
		return this.miscService.repeat(str);
	}
	
	/**
	 * Uploads an attachment linking it to a DB table.
	 * @param fkId
	 * @param fkTable
	 * @param title
	 * @param description
	 * @param fileName
	 * @param fileType
	 * @param content
	 * @return Attachment.
	 * @throws TestLinkAPIException
	 */
	public Attachment uploadAttachment(
		Integer fkId, 
		String fkTable, 
		String title, 
		String description, 
		String fileName, 
		String fileType, 
		String content
	)
	throws TestLinkAPIException
	{
		return this.miscService.uploadAttachment(
			fkId, 
			fkTable, 
			title, 
			description, 
			fileName, 
			fileType, 
			content
		);
	}
	
	/**
	 * Retrieves the full path of a node. Given a nodeId of, let's say, 10, 
	 * that is a test case. The return array will consist of Name Of Project, 
	 * Name of Suite, Name of Test Case.
	 * 
	 * @param nodeId
	 * @return Array of name of nodes
	 * @throws TestLinkAPIException
	 */
	public String[] getFullPath(Integer nodeId) 
	throws TestLinkAPIException
	{
		return this.miscService.getFullPath( nodeId );
	}
	
	/**
	 * Retrieves last execution result of a Test Case.
	 * 
	 * @param testPlanId
	 * @param testCaseId
	 * @param testCaseExternalId
	 * @return Last Execution.
	 * @throws TestLinkAPIException
	 */
	public Execution getLastExecutionResult(
		Integer testPlanId,
		Integer testCaseId, 
		Integer testCaseExternalId
		
	) 
	throws TestLinkAPIException
	{
		return this.miscService.getLastExecutionResult(testPlanId, testCaseId, testCaseExternalId);
	}
	
	/* XX Test Project operations XX */
	
	/**
	 * Creates a Test Project. 
	 * 
	 * @param testProjectName
	 * @param testProjectPrefix
	 * @param notes
	 * @param enableRequirements
	 * @param enableTestPriority
	 * @param enableAutomation
	 * @param enableInventory
	 * @param isActive
	 * @param isPublic
	 * @return Test Project object.
	 * @throws TestLinkAPIException
	 * @since 1.0
	 */
	public TestProject createTestProject(
		String testProjectName, 
		String testProjectPrefix, 
		String notes, 
		Boolean enableRequirements, 
		Boolean enableTestPriority, 
		Boolean enableAutomation, 
		Boolean enableInventory, 
		Boolean isActive, 
		Boolean isPublic
	) 
	throws TestLinkAPIException
	{
		return this.testProjectService.createTestProject(
				testProjectName, 
				testProjectPrefix, 
				notes, 
				enableRequirements, 
				enableTestPriority, 
				enableAutomation, 
				enableInventory, 
				isActive, 
				isPublic);
	}
	
	/**
	 * Retrieves a Test Project by its name.
	 * 
	 * @param projectName Test Project name.
	 * @return Test Project with given name or null if not found.
	 * @throws TestLinkAPIException
	 * @since 1.0
	 */
	public TestProject getTestProjectByName(String projectName) 
	throws TestLinkAPIException
	{
		return this.testProjectService.getTestProjectByName(projectName);
	}
	
	/**
	 * Retrieves all Test Projects from TestLink.
	 *  
	 * @return an array of Test Projects.
	 * @throws TestLinkAPIException
	 * @since 1.0
	 */
	public TestProject[] getProjects() 
	throws TestLinkAPIException
	{
		return this.testProjectService.getProjects();
	}
	
	
	/**
	 * Retrieves an array of Test Plans associated to a Test Project.
	 * 
	 * @param projectId Test Project Id.
	 * @return Array of Test Plans.
	 * @throws TestLinkAPIException
	 */
	public TestPlan[] getProjectTestPlans(Integer projectId) 
	throws TestLinkAPIException
	{
		return this.testProjectService.getProjectTestPlans(projectId);
	}
	
	/**
	 * Uploads an attachment to a Test Project.
	 * 
	 * @param testProjectId
	 * @param title
	 * @param description
	 * @param fileName
	 * @param fileType
	 * @param content
	 * @return Attachment.
	 * @throws TestLinkAPIException
	 */
	public Attachment uploadTestProjectAttachment(
		Integer testProjectId, 
		String title, 
		String description, 
		String fileName, 
		String fileType, 
		String content
	) 
	throws TestLinkAPIException
	{
		return this.testProjectService.uploadTestProjectAttachment(
			testProjectId, title, description, 
			fileName, fileType, content
		);
	}
	
	/* XX Test Plan operations XX */
	
	/**
	 * Creates a Test Plan.
	 * 
	 * @param planName Test Plan name.
	 * @param projectName Test Project name.
	 * @param notes Test Plan notes.
	 * @param isActive
	 * @param isPublic
	 * @throws TestLinkAPIException
	 * @since 1.0
	 */
	public TestPlan createTestPlan(
		String planName, 
		String projectName, 
		String notes, 
		Boolean isActive, 
		Boolean isPublic
	) 
	throws TestLinkAPIException
	{
		return this.testPlanService.createTestPlan(
				planName, 
				projectName, 
				notes, 
				isActive, 
				isPublic);
	}
	
	/**
	 * Retrieves a Test Plan by its name.
	 *
	 * @param planName Test Plan name.
	 * @param projectName Test Project name.
	 * @return Test Plan.
	 * @throws TestLinkAPIException
	 * @since 1.0
	 */
	public TestPlan getTestPlanByName(String planName, String projectName)
	throws TestLinkAPIException 
	{
		return this.testPlanService.getTestPlanByName(planName, projectName);
	}
	
	/**
	 * Retrieves Platforms of a Test Plan.
	 * 
	 * @param planId Test Plan Id.
	 * @return Platforms.
	 * @throws TestLinkAPIException
	 */
	public Platform[] getTestPlanPlatforms( Integer planId )
	throws TestLinkAPIException
	{
		return this.testPlanService.getTestPlanPlatforms( planId );
	}
	
	public Map<String, Object> getTotalsForTestPlan( Integer testPlanId ) 
	throws TestLinkAPIException
	{
		return this.testPlanService.getTotalsForTestPlan( testPlanId );
	}
	
	/* XX Build operations XX */
	
	/**
	 * Creates a Build. 
	 * 
	 * @param testPlanId
	 * @param buildName
	 * @param buildNotes
	 * @return Created Build.
	 * @throws TestLinkAPIException
	 */
	public Build createBuild( Integer testPlanId, String buildName, String buildNotes )
	throws TestLinkAPIException 
	{
		return this.buildService.createBuild(testPlanId, buildName, buildNotes);
	}
	
	/**
	 * Retrieves an Array of Builds for a given Test Plan.
	 * 
	 * @param testPlanId Test Plan ID.
	 * @return Array of Builds.
	 * @throws TestLinkAPIException
	 */
	public Build[] getBuildsForTestPlan( Integer testPlanId )
	throws TestLinkAPIException
	{
		return this.buildService.getBuildsForTestPlan( testPlanId );
	}
	
	/**
	 * Retrieves the latest Build for a given Test Plan.
	 * 
	 * @param testPlanId Test Plan ID.
	 * @return Build.
	 * @throws TestLinkAPIException
	 */
	public Build getLatestBuildForTestPlan( Integer testPlanId ) 
	throws TestLinkAPIException
	{
		return this.buildService.getLatestBuildForTestPlan( testPlanId );
	}
	
	/* XX Test Suite operations XX */
	
	public TestSuite createTestSuite(
		Integer testProjectId, 
		String name, 
		String details, 
		Integer parentId, 
		Integer order, 
		Boolean checkDuplicatedName, 
		String actionOnDuplicatedName
	) 
	throws TestLinkAPIException 
	{
		return this.testSuiteService.createTestSuite(
			testProjectId, 
			name, 
			details, 
			parentId, 
			order, 
			checkDuplicatedName, 
			actionOnDuplicatedName
		);
	}
	
	/**
	 * Retrieves Test Suites for given Ids.
	 * 
	 * @param testSuiteIds List of Test Suite Ids.
	 * @return Array of Test Suites.
	 * @throws TestLinkAPIException
	 */
	public TestSuite[] getTestSuiteByID(List<Integer> testSuiteIds)
	throws TestLinkAPIException
	{
		return this.testSuiteService.getTestSuiteByID(testSuiteIds);
	}
	
	/**
	 * Uploads an attachment to a Test Suite.
	 * 
	 * @param testSuiteId
	 * @param title
	 * @param description
	 * @param fileName
	 * @param fileType
	 * @param content
	 * @return Attachment.
	 * @throws TestLinkAPIException
	 */
	public Attachment uploadTestSuiteAttachment( 
		Integer testSuiteId, 
		String title, 
		String description, 
		String fileName, 
		String fileType, 
		String content
	)
	throws TestLinkAPIException 
	{
		return this.testSuiteService.uploadTestSuiteAttachment(
			testSuiteId, title, description, 
			fileName, fileType, content
		);
	}
	
	/**
	 * 
	 * @param testPlanId
	 * @return Array of Test Suites of Test Plan.
	 * @throws TestLinkAPIException
	 */
	public TestSuite[] getTestSuitesForTestPlan(Integer testPlanId)
	throws TestLinkAPIException
	{
		return this.testSuiteService.getTestSuitesForTestPlan(testPlanId);
	}
	
	
	/**
	 * Get list of TestSuites which are DIRECT children of a given TestSuite
	 * 
	 * @param testSuiteId
	 * @throws TestLinkAPIException
	 */
	public TestSuite[] getTestSuitesForTestSuite(Integer testSuiteId)
	throws TestLinkAPIException
	{
		return this.testSuiteService.getTestSuitesForTestSuite(testSuiteId);
	}
	
	
	/**
	 * Get set of test suites AT TOP LEVEL of tree on a Test Project
	 * 
	 * @param testProjectId
	 * @throws TestLinkAPIException
	 */
	public TestSuite[] getFirstLevelTestSuitesForTestProject(Integer testProjectId)
	throws TestLinkAPIException
	{
		return this.testSuiteService.getFirstLevelTestSuitesForTestProject(testProjectId);
	}
	
	
	/* XX Test Case operations XX */
	
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
	 * @return TestCase.
	 * @throws TestLinkAPIException
	 */
	public TestCase createTestCase( 
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
		String actionOnDuplicatedName
	) 
	throws TestLinkAPIException 
	{
		return this.testCaseService.createTestCase(
			testCaseName, 
			testSuiteId, 
			testProjectId, 
			authorLogin, 
			summary, 
			steps, 
			preconditions, 
			importance, 
			execution, 
			order, 
			internalId, 
			checkDuplicatedName, 
			actionOnDuplicatedName
		);
	}
	
	/**
	 * Create, Update or Push a list of TestCaseSteps in a Test Case.
	 * 
	 * @param testCaseExternalId
	 * @param version
	 * @param testCaseSteps
	 * @return a Map with results.
	 * @throws TestLinkAPIException
	 */
	public Map<String, Object> createTestCaseSteps(
		String testCaseExternalId,
		Integer version,
		TestCaseStepAction action,
		List<TestCaseStep> testCaseSteps
	)
	throws TestLinkAPIException
	{
		return this.testCaseService.createTestCaseSteps(
			testCaseExternalId,
			version,
			TestCaseStepAction.Create,
			testCaseSteps
		);
	}
	
	/**
	 * Add a list of TestCaseSteps in a Test Case.
	 * A convenient method to add steps, internally call to the
	 * {@link #createTestCaseSteps(Integer, Integer, TestCaseStepAction, List)} method with 'Create' action.
	 * 
	 * @param testCaseExternalId
	 * @param version
	 * @param testCaseSteps
	 * @return a Map with results.
	 * @throws TestLinkAPIException
	 */
	public Map<String, Object> addTestCaseSteps(
		String testCaseExternalId,
		Integer version,
		List<TestCaseStep> testCaseSteps
	)
	throws TestLinkAPIException
	{
		return createTestCaseSteps(
			testCaseExternalId,
			version,
			TestCaseStepAction.Create,
			testCaseSteps
		);
	}
	
	/**
	 * Update (override) a list of TestCaseSteps in a Test Case.
	 * A convenient method to update steps, internally call to the
	 * {@link #createTestCaseSteps(Integer, Integer, TestCaseStepAction, List)} method with 'Update' action.
	 * 
	 * @param testCaseExternalId
	 * @param version
	 * @param testCaseSteps
	 * @return a Map with results.
	 * @throws TestLinkAPIException
	 */
	public Map<String, Object> updateTestCaseSteps(
		String testCaseExternalId,
		Integer version,
		List<TestCaseStep> testCaseSteps
	)
	throws TestLinkAPIException
	{
		return createTestCaseSteps(
			testCaseExternalId,
			version,
			TestCaseStepAction.Update,
			testCaseSteps
		);
	}
	
	/**
	 * Push (insert and move down previous steps) a list of
	 * TestCaseSteps in a TestCase.
	 * A convenient method to push steps, internally call to the
	 * {@link #createTestCaseSteps(Integer, Integer, TestCaseStepAction, List)} method with 'Push' action.
	 * 
	 * @param testCaseExternalId
	 * @param version
	 * @param testCaseSteps
	 * @return a Map with results.
	 * @throws TestLinkAPIException
	 */
	public Map<String, Object> pushTestCaseSteps(
		String testCaseExternalId,
		Integer version,
		List<TestCaseStep> testCaseSteps
	)
	throws TestLinkAPIException
	{
		return createTestCaseSteps(
			testCaseExternalId,
			version,
			TestCaseStepAction.Push,
			testCaseSteps
		);
	}
	
	/**
	 * Delete a list if TestCaseSteps from a Test Case.
	 * 
	 * @param testCaseExternalId
	 * @param version
	 * @param testCaseSteps
	 * @return a Map with results.
	 * @throws TestLinkAPIException
	 */
	public Map<String, Object> deleteTestCaseSteps(
		String testCaseExternalId,
		Integer version,
		List<TestCaseStep> testCaseSteps
	)
	throws TestLinkAPIException
	{
		return this.testCaseService.deleteTestCaseSteps(
			testCaseExternalId,
			version,
			testCaseSteps
		);
	}
	
	/**
	 * Adds a Test Case to a Test Plan.
	 * @param testProjectId
	 * @param testPlanId
	 * @param testCaseId
	 * @param version
	 * @param platformId
	 * @param order
	 * @param urgency
	 * @return Feature ID.
	 * @throws TestLinkAPIException
	 */
	public Integer addTestCaseToTestPlan(
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
		return this.testCaseService.addTestCaseToTestPlan(
			testProjectId, 
			testPlanId, 
			testCaseId, 
			version, 
			platformId, 
			order, 
			urgency);
	}
	
	/**
	 * Retrieves Test Cases for a Test Suite.
	 * 
	 * @param testSuiteId
	 * @param deep
	 * @param details
	 * @return Array of Test Cases of the Test Suite.
	 * @throws TestLinkAPIException
	 */
	public TestCase[] getTestCasesForTestSuite(
		Integer testSuiteId, 
		Boolean deep, 
		String details
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.getTestCasesForTestSuite(testSuiteId, deep, details);
	}
	

	/**
	 * Get a test case
	 * @param testCaseId
	 * @param testCaseExternalId
	 * @param version
	 * @return Test Case.
	 * @throws TestLinkAPIException
	 */
	public TestCase getTestCase(
			Integer testCaseId, 
			Integer testCaseExternalId, 
			Integer version
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.getTestCase(testCaseId, testCaseExternalId, version);
	}
	
	/**
	 * Get a Test Case using the full external id, composed by the prefix and
	 * the external id: prefix-externalId
	 * 
	 * @param fullTestCaseExternalId Full external id: prefix-externalId
	 * @param version
	 * @return Test Case.
	 * @throws TestLinkAPIException
	 */
	public TestCase getTestCaseByExternalId(
			String fullTestCaseExternalId, 
			Integer version
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.getTestCaseByExternalId(fullTestCaseExternalId, version);
	}
	
	/**
	 * Retrieves Test Cases for Test Plans.
	 * 
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
	 * @return Array of Test Cases of the Test Plan.
	 * @throws TestLinkAPIException
	 */
	public TestCase[] getTestCasesForTestPlan(
		Integer testPlanId, 
		List<Integer> testCasesIds, 
		Integer buildId, 
		List<Integer> keywordsIds, 
		String keywords, // , separated e.g.: database,performance
		Boolean executed, 
		List<Integer> assignedTo, 
		String executeStatus, // , separated e.g.: p,n,f
		ExecutionType executionType, 
		Boolean getStepInfo
		) 
	throws TestLinkAPIException
	{
		return this.testCaseService.getTestCasesForTestPlan(
			testPlanId, 
			testCasesIds, 
			buildId, 
			keywordsIds, 
			keywords, 
			executed, 
			assignedTo, 
			executeStatus, 
			executionType, 
			getStepInfo
		);
	}
	
	/**
	 * Get a test case ID by a test case Name
	 * @param testCaseName
	 * @param testSuiteName
	 * @param testProjectName
	 * @param testCasePathName
	 * @return Test Case ID.
	 * @throws TestLinkAPIException
	 */
	public Integer getTestCaseIDByName(
		    String testCaseName, 
		    String testSuiteName, 
		    String testProjectName, 
		    String testCasePathName
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.getTestCaseIDByName(testCaseName, testSuiteName, testProjectName, testCasePathName);
	}

	/**
	 * Uploads an attachment to a Test Case.
	 * 
	 * @param testCaseId
	 * @param title
	 * @param description
	 * @param fileName
	 * @param fileType
	 * @param content
	 * @return Attachment.
	 * @throws TestLinkAPIException
	 */
	public Attachment uploadTestCaseAttachment(
		Integer testCaseId, 
		String title, 
		String description, 
		String fileName, 
		String fileType, 
		String content
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.uploadTestCaseAttachment
		(
			testCaseId, 
			title, 
			description, 
			fileName, 
			fileType, 
			content
		);
	}
	
	/**
	 * Return an array of attachments of a Test Case.
	 * 
	 * @param testCaseId
	 * @param testCaseExternalId
	 * @return Array of Attachments.
	 * @throws TestLinkAPIException
	 */
	public Attachment[] getTestCaseAttachments(
		Integer testCaseId, 
		Integer testCaseExternalId
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.getTestCaseAttachments(testCaseId,testCaseExternalId);
	}
	
	
	
	public Attachment uploadExecutionAttachment(
		Integer executionId, 
		String title, 
		String description, 
		String fileName, 
		String fileType, 
		String content
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.uploadExecutionAttachment(
				executionId, title, description, 
			fileName, fileType, content
		);
	}
	
	/**
	 * Deletes an execution. 
	 * 
	 * @param executionId Execution Id.
	 * @throws TestLinkAPIException
	 */
	public void deleteExecution(Integer executionId)
	throws TestLinkAPIException
	{
		this.testCaseService.deleteExecution( executionId );
	}
	
	/**
	 * Reports a Test Case result.
	 * 
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
	 * @throws TestLinkAPIException
	 */
	public ReportTCResultResponse reportTCResult(
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
		Map<String, String> customFields,
		Boolean overwrite
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.reportTCResult(
				testCaseId, 
				testCaseExternalId, 
				testPlanId, 
				status, 
				buildId, 
				buildName, 
				notes, 
				guess, 
				bugId, 
				platformId, 
				platformName, 
				customFields,
				overwrite
		);
	}
	
	/**
	 * Reports a Test Case result.
	 * 
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
	 * @throws TestLinkAPIException
	 */
	public ReportTCResultResponse setTestCaseExecutionResult(
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
		Map<String, String> customFields,
		Boolean overwrite
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.reportTCResult(
				testCaseId, 
				testCaseExternalId, 
				testPlanId, 
				status, 
				buildId, 
				buildName, 
				notes, 
				guess, 
				bugId, 
				platformId, 
				platformName, 
				customFields,
				overwrite
		);
	}
	
	/**
	 * Retrieves list of Custom Fields for a Test Case.
	 * @param testCaseId
	 * @param testCaseExternalId
	 * @param versionNumber
	 * @param testProjectId
	 * @param customFieldName
	 * @return Custom Field.
	 * @throws TestLinkAPIException
	 */
	public CustomField getTestCaseCustomFieldDesignValue(
		Integer testCaseId, 
		Integer testCaseExternalId, 
		Integer versionNumber, 
		Integer testProjectId, 
		String customFieldName, 
		ResponseDetails details
	) 
	throws TestLinkAPIException
	{
		return this.testCaseService.getTestCaseCustomFieldDesignValue( 
			testCaseId, 
			testCaseExternalId, 
			versionNumber, 
			testProjectId, 
			customFieldName, 
			details
		);
	}
	
	/* XX Requirements Specification operations XX */
	
	/**
	 * Uploads an attachment to a Requirement Specification.
	 * 
	 * @param reqSpecId
	 * @param title
	 * @param description
	 * @param fileName
	 * @param fileType
	 * @param content
	 * @return Attachment.
	 * @throws TestLinkAPIException
	 */
	public Attachment uploadRequirementSpecificationAttachment(
		Integer reqSpecId, 
		String title, 
		String description, 
		String fileName, 
		String fileType, 
		String content
	) 
	throws TestLinkAPIException
	{
		return this.reqSpecService.uploadRequirementSpecificationAttachment(
			reqSpecId, 
			title, 
			description, 
			fileName, 
			fileType, 
			content
		);
	}
	
	/* XX Requirements operations XX */
	
	/**
	 * Uploads an attachment to a Requirement. 
	 * 
	 * @param requirementId
	 * @param title
	 * @param description
	 * @param fileName
	 * @param fileType
	 * @param content
	 * @return Attachment.
	 * @throws TestLinkAPIException
	 */
	public Attachment uploadRequirementAttachment(
		Integer requirementId, 
		String title, 
		String description, 
		String fileName, 
		String fileType, 
		String content
	) 
	throws TestLinkAPIException
	{
		return this.requirementService.uploadRequirementAttachment(
			requirementId, 
			title, 
			description, 
			fileName, 
			fileType, 
			content
		);
	}
	
	/**
	 * Assign a requirements to a Test Case.
	 * @param testCaseId
	 * @param testProjectId
	 * @param requirements
	 * @throws TestLinkAPIException
	 */
	public void assignRequirements(
		Integer testCaseId, 
		Integer testProjectId, 
		List<Requirement> requirements
	) 
	throws TestLinkAPIException
	{
		this.requirementService.assignRequirements(
				testCaseId, testProjectId, requirements);
	}	
	
	/**
	 * Executes a XML-RPC call. Use this method to talk with TestLink in a 
	 * lower level.
	 * 
	 * @param methodName Name of the method.
	 * @param executionData Execution data map.
	 * @return Object returned from the server.
	 * @throws XmlRpcException
	 * @throws TestLinkAPIException
	 */
	public Object executeXmlRpcCall(
			String methodName, 
			Map<String, Object> executionData ) 
	throws XmlRpcException, TestLinkAPIException
	{
		return miscService.executeXmlRpcCall(methodName, executionData);
	}
	
}
