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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkTables;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
@SuppressWarnings("unchecked")
class TestSuiteService extends BaseService {

    /**
     * @param xmlRpcClient XML RPC Client.
     * @param devKey TestLink User DevKey.
     */
    TestSuiteService(XmlRpcClient xmlRpcClient, String devKey) {
        super(xmlRpcClient, devKey);
    }

    /**
     * Creates a Test Suite.
     *
     * @param testProjectId
     * @param name
     * @param details
     * @param parentId
     * @param order
     * @param checkDuplicatedName
     * @param actionOnDuplicatedName
     * @return Test Suite.
     * @throws TestLinkAPIException
     */
    protected TestSuite createTestSuite(Integer testProjectId, String name, String details, Integer parentId,
            Integer order, Boolean checkDuplicatedName, ActionOnDuplicate actionOnDuplicatedName)
            throws TestLinkAPIException {
        TestSuite testSuite;

        Integer id = 0;

        testSuite = new TestSuite(id, testProjectId, name, details, parentId, order, checkDuplicatedName,
                actionOnDuplicatedName);

        try {
            Map<String, Object> executionData = Util.getTestSuiteMap(testSuite);
            Object response = this.executeXmlRpcCall(TestLinkMethods.CREATE_TEST_SUITE.toString(), executionData);
            Object[] responseArray = Util.castToArray(response);
            Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

            id = Util.getInteger(responseMap, TestLinkResponseParams.ID.toString());
            testSuite.setId(id);

            testSuite.setName(name);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error creating test suite: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return testSuite;
    }

    /**
     * @param testSuiteIds
     * @return
     */
    protected TestSuite[] getTestSuiteByID(List<Integer> testSuiteIds) throws TestLinkAPIException {
        TestSuite[] testSuites = null;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_SUITE_ID.toString(), testSuiteIds);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TEST_SUITE_BY_ID.toString(), executionData);
            if (response instanceof Object[]) {
                Object[] responseArray = Util.castToArray(response);
                testSuites = new TestSuite[responseArray.length];

                for (int i = 0; i < responseArray.length; i++) {
                    Map<String, Object> responseMap = (Map<String, Object>) responseArray[i];
                    testSuites[i] = Util.getTestSuite(responseMap);
                }
            } else if (response instanceof Map<?, ?>) {
                Map<String, Object> responseMap = (Map<String, Object>) response;
                if (StringUtils.isBlank(Util.getString(responseMap, TestLinkResponseParams.ID.toString()))) {
                    testSuites = new TestSuite[responseMap.size()];
                    int i = 0;
                    Set<Entry<String, Object>> entrySet = responseMap.entrySet();
                    for (Entry<String, Object> entry : entrySet) {
                        Map<String, Object> testSuiteMap;
                        if (entry.getValue() instanceof Object[]) {
                            Object[] responseArray = (Object[]) entry.getValue();
                            testSuiteMap = (Map<String, Object>) responseArray[0];
                        } else {
                            testSuiteMap = (Map<String, Object>) entry.getValue();
                        }
                        testSuites[i] = Util.getTestSuite(testSuiteMap);
                        i++;
                    }
                } else {
                    testSuites = new TestSuite[1];
                    testSuites[0] = Util.getTestSuite(responseMap);
                }
            }
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving test suites by id: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return testSuites;
    }

    /**
     * @param testSuiteId
     * @param title
     * @param description
     * @param fileName
     * @param fileType
     * @param content
     * @return
     */
    protected Attachment uploadTestSuiteAttachment(Integer testSuiteId, String title, String description,
            String fileName, String fileType, String content) throws TestLinkAPIException {
        Attachment attachment;

        Integer id = 0;

        attachment = new Attachment(id, testSuiteId, TestLinkTables.NODES_HIERARCHY.toString(), title, description,
                fileName, null, fileType, content);

        try {
            Map<String, Object> executionData = Util.getTestSuiteAttachmentMap(attachment);
            Object response = this.executeXmlRpcCall(TestLinkMethods.UPLOAD_TEST_SUITE_ATTACHMENT.toString(),
                    executionData);
            Map<String, Object> responseMap = (Map<String, Object>) response;
            id = Util.getInteger(responseMap, TestLinkResponseParams.ID.toString());
            attachment.setId(id);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error uploading attachment for test suite: " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return attachment;
    }

    /**
     * Get list of TestSuites from a test plan
     *
     * @param testPlanId
     * @throws TestLinkAPIException
     */
    protected TestSuite[] getTestSuitesForTestPlan(Integer testPlanId) throws TestLinkAPIException {
        TestSuite[] testSuites;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);

            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TEST_SUITES_FOR_TEST_PLAN.toString(),
                    executionData);

            Object[] responseArray = Util.castToArray(response);
            testSuites = new TestSuite[responseArray.length];

            for (int i = 0; i < responseArray.length; i++) {
                Map<String, Object> responseMap = (Map<String, Object>) responseArray[i];
                testSuites[i] = Util.getTestSuite(responseMap);
            }

        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving test suites by Test Plan ID: " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return testSuites;
    }

    /**
     * Get list of TestSuites which are DIRECT children of a given TestSuite
     *
     * @param testSuiteId
     * @throws TestLinkAPIException
     */
    protected TestSuite[] getTestSuitesForTestSuite(Integer testSuiteId) throws TestLinkAPIException {
        TestSuite[] testSuites;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_SUITE_ID.toString(), testSuiteId);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TEST_SUITES_FOR_TEST_SUITE.toString(),
                    executionData);

            Map<String, Object> responseMap = Util.castToMap(response);
            Set<Entry<String, Object>> entrySet = responseMap.entrySet();

            testSuites = new TestSuite[entrySet.size()];
            boolean singleElement = false;
            int index = 0;
            for (Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                Object o = entry.getValue();
                if (o instanceof String) {
                    // TBD: think something wiser
                    singleElement = true;
                    break;
                }
                Map<String, Object> testSuiteMap = (Map<String, Object>) entry.getValue();
                testSuiteMap.put(TestLinkResponseParams.ID.toString(), key);
                testSuites[index] = Util.getTestSuite(testSuiteMap);
                index += 1;
            }

            if (singleElement) {
                testSuites = new TestSuite[1];
                testSuites[0] = Util.getTestSuite(responseMap);
            }
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException(
                    "Error retrieving test suites which are DIRECT children of a given TestSuite: "
                            + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return testSuites;
    }

    /**
     * Get set of test suites AT TOP LEVEL of tree on a Test Project
     *
     * @param testProjectId
     * @throws TestLinkAPIException
     */
    protected TestSuite[] getFirstLevelTestSuitesForTestProject(Integer testProjectId) throws TestLinkAPIException {
        TestSuite[] testSuites;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), testProjectId);
            Object response = this.executeXmlRpcCall(
                    TestLinkMethods.GET_FIRST_LEVEL_TEST_SUITES_FOR_TEST_PROJECT.toString(), executionData);

            Object[] responseArray = Util.castToArray(response);
            testSuites = new TestSuite[responseArray.length];

            for (int i = 0; i < responseArray.length; i++) {
                Map<String, Object> responseMap = (Map<String, Object>) responseArray[i];
                testSuites[i] = Util.getTestSuite(responseMap);
            }
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException(
                    "Error retrieving test suites AT TOP LEVEL of tree on a Test Project: " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return testSuites;
    }

    /**
     * Get attachments of a test suite.
     *
     * @param testSuiteId test suite ID
     * @return Array of attachments for test suite
     * @throws TestLinkAPIException if an error occurs
     * @author dennis@etern-it.de
     */
    protected Attachment[] getTestSuiteAttachments(Integer testSuiteId) {
        Attachment[] attachments;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_SUITE_ID.toString(), testSuiteId);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TEST_SUITE_ATTACHMENTS.toString(),
                    executionData);
            if (response instanceof Map<?, ?>) {
                Map<String, Object> responseMap = Util.castToMap(response);
                Set<Entry<String, Object>> entrySet = responseMap.entrySet();

                attachments = new Attachment[entrySet.size()];

                int index = 0;
                for (Entry<String, Object> entry : entrySet) {
                    String key = entry.getKey();
                    Map<String, Object> attachmentMap = (Map<String, Object>) entry.getValue();
                    attachmentMap.put(TestLinkResponseParams.ID.toString(), key);
                    attachments[index] = Util.getAttachment(attachmentMap);
                    index += 1;
                }
            } else {
                attachments = new Attachment[0];
            }

        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving test suite's attachments: " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return attachments;
    }
}
