/*
 * The MIT License
 *
 * Copyright (c) 2010 Bruno P. Kinoshita http://www.kinoshita.eti.br
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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConversionException;
import org.apache.commons.configuration2.io.FileLocator;
import org.apache.commons.configuration2.io.FileLocatorUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcSun15HttpTransportFactory;

import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.ResponseDetails;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStepAction;
import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.model.Execution;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.Requirement;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStepResult;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.model.User;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * TestLink API class.
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestLinkAPI {

    /**
     * TestLink URL.
     */
    private final URL url;

    /**
     * TestLink Developer Key.
     */
    private final String devKey;

    private final TestProjectService testProjectService;
    private final TestPlanService testPlanService;
    private final MiscService miscService;
    private final TestCaseService testCaseService;
    private final TestSuiteService testSuiteService;
    private final BuildService buildService;
    private final RequirementService requirementService;
    private final ReqSpecService reqSpecService;

    private static final Logger LOG = Logger.getLogger(TestLinkAPI.class.getName());

    /**
     * XML-RPC client.
     */
    private final XmlRpcClient xmlRpcClient;

    // Constants for properties
    private static final String XMLRPC_BASIC_ENCODING = "xmlrpc.basicEncoding";
    private static final String XMLRPC_BASIC_PASSWORD = "xmlrpc.basicPassword";
    private static final String XMLRPC_BASIC_USERNAME = "xmlrpc.basicUsername";
    private static final String XMLRPC_CONNECTION_TIMEOUT = "xmlrpc.connectionTimeout";
    private static final String XMLRPC_CONTENT_LENGTH_OPTIONAL = "xmlrpc.contentLengthOptional";
    private static final String XMLRPC_ENABLED_FOR_EXCEPTIONS = "xmlrpc.enabledForExceptions";
    private static final String XMLRPC_ENCODING = "xmlrpc.encoding";
    private static final String XMLRPC_GZIP_COMPRESSION = "xmlrpc.gzipCompression";
    private static final String XMLRPC_GZIP_REQUESTING = "xmlrpc.gzipRequesting";
    private static final String XMLRPC_REPLY_TIMEOUT = "xmlrpc.replyTimeout";
    private static final String XMLRPC_USER_AGENT = "xmlrpc.userAgent";

    /**
     * Constructor with parameters.
     *
     * <p>
     * Instantiates TestLink services. It also checks the devKey and throws a TestLinkAPIException if it is invalid.
     * </p>
     *
     * @param url The URL to set.
     * @param devKey The Developer Key to set.
     * @throws TestLinkAPIException if the service returns an error
     * @since 1.0
     */
    public TestLinkAPI(URL url, String devKey) throws TestLinkAPIException {
        this.url = url;
        this.devKey = devKey;

        this.xmlRpcClient = new XmlRpcClient();

        // application configuration
        final CompositeConfiguration appConfig = this.createApplicationConfiguration();
        // XML-RPC client specific configuration, using the application
        // configuration
        final XmlRpcClientConfigImpl config = this.createXmlRpcClientConfiguration(url, appConfig);
        this.xmlRpcClient.setConfig(config);

        this.testProjectService = new TestProjectService(xmlRpcClient, devKey);
        this.testPlanService = new TestPlanService(xmlRpcClient, devKey);
        this.miscService = new MiscService(xmlRpcClient, devKey);
        this.testCaseService = new TestCaseService(xmlRpcClient, devKey);
        this.testSuiteService = new TestSuiteService(xmlRpcClient, devKey);
        this.buildService = new BuildService(xmlRpcClient, devKey);
        this.requirementService = new RequirementService(xmlRpcClient, devKey);
        this.reqSpecService = new ReqSpecService(xmlRpcClient, devKey);

        this.miscService.checkDevKey(devKey);
    }

    /**
     * Constructor with parameters.
     *
     * @param url The URL to set.
     * @param devKey The Developer Key to set.
     * @param proxyHost The IP address of the proxy server
     * @param proxyPort The proxy server port
     * @throws TestLinkAPIException if the service returns an error
     * @since 1.9.20
     */
    public TestLinkAPI(URL url, String devKey, String proxyHost, int proxyPort) throws TestLinkAPIException {
        this.url = url;
        this.devKey = devKey;
        this.xmlRpcClient = new XmlRpcClient();

        //proxy configuration
        XmlRpcSun15HttpTransportFactory fac = new XmlRpcSun15HttpTransportFactory(xmlRpcClient);
        fac.setProxy(proxyHost, proxyPort);
        xmlRpcClient.setTransportFactory(fac);

        // application configuration
        final CompositeConfiguration appConfig = this.createApplicationConfiguration();
        // XML-RPC client specific configuration, using the application configuration
        final XmlRpcClientConfigImpl config = this.createXmlRpcClientConfiguration(url, appConfig);
        this.xmlRpcClient.setConfig(config);
        this.testProjectService = new TestProjectService(xmlRpcClient, devKey);
        this.testPlanService = new TestPlanService(xmlRpcClient, devKey);
        this.miscService = new MiscService(xmlRpcClient, devKey);
        this.testCaseService = new TestCaseService(xmlRpcClient, devKey);
        this.testSuiteService = new TestSuiteService(xmlRpcClient, devKey);
        this.buildService = new BuildService(xmlRpcClient, devKey);
        this.requirementService = new RequirementService(xmlRpcClient, devKey);
        this.reqSpecService = new ReqSpecService(xmlRpcClient, devKey);
        this.miscService.checkDevKey(devKey);
    }

    /**
     * Creates application composite configuration.
     *
     * @return Application composite configuration.
     */
    private CompositeConfiguration createApplicationConfiguration() {
        CompositeConfiguration cc = new CompositeConfiguration();

        SystemConfiguration systemConfiguration = new SystemConfiguration();
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
        propertiesConfiguration.setThrowExceptionOnMissing(true);
        propertiesConfiguration.setListDelimiterHandler(new DefaultListDelimiterHandler(';'));
        propertiesConfiguration.setIncludesAllowed(false);
        FileLocator locator = FileLocatorUtils.fileLocator()
                     .fileName("testlinkjavaapi.propertiesxml")
                     .create();
        propertiesConfiguration.initFileLocator(locator);

        cc.addConfiguration(systemConfiguration);
        cc.addConfiguration(propertiesConfiguration);

        return cc;
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
    private XmlRpcClientConfigImpl createXmlRpcClientConfiguration(URL url, CompositeConfiguration appConfig) {
        final XmlRpcClientConfigImpl xmlRpcClientConfig = new XmlRpcClientConfigImpl();

        xmlRpcClientConfig.setServerURL(url);
        xmlRpcClientConfig.setEnabledForExtensions(true);

        xmlRpcClientConfig.setBasicEncoding(appConfig.getString(XMLRPC_BASIC_ENCODING));
        xmlRpcClientConfig.setBasicPassword(appConfig.getString(XMLRPC_BASIC_PASSWORD));
        xmlRpcClientConfig.setBasicUserName(appConfig.getString(XMLRPC_BASIC_USERNAME));

        try {
            xmlRpcClientConfig.setConnectionTimeout(appConfig.getInt(XMLRPC_CONNECTION_TIMEOUT));
        } catch (ConversionException | NoSuchElementException ce) {
            this.debug(ce);
        }

        try {
            xmlRpcClientConfig.setContentLengthOptional(appConfig.getBoolean(XMLRPC_CONTENT_LENGTH_OPTIONAL));
        } catch (ConversionException | NoSuchElementException ce) {
            this.debug(ce);
        }

        try {
            xmlRpcClientConfig.setEnabledForExceptions(appConfig.getBoolean(XMLRPC_ENABLED_FOR_EXCEPTIONS));
        } catch (ConversionException | NoSuchElementException ce) {
            this.debug(ce);
        }

        xmlRpcClientConfig.setEncoding(appConfig.getString(XMLRPC_ENCODING));

        try {
            xmlRpcClientConfig.setGzipCompressing(appConfig.getBoolean(XMLRPC_GZIP_COMPRESSION));
        } catch (ConversionException | NoSuchElementException ce) {
            this.debug(ce);
        }

        try {
            xmlRpcClientConfig.setGzipRequesting(appConfig.getBoolean(XMLRPC_GZIP_REQUESTING));
        } catch (ConversionException | NoSuchElementException ce) {
            this.debug(ce);
        }

        try {
            xmlRpcClientConfig.setReplyTimeout(appConfig.getInt(XMLRPC_REPLY_TIMEOUT));
        } catch (ConversionException | NoSuchElementException ce) {
            this.debug(ce);
        }

        xmlRpcClientConfig.setUserAgent(appConfig.getString(XMLRPC_USER_AGENT));

        return xmlRpcClientConfig;
    }

    /**
     * Logs a throwable object in debug level. Before outputting the message it checks if debug is enabled or not. If it
     * is not enabled the message is not displayed and the String object is not created/concatenated, etc.
     *
     * @param throwable Throwable object.
     */
    private void debug(Throwable throwable) {
        if (LOG.isLoggable(Level.FINEST)) {
            LOG.log(Level.FINEST, throwable.getMessage(), throwable);
        }
    }

    /**
     * @return XML-RPC Client.
     */
    public XmlRpcClient getXmlRpcClient() {
        return this.xmlRpcClient;
    }

    /* ------- Utility methods ------- */

    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }

    /**
     * @return the devKey
     */
    public String getDevKey() {
        return devKey;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TestLinkAPI [url=" + url + ", devKey=********]";
    }

    /* ------- TL API methods ------- */

    /* XX Misc operations XX */

    /**
     * Checks developer key.
     *
     * @param devKey Developer Key.
     * @return <code>true</code> if devKey is valid, <code>false</code> otherwise.
     * @throws TestLinkAPIException if the service returns an error
     * @since 1.0
     */
    public Boolean checkDevKey(String devKey) throws TestLinkAPIException {
        return this.miscService.checkDevKey(devKey);
    }

    /**
     * Checks if the given user exists.
     *
     * @param user user user name
     * @return <code>true</code> if the user exists, <code>false</code> otherwise.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Boolean doesUserExist(String user) throws TestLinkAPIException {
        return this.miscService.doesUserExist(user);
    }

    /**
     * Get user by login.
     *
     * @param login user login
     * @return user the user
     * @throws TestLinkAPIException if the service returns an error
     */
    public User getUserByLogin(String login) throws TestLinkAPIException {
        return this.miscService.getUserByLogin(login);
    }

    /**
     * ping method is an alias for sayHello.
     *
     * @return Hello message
     * @throws TestLinkAPIException if the service returns an error
     */
    public String ping() throws TestLinkAPIException {
        return this.sayHello();
    }

    /**
     * Says hello to the user.
     *
     * @return Hello message
     * @throws TestLinkAPIException if the service returns an error
     */
    public String sayHello() throws TestLinkAPIException {
        return this.miscService.sayHello();
    }

    /**
     * Displays information about TL.
     *
     * @return About text
     * @throws TestLinkAPIException if the service returns an error
     */
    public String about() throws TestLinkAPIException {
        return this.miscService.about();
    }

    /**
     * Set the test mode.
     *
     * @param testMode test mode
     * @return Test mode
     * @throws TestLinkAPIException if the service returns an error
     */
    public Boolean setTestMode(Boolean testMode) throws TestLinkAPIException {
        return this.miscService.setTestMode(testMode);
    }

    /**
     * Repeat the text.
     *
     * @param str text
     * @return String repeated
     * @throws TestLinkAPIException if the service returns an error
     */
    public String repeat(String str) throws TestLinkAPIException {
        return this.miscService.repeat(str);
    }

    /**
     * Upload an attachment linking it to a DB table.
     *
     * @param fkId FK
     * @param fkTable FK table name
     * @param title title
     * @param description description
     * @param fileName file name
     * @param fileType file type
     * @param content content
     * @return Attachment
     * @throws TestLinkAPIException if the service returns an error
     */
    public Attachment uploadAttachment(Integer fkId, String fkTable, String title, String description, String fileName,
            String fileType, String content) throws TestLinkAPIException {
        return this.miscService.uploadAttachment(fkId, fkTable, title, description, fileName, fileType, content);
    }

    /**
     * Retrieves the full path of a node. Given a nodeId of, let's say, 10, that is a test case. The return array will
     * consist of Name Of Project, Name of Suite, Name of Test Case.
     *
     * @param nodeId node ID
     * @return Array of name of nodes
     * @throws TestLinkAPIException if the service returns an error
     */
    public String[] getFullPath(Integer nodeId) throws TestLinkAPIException {
        return this.miscService.getFullPath(nodeId);
    }

    /**
     * Retrieves last execution result of a Test Case.
     *
     * @param testPlanId test plan ID
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param platformId platform ID
     * @param platformName platform name
     * @param buildId build ID
     * @param buildName build name
     * @param options array of options
     * @return last Execution
     * @throws TestLinkAPIException if the service returns an error
     */
    public Execution getLastExecutionResult(Integer testPlanId, Integer testCaseId, String testCaseExternalId,
            Integer platformId, String platformName, Integer buildId, String buildName, Integer options
    ) throws TestLinkAPIException {
        return this.miscService.getLastExecutionResult(testPlanId, testCaseId, testCaseExternalId,
                platformId, platformName, buildId, buildName, options);
    }

    /* XX Test Project operations XX */

    /**
     * Creates a Test Project.
     *
     * @param testProjectName test project name
     * @param testProjectPrefix test project prefix
     * @param notes notes
     * @param enableRequirements flag to enable requirements
     * @param enableTestPriority flag to enable test priority
     * @param enableAutomation flag to enable automation
     * @param enableInventory flag to enable inventory
     * @param isActive whether the project is active or not
     * @param isPublic whether the project is public or not
     * @return Test Project object
     * @throws TestLinkAPIException if the service returns an error
     * @since 1.0
     */
    public TestProject createTestProject(String testProjectName, String testProjectPrefix, String notes,
            Boolean enableRequirements, Boolean enableTestPriority, Boolean enableAutomation, Boolean enableInventory,
            Boolean isActive, Boolean isPublic) throws TestLinkAPIException {
        return this.testProjectService.createTestProject(testProjectName, testProjectPrefix, notes, enableRequirements,
                enableTestPriority, enableAutomation, enableInventory, isActive, isPublic);
    }

    /**
     * Retrieves a Test Project by its name.
     *
     * @param projectName Test Project name.
     * @return Test Project with given name or null if not found.
     * @throws TestLinkAPIException if the service returns an error
     * @since 1.0
     */
    public TestProject getTestProjectByName(String projectName) throws TestLinkAPIException {
        return this.testProjectService.getTestProjectByName(projectName);
    }

    /**
     * Retrieves the platforms of a test project.
     *
     * @param projectId test project ID
     * @return platforms array
     * @throws TestLinkAPIException if the service returns an error if an error occurs when retrieving the platforms
     * @since 1.9.6-0
     */
    public Platform[] getProjectPlatforms(Integer projectId) throws TestLinkAPIException {
        return this.testProjectService.getProjectPlatforms(projectId);
    }

    /**
     * Retrieves all Test Projects from TestLink.
     *
     * @return an array of Test Projects.
     * @throws TestLinkAPIException if the service returns an error
     * @since 1.0
     */
    public TestProject[] getProjects() throws TestLinkAPIException {
        return this.testProjectService.getProjects();
    }

    /**
     * Retrieves an array of Test Plans associated to a Test Project.
     *
     * @param projectId Test Project ID
     * @return Array of Test Plans.
     * @throws TestLinkAPIException if the service returns an error
     */
    public TestPlan[] getProjectTestPlans(Integer projectId) throws TestLinkAPIException {
        return this.testProjectService.getProjectTestPlans(projectId);
    }

    /**
     * Uploads an attachment to a Test Project.
     *
     * @param testProjectId test project ID test project ID
     * @param title title
     * @param description description
     * @param fileName file name
     * @param fileType file type
     * @param content content
     * @return Attachment
     * @throws TestLinkAPIException if the service returns an error
     */
    public Attachment uploadTestProjectAttachment(Integer testProjectId, String title, String description,
            String fileName, String fileType, String content) throws TestLinkAPIException {
        return this.testProjectService.uploadTestProjectAttachment(testProjectId, title, description, fileName,
                fileType, content);
    }

    /* XX Test Plan operations XX */

    /**
     * Creates a Test Plan.
     *
     * @param planName Test Plan name.
     * @param projectName Test Project name.
     * @param notes Test Plan notes.
     * @param isActive whether the project is active or not
     * @param isPublic whether the project is public or not
     * @throws TestLinkAPIException if the service returns an error
     * @since 1.0
     * @return Test plan
     */
    public TestPlan createTestPlan(String planName, String projectName, String notes, Boolean isActive,
            Boolean isPublic) throws TestLinkAPIException {
        return this.testPlanService.createTestPlan(planName, projectName, notes, isActive, isPublic);
    }

    /**
     * Retrieves a Test Plan by its name.
     *
     * @param planName Test Plan name.
     * @param projectName Test Project name.
     * @return Test Plan.
     * @throws TestLinkAPIException if the service returns an error
     * @since 1.0
     */
    public TestPlan getTestPlanByName(String planName, String projectName) throws TestLinkAPIException {
        return this.testPlanService.getTestPlanByName(planName, projectName);
    }

    /**
     * Retrieves Platforms of a Test Plan.
     *
     * @param planId Test Plan Id.
     * @return Platforms
     * @throws TestLinkAPIException if the service returns an error
     */
    public Platform[] getTestPlanPlatforms(Integer planId) throws TestLinkAPIException {
        return this.testPlanService.getTestPlanPlatforms(planId);
    }

    /**
     * Gets stats for test plan.
     *
     * @param testPlanId test plan ID
     * @return statistics on test plan
     * @throws TestLinkAPIException if the service returns an error
     */
    public Map<String, Object> getTotalsForTestPlan(Integer testPlanId) throws TestLinkAPIException {
        return this.testPlanService.getTotalsForTestPlan(testPlanId);
    }

    /**
     * Removes a platform from a test plan.
     *
     * @param testProjectId test project ID test project ID
     * @param testPlanId test plan ID
     * @param platformName platform name
     * @return status message
     * @throws TestLinkAPIException if the service returns an error
     */
    public Map<String, Object> removePlatformFromTestPlan(Integer testProjectId, Integer testPlanId,
            String platformName) throws TestLinkAPIException {
        return this.testPlanService.removePlatformFromTestPlan(testProjectId, testPlanId, platformName);
    }

    /**
     * Adds a platform to a test plan.
     *
     * @param testProjectId test project ID test project ID
     * @param testPlanId test plan ID
     * @param platformName platform name
     * @return status message
     * @throws TestLinkAPIException if the service returns an error
     */
    public Map<String, Object> addPlatformToTestPlan(Integer testProjectId, Integer testPlanId, String platformName)
            throws TestLinkAPIException {
        return this.testPlanService.addPlatformToTestPlan(testProjectId, testPlanId, platformName);
    }

    /* XX Build operations XX */

    /**
     * Update build custom fields.
     *
     * @since 1.9.16-0
     * @param buildId Build ID
     * @param testProjectId Test Project ID
     * @param testPlanId test plan ID
     * @param customFields Custom Fields name, value pairs
     * @return Response XML-RPC Response
     * @throws TestLinkAPIException if the service returns as error
     */
    public Map<String, Object> updateBuildCustomFields(Integer buildId, Integer testProjectId, Integer testPlanId, Map<String, String> customFields) throws TestLinkAPIException {
        return this.buildService.updateBuildCustomFields(buildId, testProjectId, testPlanId, customFields);
    }

    /**
     * Creates a Build.
     *
     * @param testPlanId test plan ID
     * @param buildName build name
     * @param buildNotes build notes
     * @return Created Build
     * @throws TestLinkAPIException if the service returns an error
     */
    public Build createBuild(Integer testPlanId, String buildName, String buildNotes) throws TestLinkAPIException {
        return this.buildService.createBuild(testPlanId, buildName, buildNotes);
    }

    /**
     * Retrieves an Array of Builds for a given Test Plan.
     *
     * @param testPlanId Test Plan ID.
     * @return Array of Builds.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Build[] getBuildsForTestPlan(Integer testPlanId) throws TestLinkAPIException {
        return this.buildService.getBuildsForTestPlan(testPlanId);
    }

    /**
     * Retrieves the latest Build for a given Test Plan.
     *
     * @param testPlanId Test Plan ID.
     * @return Build.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Build getLatestBuildForTestPlan(Integer testPlanId) throws TestLinkAPIException {
        return this.buildService.getLatestBuildForTestPlan(testPlanId);
    }

    /**
     * Gets the exec counters by build.
     *
     * @param testPlanId test plan ID
     * @return server response map
     */
    public Map<String, Object> getExecCountersByBuild(Integer testPlanId) {
        return this.buildService.getExecCountersByBuild(testPlanId);
    }

    /* XX Test Suite operations XX */

    /**
     * Create a test suite.
     *
     * @param testProjectId Test project ID
     * @param name Test suite name
     * @param details details
     * @param parentId Parent ID
     * @param order order
     * @param checkDuplicatedName whether to check for duplicated names or not
     * @param actionOnDuplicatedName action when there is a duplicated name
     * @return {@link TestSuite}
     * @throws TestLinkAPIException if it fails to create a test suite
     */
    public TestSuite createTestSuite(Integer testProjectId, String name, String details, Integer parentId,
            Integer order, Boolean checkDuplicatedName, ActionOnDuplicate actionOnDuplicatedName)
            throws TestLinkAPIException {
        return this.testSuiteService.createTestSuite(testProjectId, name, details, parentId, order, checkDuplicatedName,
                actionOnDuplicatedName);
    }

    /**
     * Retrieves Test Suites for given Ids.
     *
     * @param testSuiteIds List of Test Suite Ids.
     * @return Array of Test Suites.
     * @throws TestLinkAPIException if the service returns an error
     */
    public TestSuite[] getTestSuiteByID(List<Integer> testSuiteIds) throws TestLinkAPIException {
        return this.testSuiteService.getTestSuiteByID(testSuiteIds);
    }

    /**
     * Uploads an attachment to a Test Suite.
     *
     * @param testSuiteId test suite ID
     * @param title title
     * @param description description
     * @param fileName file name
     * @param fileType file type
     * @param content content
     * @return Attachment.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Attachment uploadTestSuiteAttachment(Integer testSuiteId, String title, String description, String fileName,
            String fileType, String content) throws TestLinkAPIException {
        return this.testSuiteService.uploadTestSuiteAttachment(testSuiteId, title, description, fileName, fileType,
                content);
    }

    /**
     *
     * @param testPlanId test plan ID
     * @return Array of Test Suites of Test Plan.
     * @throws TestLinkAPIException if the service returns an error
     */
    public TestSuite[] getTestSuitesForTestPlan(Integer testPlanId) throws TestLinkAPIException {
        return this.testSuiteService.getTestSuitesForTestPlan(testPlanId);
    }

    /**
     * Get list of TestSuites which are DIRECT children of a given TestSuite
     *
     * @param testSuiteId test suite ID
     * @throws TestLinkAPIException if the service returns an error
     * @return an array of test suites
     */
    public TestSuite[] getTestSuitesForTestSuite(Integer testSuiteId) throws TestLinkAPIException {
        return this.testSuiteService.getTestSuitesForTestSuite(testSuiteId);
    }

    /**
     * Get set of test suites AT TOP LEVEL of tree on a Test Project
     *
     * @param testProjectId test project ID
     * @throws TestLinkAPIException if the service returns an error
     * @return an array of test suites
     */
    public TestSuite[] getFirstLevelTestSuitesForTestProject(Integer testProjectId) throws TestLinkAPIException {
        return this.testSuiteService.getFirstLevelTestSuitesForTestProject(testProjectId);
    }

    /* XX Test Case operations XX */

    /**
     * Creates a Test Case.
     *
     * @param testCaseName test case name
     * @param testSuiteId test suite ID
     * @param testProjectId test project ID
     * @param authorLogin author login
     * @param summary summary
     * @param steps steps
     * @param preconditions preconditions
     * @param status status
     * @param importance importance
     * @param execution execution
     * @param order order
     * @param internalId internal ID
     * @param checkDuplicatedName flag to check for duplicated name
     * @param actionOnDuplicatedName what to do when a duplicated name is found
     * @return TestCase.
     * @throws TestLinkAPIException if the service returns an error
     */
    public TestCase createTestCase(String testCaseName, Integer testSuiteId, Integer testProjectId, String authorLogin,
            String summary, List<TestCaseStep> steps, String preconditions, TestCaseStatus status,
            TestImportance importance, ExecutionType execution, Integer order, Integer internalId,
            Boolean checkDuplicatedName, ActionOnDuplicate actionOnDuplicatedName) throws TestLinkAPIException {
        return this.testCaseService.createTestCase(testCaseName, testSuiteId, testProjectId, authorLogin, summary,
                steps, preconditions, status, importance, execution, order, internalId, checkDuplicatedName,
                actionOnDuplicatedName);
    }

    /**
     * Update an already existing test case with the data of the given test case as a new version.
     *
     * @param tc new version of the test case
     * @return response map
     * @throws TestLinkAPIException if the service returns an error if updating the test case fails
     */
    public Map<String, Object> updateTestCase(TestCase tc) throws TestLinkAPIException {
        return this.testCaseService.updateTestCase(tc);
    }

    /**
     * Create, Update or Push a list of TestCaseSteps in a Test Case.
     *
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param version version
     * @param action action
     * @param testCaseSteps test case steps
     * @return a Map with results.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Map<String, Object> createTestCaseSteps(Integer testCaseId, String testCaseExternalId, Integer version,
            TestCaseStepAction action, List<TestCaseStep> testCaseSteps) throws TestLinkAPIException {
        return this.testCaseService.createTestCaseSteps(testCaseId, testCaseExternalId, version, action, testCaseSteps);
    }

    /**
     * Delete a list if TestCaseSteps from a Test Case.
     *
     * @param testCaseExternalId test case external ID
     * @param version version
     * @param testCaseSteps test case steps
     * @return a Map with results.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Map<String, Object> deleteTestCaseSteps(String testCaseExternalId, Integer version,
            List<TestCaseStep> testCaseSteps) throws TestLinkAPIException {
        return this.testCaseService.deleteTestCaseSteps(testCaseExternalId, version, testCaseSteps);
    }

    /**
     * Adds a Test Case to a Test Plan.
     *
     * @param testProjectId test project ID
     * @param testPlanId test plan ID
     * @param testCaseId test case ID
     * @param version version
     * @param platformId platform ID
     * @param order order
     * @param urgency urgency
     * @return Feature ID.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Integer addTestCaseToTestPlan(Integer testProjectId, Integer testPlanId, Integer testCaseId, Integer version,
            Integer platformId, Integer order, Integer urgency) throws TestLinkAPIException {
        return this.testCaseService.addTestCaseToTestPlan(testProjectId, testPlanId, testCaseId, version, platformId,
                order, urgency);
    }

    /**
     * Retrieves Test Cases for a Test Suite.
     *
     * @param testSuiteId test suite ID
     * @param deep flag for depth
     * @param detail test case details requested
     * @return Array of Test Cases of the Test Suite.
     * @throws TestLinkAPIException if the service returns an error
     */
    public TestCase[] getTestCasesForTestSuite(Integer testSuiteId, Boolean deep, TestCaseDetails detail)
            throws TestLinkAPIException {
        return this.testCaseService.getTestCasesForTestSuite(testSuiteId, deep, detail);
    }

    /**
     * Get a test case
     *
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param version version
     * @return Test Case.
     * @throws TestLinkAPIException if the service returns an error
     */
    public TestCase getTestCase(Integer testCaseId, Integer testCaseExternalId, Integer version)
            throws TestLinkAPIException {
        return this.testCaseService.getTestCase(testCaseId, testCaseExternalId, version);
    }

    /**
     * Get a Test Case using the full external id, composed by the prefix and the external id: prefix-externalId
     *
     * @param fullTestCaseExternalId Full external id: prefix-externalId
     * @param version version
     * @return Test Case.
     * @throws TestLinkAPIException if the service returns an error
     */
    public TestCase getTestCaseByExternalId(String fullTestCaseExternalId, Integer version)
            throws TestLinkAPIException {
        return this.testCaseService.getTestCaseByExternalId(fullTestCaseExternalId, version);
    }

    /**
     * Retrieves Test Cases for Test Plans.
     *
     * @param testPlanId test plan ID
     * @param testCasesIds test case ID's
     * @param buildId build ID
     * @param keywordsIds keyword ID's
     * @param keywords keywords
     * @param executed flag for executed or not
     * @param assignedTo assignee
     * @param executeStatus execution status
     * @param executionType execution type
     * @param getStepInfo test case step info
     * @param detail test case details
     * @return Array of Test Cases of the Test Plan.
     * @throws TestLinkAPIException if the service returns an error
     */
    public TestCase[] getTestCasesForTestPlan(Integer testPlanId, List<Integer> testCasesIds, Integer buildId,
            List<Integer> keywordsIds, String keywords, // , separated e.g.:
                                                        // database,performance
            Boolean executed, List<Integer> assignedTo, String[] executeStatus, // ,
                                                                                // separated
                                                                                // e.g.:
                                                                                // p,n,f
            ExecutionType executionType, Boolean getStepInfo, TestCaseDetails detail) throws TestLinkAPIException {
        return this.testCaseService.getTestCasesForTestPlan(testPlanId, testCasesIds, buildId, keywordsIds, keywords,
                executed, assignedTo, executeStatus, executionType, getStepInfo, detail);
    }

    /**
     * Get a test case ID by a test case Name
     *
     * @param testCaseName test case name
     * @param testSuiteName test suite name
     * @param testProjectName test project name
     * @param testCasePathName test case path name
     * @return Test Case ID.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Integer getTestCaseIDByName(String testCaseName, String testSuiteName, String testProjectName,
            String testCasePathName) throws TestLinkAPIException {
        return this.testCaseService.getTestCaseIDByName(testCaseName, testSuiteName, testProjectName, testCasePathName);
    }

    /**
     * Uploads an attachment to a Test Case.
     *
     * @param testCaseId test case ID
     * @param title title
     * @param description description
     * @param fileName file name
     * @param fileType file type
     * @param content content
     * @param version test case version
     * @return Attachment.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Attachment uploadTestCaseAttachment(Integer testCaseId, String title, String description, String fileName,
            String fileType, String content, int version) throws TestLinkAPIException {
        return this.testCaseService.uploadTestCaseAttachment(testCaseId, title, description, fileName, fileType,
                content, version);
    }

    /**
     * Return an array of attachments of a Test Case.
     *
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param testCaseVersion test case version
     * @return Array of Attachments
     * @throws TestLinkAPIException if the service returns an error
     */
    public Attachment[] getTestCaseAttachments(Integer testCaseId, Integer testCaseVersion, Integer testCaseExternalId)
            throws TestLinkAPIException {
        return this.testCaseService.getTestCaseAttachments(testCaseId, testCaseVersion, testCaseExternalId);
    }

    /**
     * Return an array of attachments of a Test Suite.
     *
     * @param testSuiteId test suite ID
     * @return Array of Attachments.
     * @throws TestLinkAPIException if service return error
     */
    public Attachment[] getTestSuiteAttachments(Integer testSuiteId)
            throws TestLinkAPIException {
        return this.testSuiteService.getTestSuiteAttachments(testSuiteId);
    }

    /**
     * Upload an execution attachment.
     *
     * @param executionId execution ID
     * @param title title
     * @param description description
     * @param fileName file name
     * @param fileType file type
     * @param content content
     * @return attachment
     * @throws TestLinkAPIException if the service returns an error
     */
    public Attachment uploadExecutionAttachment(Integer executionId, String title, String description, String fileName,
            String fileType, String content) throws TestLinkAPIException {
        return this.testCaseService.uploadExecutionAttachment(executionId, title, description, fileName, fileType,
                content);
    }

    /**
     * Deletes an execution.
     *
     * @param executionId Execution Id.
     * @throws TestLinkAPIException if the service returns an error
     */
    public void deleteExecution(Integer executionId) throws TestLinkAPIException {
        this.testCaseService.deleteExecution(executionId);
    }

    /**
     * Reports a Test Case result.
     *
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param testPlanId test plan ID
     * @param status status
     * @param steps test steps results
     * @param buildId build ID
     * @param buildName build name
     * @param notes notes
     * @param executionDuration execution duration (minutes)
     * @param guess flag to guess other parameters or not
     * @param bugId bug ID
     * @param platformId platform ID
     * @param platformName platform name
     * @param customFields custom fields
     * @param overwrite flag to overwrite or not
     * @param user if present checks if user is a valid login
     * @param timestamp format YYYY-MM-DD HH:MM:SS (e.g. 2015-05-22 12:15:45)
     * @throws TestLinkAPIException if the service returns an error
     * @return report test case result server response
     */
    public ReportTCResultResponse reportTCResult(Integer testCaseId, Integer testCaseExternalId, Integer testPlanId,
            ExecutionStatus status, List<TestCaseStepResult> steps, Integer buildId, String buildName, String notes,
            Integer executionDuration, Boolean guess, String bugId, Integer platformId, String platformName,
            Map<String, String> customFields, Boolean overwrite, String user, String timestamp)
            throws TestLinkAPIException {
        return this.testCaseService.reportTCResult(testCaseId, testCaseExternalId, testPlanId, status, steps, buildId,
                buildName, notes, executionDuration, guess, bugId, platformId, platformName, customFields, overwrite,
                user, timestamp);
    }

    /**
     * Reports a Test Case result.
     *
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param testPlanId test plan ID
     * @param status status
     * @param steps test steps results
     * @param buildId build ID
     * @param buildName build name
     * @param notes notes
     * @param executionDuration execution duration (minutes)
     * @param guess flag to guess other parameters or not
     * @param bugId bug ID
     * @param platformId platform ID
     * @param platformName platform name
     * @param customFields custom fields
     * @param overwrite flag to overwrite or not
     * @param user if present checks if user is a valid login
     * @param timestamp format YYYY-MM-DD HH:MM:SS (e.g. 2015-05-22 12:15:45)
     * @return response
     * @throws TestLinkAPIException if the service returns an error
     */
    public ReportTCResultResponse setTestCaseExecutionResult(Integer testCaseId, Integer testCaseExternalId,
            Integer testPlanId, ExecutionStatus status, List<TestCaseStepResult> steps, Integer buildId,
            String buildName, String notes, Integer executionDuration, Boolean guess, String bugId, Integer platformId,
            String platformName, Map<String, String> customFields, Boolean overwrite, String user, String timestamp)
            throws TestLinkAPIException {
        return this.reportTCResult(testCaseId, testCaseExternalId, testPlanId, status, steps, buildId,
                buildName, notes, executionDuration, guess, bugId, platformId, platformName, customFields, overwrite,
                user, timestamp);
    }

    /**
     * Retrieves list of Custom Fields for a Test Case.
     *
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param versionNumber version number
     * @param testProjectId test project ID
     * @param customFieldName custom field name
     * @param details details
     * @return Custom Field.
     * @throws TestLinkAPIException if the service returns an error
     */
    public CustomField getTestCaseCustomFieldDesignValue(Integer testCaseId, Integer testCaseExternalId,
            Integer versionNumber, Integer testProjectId, String customFieldName, ResponseDetails details)
            throws TestLinkAPIException {
        return this.testCaseService.getTestCaseCustomFieldDesignValue(testCaseId, testCaseExternalId, versionNumber,
                testProjectId, customFieldName, details);
    }

    /**
     * Gets the test plan custom field value
     *
     * @param testPlanId test plan ID
     * @param testProjectId test project ID
     * @param customFieldName custom field name
     * @param details details
     * @return custom field
     * @throws TestLinkAPIException if the service returns an error
     */
    public CustomField getTestPlanCustomFieldDesignValue(Integer testPlanId, Integer testProjectId,
            String customFieldName, ResponseDetails details) throws TestLinkAPIException {
        return this.testPlanService.getTestPlanCustomFieldDesignValue(testPlanId, testProjectId, customFieldName,
                details);
    }

    /**
     * Gets the test case custom field value on test plan design scope.
     *
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param versionNumber version number
     * @param testProjectId test project ID
     * @param customFieldName custom field name
     * @param details details
     * @return custom field
     * @throws TestLinkAPIException if the service returns an error
     */
    public CustomField getTestCaseCustomFieldTestPlanDesignValue(Integer testCaseId, Integer testCaseExternalId,
            Integer versionNumber, Integer testProjectId, String customFieldName, ResponseDetails details)
            throws TestLinkAPIException {
        return this.testCaseService.getTestCaseCustomFieldTestPlanDesignValue(testCaseId, testCaseExternalId,
                versionNumber, testProjectId, customFieldName, details);
    }

    /**
     * Gets the test case custom field value on test execution scope.
     *
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param versionNumber version number
     * @param executionId execution ID
     * @param testPlanID test plan ID
     * @param testProjectId test project ID
     * @param customFieldName custom field name
     * @param details details
     * @return custom field
     * @throws TestLinkAPIException if the service returns an error
     */
    public CustomField getTestCaseCustomFieldExecutionValue(Integer testCaseId, Integer testCaseExternalId,
            Integer versionNumber, Integer executionId, Integer testPlanID, Integer testProjectId, String customFieldName,
            ResponseDetails details) throws TestLinkAPIException {
        return this.testCaseService.getTestCaseCustomFieldExecutionValue(testCaseId, testCaseExternalId, versionNumber,
                executionId, testPlanID, testProjectId, customFieldName, details);
    }

    /**
     * Gets list of keywords for a given Test case
     *
     * @param testProjectId test project ID
     * @param testCaseId test case ID
     * @return test case keywords
     * @throws TestLinkAPIException if the service returns an error
     */
    public List<String> getTestCaseKeywords(Integer testProjectId, Integer testCaseId) throws TestLinkAPIException {
        return this.testCaseService.getTestCaseKeywords(testProjectId, testCaseId);
    }

    /**
     * Sets the test case execution type.
     *
     * @param testProjectId test project ID
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param versionNumber version number
     * @param executionType execution type
     * @return server response map
     */
    public Map<String, Object> setTestCaseExecutionType(Integer testProjectId, Integer testCaseId,
            Integer testCaseExternalId, Integer versionNumber, ExecutionType executionType) {
        return this.testCaseService.setTestCaseExecutionType(testProjectId, testCaseId, testCaseExternalId,
                versionNumber, executionType);
    }

    /**
     * Update the value of an existing custom field for a Test Case
     *
     * @param testCaseId test case ID
     * @param versionNumber version number
     * @param testProjectId test project ID
     * @param customFieldName custom field name
     * @param customFieldValue custom field value
     * @return server response map
     */
    public Map<String, Object> updateTestCaseCustomFieldDesignValue(Integer testCaseId, Integer versionNumber,
            Integer testProjectId, String customFieldName, String customFieldValue) {
        return this.testCaseService.updateTestCaseCustomFieldDesignValue(testCaseId, versionNumber, testProjectId,
                customFieldName, customFieldValue);
    }

    /**
     * Assign user to execute Test Case in Test Plan
     *
     * @param testPlanId test plan ID
     * @param testCaseExternalId test case external ID
     * @param user user
     * @param buildName build name
     * @throws TestLinkAPIException if the service returns an error
     */
    public void assignTestCaseExecutionTask(Integer testPlanId, String testCaseExternalId, String user,
            String buildName) throws TestLinkAPIException {
        this.testCaseService.assignTestCaseExecutionTask(testPlanId, testCaseExternalId, user, buildName);
    }

    /* XX Requirements Specification operations XX */

    /**
     * Uploads an attachment to a Requirement Specification.
     *
     * @param reqSpecId requirement specification ID
     * @param title title
     * @param description description
     * @param fileName file name
     * @param fileType file type
     * @param content content
     * @return Attachment.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Attachment uploadRequirementSpecificationAttachment(Integer reqSpecId, String title, String description,
            String fileName, String fileType, String content) throws TestLinkAPIException {
        return this.reqSpecService.uploadRequirementSpecificationAttachment(reqSpecId, title, description, fileName,
                fileType, content);
    }

    /* XX Requirements operations XX */

    /**
     * Uploads an attachment to a Requirement.
     *
     * @param requirementId requirement ID
     * @param title title
     * @param description description
     * @param fileName file name
     * @param fileType file type
     * @param content content
     * @return Attachment.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Attachment uploadRequirementAttachment(Integer requirementId, String title, String description,
            String fileName, String fileType, String content) throws TestLinkAPIException {
        return this.requirementService.uploadRequirementAttachment(requirementId, title, description, fileName,
                fileType, content);
    }

    /**
     * Assign a requirements to a Test Case.
     *
     * @param testCaseId test case ID
     * @param testProjectId test project ID
     * @param requirements list of requirements
     * @throws TestLinkAPIException if the service returns an error
     */
    public void assignRequirements(Integer testCaseId, Integer testProjectId, List<Requirement> requirements)
            throws TestLinkAPIException {
        this.requirementService.assignRequirements(testCaseId, testProjectId, requirements);
    }

    /**
     * Executes a XML-RPC call. Use this method to talk with TestLink in a lower level.
     *
     * @param methodName Name of the method.
     * @param executionData Execution data map.
     * @return Object returned from the server.
     * @throws XmlRpcException if the XML-RPC call is invalid
     * @throws TestLinkAPIException if the service returns an error
     */
    public Object executeXmlRpcCall(String methodName, Map<String, Object> executionData)
            throws XmlRpcException, TestLinkAPIException {
        return miscService.executeXmlRpcCall(methodName, executionData);
    }

    /**
     * Add keywords to existing test cases.
     *
     * @param testCaseKeywordsMap - Map of testcase externalId to list of keywords
     * @return Object returned from the server.
     * @throws TestLinkAPIException if the service returns an error
     */
    public Map<String, Object> addTestCaseKeywords(Map<String, List<String>> testCaseKeywordsMap) {
        return this.testCaseService.addTestCaseKeyWords(testCaseKeywordsMap);
    }

    /**
     * Get bugs related to a test case.
     *
     * @param testPlanId test plan ID
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param platformId platform ID
     * @param platformName platform name
     * @param buildId build ID
     * @param buildName build name
     * @return array with the ID's of bugs
     */
    public List<Integer> getTestCaseBugs(Integer testPlanId, Integer testCaseId, Integer testCaseExternalId,
            Integer platformId, String platformName, Integer buildId, String buildName) {
        return this.testCaseService.getTestCaseBugs(testPlanId, testCaseId, testCaseExternalId, platformId,
                platformName, buildId, buildName);
    }
}
