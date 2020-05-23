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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkTables;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * This class is responsible for managing test projects.
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
@SuppressWarnings("unchecked")
class TestProjectService extends BaseService {

    /**
     * @param xmlRpcClient XML RPC Client.
     * @param devKey TestLink User DevKey.
     */
    TestProjectService(XmlRpcClient xmlRpcClient, String devKey) {
        super(xmlRpcClient, devKey);
    }

    /**
     * Creates a Test Project.
     *
     * @return Created Test Project object.
     */
    protected TestProject createTestProject(String testProjectName, String testProjectPrefix, String notes,
            Boolean enableRequirements, Boolean enableTestPriority, Boolean enableAutomation, Boolean enableInventory,
            Boolean isActive, Boolean isPublic) throws TestLinkAPIException {
        TestProject testProject;

        Integer id = 0;

        testProject = new TestProject(id, testProjectName, testProjectPrefix, notes, enableRequirements,
                enableTestPriority, enableAutomation, enableInventory, isActive, isPublic);

        try {
            Map<String, Object> executionData = Util.getTestProjectMap(testProject);
            Object response = this.executeXmlRpcCall(TestLinkMethods.CREATE_TEST_PROJECT.toString(), executionData);
            Object[] responseArray = Util.castToArray(response);
            Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

            id = Util.getInteger(responseMap, TestLinkResponseParams.ID.toString());
            testProject.setId(id);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error creating test project: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return testProject;
    }

    protected TestProject getTestProjectByName(String projectName) throws TestLinkAPIException {
        TestProject testProject;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PROJECT_NAME.toString(), projectName);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TEST_PROJECT_BY_NAME.toString(),
                    executionData);
            Map<String, Object> responseMap = Util.castToMap(response);
            testProject = Util.getTestProject(responseMap);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving test project: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return testProject;
    }

    /**
     *
     * @return
     * @throws TestLinkAPIException
     */
    protected TestProject[] getProjects() throws TestLinkAPIException {
        TestProject[] projects;

        try {
            Map<String, Object> executionData = new HashMap<>();
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_PROJECTS.toString(), executionData);
            Object[] responseArray = Util.castToArray(response);
            projects = new TestProject[responseArray.length];
            for (int i = 0; i < responseArray.length; i++) {
                Map<String, Object> projectMap = (Map<String, Object>) responseArray[i];
                TestProject testProject = Util.getTestProject(projectMap);
                projects[i] = testProject;
            }

        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving test projects: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return projects;
    }

    /**
     * Retrieves Test Plans associated to a Test Project.
     *
     * @param projectId Test Project id.
     * @return Associated Test Plans.
     * @throws TestLinkAPIException
     */
    protected TestPlan[] getProjectTestPlans(Integer projectId) throws TestLinkAPIException {
        TestPlan[] testPlans;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), projectId);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_PROJECT_TEST_PLANS.toString(), executionData);
            Object[] responseArray = Util.castToArray(response);
            testPlans = new TestPlan[responseArray.length];
            for (int i = 0; i < responseArray.length; i++) {
                Map<String, Object> planMap = (Map<String, Object>) responseArray[i];
                TestPlan testPlan = Util.getTestPlan(planMap);
                testPlans[i] = testPlan;
            }

        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving test plans: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return testPlans;
    }

    /**
     * @param testProjectId
     * @param title
     * @param description
     * @param fileName
     * @param fileType
     * @param content
     * @return
     * @throws TestLinkAPIException
     */
    protected Attachment uploadTestProjectAttachment(Integer testProjectId, String title, String description,
            String fileName, String fileType, String content) throws TestLinkAPIException {
        Attachment attachment;

        Integer id = 0;

        attachment = new Attachment(id, testProjectId, TestLinkTables.NODES_HIERARCHY.toString(), title, description,
                fileName, null, fileType, content);

        try {
            Map<String, Object> executionData = Util.getTestProjectAttachmentMap(attachment);
            Object response = this.executeXmlRpcCall(TestLinkMethods.UPLOAD_TEST_PROJECT_ATTACHMENT.toString(),
                    executionData);
            Map<String, Object> responseMap = (Map<String, Object>) response;
            id = Util.getInteger(responseMap, TestLinkResponseParams.ID.toString());
            attachment.setId(id);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error uploading attachment for test project: " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return attachment;
    }

    protected Platform[] getProjectPlatforms(Integer testProjectId) throws TestLinkAPIException {
        Platform[] platforms;
        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), testProjectId);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_PROJECT_PLATFORMS.toString(), executionData);
            Map<String, Object> responseMap = Util.castToMap(response);
            if (responseMap.size() == 2 && responseMap.get("id") != null && responseMap.get("name") != null
                    && responseMap.get("name") instanceof String) {
                platforms = new Platform[1];
                Platform platform = Util.getPlatform(responseMap);
                platforms[0] = platform;
            } else {
                platforms = new Platform[responseMap.size()];
                Collection<Object> list = responseMap.values();
                int index = 0;
                for (Object o : list) {
                    Platform platform = Util.getPlatform((Map<String, Object>) o);
                    platforms[index] = platform;
                    index++;
                }
            }
            return platforms;
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error getting project platform: " + xmlrpcex.getMessage(), xmlrpcex);
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        XmlRpcClientConfigImpl pConfig = new XmlRpcClientConfigImpl();
        pConfig.setServerURL(new URL("http://localhost:3300/testlink-1.9.6/lib/api/xmlrpc.php"));
        xmlRpcClient.setConfig(pConfig);
        TestProjectService service = new TestProjectService(xmlRpcClient, "09b83b6813a55ef6f7e2d7d63cb6f65c");
        Platform[] platforms = service.getProjectPlatforms(2);
        for (Platform platform : platforms) {
            System.out.println(platform);
        }
    }

}
