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
import java.util.HashMap;
import java.util.Map;

import br.eti.kinoshita.testlinkjavaapi.constants.ResponseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
@SuppressWarnings("unchecked")
class TestPlanService extends BaseService {

    /**
     * @param xmlRpcClient XML RPC Client.
     * @param devKey TestLink User DevKey.
     */
    protected TestPlanService(XmlRpcClient xmlRpcClient, String devKey) {
        super(xmlRpcClient, devKey);
    }

    protected TestPlan createTestPlan(String planName, String projectName, String notes, Boolean isActive,
            Boolean isPublic) throws TestLinkAPIException {
        final TestPlan testPlan;

        Integer id = 0;

        testPlan = new TestPlan(id, planName, projectName, notes, isActive, isPublic);

        try {
            final Map<String, Object> executionData = Util.getTestPlanMap(testPlan);
            final Object response = this.executeXmlRpcCall(TestLinkMethods.CREATE_TEST_PLAN.toString(), executionData);
            final Object[] responseArray = (Object[]) response;
            final Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

            id = Util.getInteger(responseMap, TestLinkResponseParams.ID.toString());
            testPlan.setId(id);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error creating test plan: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return testPlan;
    }

    /**
     * Retrieves a Test Plan by its name.
     *
     * @param planName Test Plan name.
     * @param projectName Test Project name.
     * @return Test Plane.
     * @throws TestLinkAPIException
     */
    protected TestPlan getTestPlanByName(String planName, String projectName) throws TestLinkAPIException {
        final TestPlan testPlan;

        try {
            final Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_NAME.toString(), planName);
            executionData.put(TestLinkParams.TEST_PROJECT_NAME.toString(), projectName);
            final Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TEST_PLAN_BY_NAME.toString(), executionData);
            final Object[] responseArray = (Object[]) response;
            final Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];
            // TBD: check with TL team if we can change it there.
            responseMap.put(TestLinkResponseParams.PROJECT_NAME.toString(), projectName);
            testPlan = Util.getTestPlan(responseMap);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error creating test project: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return testPlan;
    }

    /**
     *
     * @param testPlanId
     * @param testProjectId
     * @param customFieldName
     * @param details
     * @return
     * @throws TestLinkAPIException
     */
    protected CustomField getTestPlanCustomFieldDesignValue(Integer testPlanId, Integer testProjectId,
            String customFieldName, ResponseDetails details) throws TestLinkAPIException {
        CustomField customField = null;
        try {
            final Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), testProjectId);
            executionData.put(TestLinkParams.CUSTOM_FIELD_NAME.toString(), customFieldName);
            executionData.put(TestLinkParams.DETAILS.toString(), Util.getStringValueOrNull(details));

            final Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TEST_PLAN_CUSTOM_FIELD_DESIGN_VALUE.toString(),
                    executionData);

            if (response instanceof String) {
                customField = new CustomField();
                customField.setValue(response.toString());
            } else if (response instanceof Map<?, ?>) {
                final Map<String, Object> responseMap = Util.castToMap(response);
                customField = Util.getCustomField(responseMap);
            }
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving test case custom field value: " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return customField;
    }

    /**
     * @param planId
     * @return
     */
    protected Platform[] getTestPlanPlatforms(Integer planId) throws TestLinkAPIException {
        final Platform[] platforms;

        try {
            final Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), planId);
            final Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TEST_PLAN_PLATFORMS.toString(), executionData);
            final Object[] responseArray = (Object[]) response;
            platforms = new Platform[responseArray.length];
            for (int i = 0; i < responseArray.length; i++) {
                final Map<String, Object> projectMap = (Map<String, Object>) responseArray[i];
                final Platform platform = Util.getPlatform(projectMap);
                platforms[i] = platform;
            }

        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving platforms: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return platforms;
    }

    /**
     * @param testPlanId
     * @return
     */
    protected Map<String, Object> getTotalsForTestPlan(Integer testPlanId) throws TestLinkAPIException {

        Map<String, Object> responseMap = null;

        try {
            final Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            final Object response = this.executeXmlRpcCall(TestLinkMethods.GET_TOTALS_FOR_TEST_PLAN.toString(),
                    executionData);
            if (response instanceof Object[]) {
                final Object[] responseArray = (Object[]) response;
                responseMap = (Map<String, Object>) responseArray[0];
            } else if (response instanceof Map<?, ?>) {
                responseMap = (Map<String, Object>) response;
            }
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving totals for test plan: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return responseMap;

    }

    protected Map<String, Object> removePlatformFromTestPlan(Integer testProjectId, Integer testPlanId,
            String platformName) {
        try {
            final Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), testProjectId);
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            executionData.put(TestLinkParams.PLATFORM_NAME.toString(), platformName);
            final Object response = this.executeXmlRpcCall(TestLinkMethods.REMOVE_PLATFORM_FROM_TEST_PLAN.toString(),
                    executionData);
            return Util.castToMap(response);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving platforms: " + xmlrpcex.getMessage(), xmlrpcex);
        }
    }

    protected Map<String, Object> addPlatformToTestPlan(Integer testProjectId, Integer testPlanId,
            String platformName) {
        try {
            final Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), testProjectId);
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            executionData.put(TestLinkParams.PLATFORM_NAME.toString(), platformName);
            final Object response = this.executeXmlRpcCall(TestLinkMethods.ADD_PLATFORM_TO_TEST_PLAN.toString(),
                    executionData);
            return Util.castToMap(response);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving platforms: " + xmlrpcex.getMessage(), xmlrpcex);
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        final XmlRpcClient xmlRpcClient = new XmlRpcClient();
        final XmlRpcClientConfigImpl pConfig = new XmlRpcClientConfigImpl();
        pConfig.setServerURL(new URL("http://localhost:3300/testlink-1.9.6/lib/api/xmlrpc.php"));
        xmlRpcClient.setConfig(pConfig);
        final TestPlanService service = new TestPlanService(xmlRpcClient, "09b83b6813a55ef6f7e2d7d63cb6f65c");
        final Map<?, ?> message = service.addPlatformToTestPlan(2, 8, "browser");
        System.out.println(message);
    }

}
