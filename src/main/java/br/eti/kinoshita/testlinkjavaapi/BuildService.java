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
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
class BuildService extends BaseService {

    /**
     * @param xmlRpcClient XML RPC Client.
     * @param devKey TestLink User DevKey.
     */
    BuildService(XmlRpcClient xmlRpcClient, String devKey) {
        super(xmlRpcClient, devKey);
    }

    @SuppressWarnings("unchecked")
    protected Build createBuild(Integer testPlanId, String buildName, String buildNotes) throws TestLinkAPIException {
        Build build;

        Integer id = 0;

        build = new Build(id, testPlanId, buildName, buildNotes);

        try {
            Map<String, Object> executionData = Util.getBuildMap(build);
            Object response = this.executeXmlRpcCall(TestLinkMethods.CREATE_BUILD.toString(), executionData);
            Object[] responseArray = Util.castToArray(response);
            Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];

            id = Util.getInteger(responseMap, TestLinkResponseParams.ID.toString());
            build.setId(id);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error creating build: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return build;
    }

    /**
     * @param testPlanId
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Build[] getBuildsForTestPlan(Integer testPlanId) throws TestLinkAPIException {
        Build[] builds = null;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_BUILDS_FOR_TEST_PLAN.toString(),
                    executionData);
            if (response instanceof Object[]) {
                Object[] responseArray = Util.castToArray(response);
                builds = new Build[responseArray.length];
                for (int i = 0; i < responseArray.length; i++) {
                    Map<String, Object> responseMap = (Map<String, Object>) responseArray[i];
                    builds[i] = Util.getBuild(responseMap);
                }
            }

        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving test plan's builds: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return builds;
    }

    /**
     * @param testPlanId
     * @return
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected Build getLatestBuildForTestPlan(Integer testPlanId) throws TestLinkAPIException {
        Build build = null;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_LATEST_BUILD_FOR_TEST_PLAN.toString(),
                    executionData);
            if (response instanceof Map<?, ?>) {
                Map<String, Object> responseMap = (Map<String, Object>) response;
                build = Util.getBuild(responseMap);
            }
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving latest build for test plan: " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return build;
    }

    /**
     * @param testPlanId
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> getExecCountersByBuild(Integer testPlanId) {
        Map<String, Object> responseMap = null;
        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_EXEC_COUNTERS_BY_BUILD.toString(),
                    executionData);
            if (response instanceof Map<?, ?>) {
                responseMap = (Map<String, Object>) response;
            }
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error getting exec counters by build: " + xmlrpcex.getMessage(), xmlrpcex);
        }
        return responseMap;
    }

    /**
     * Update build custom fields.
     *
     * @since 1.9.16-0
     * @param buildId Build ID
     * @param testProjectId Test Project ID
     * @param customFields Custom Fields name,value pairs
     * @return Response XML-RPC Response
     * @throws TestLinkAPIException if the service returns as error
     */
    protected Map<String, Object> updateBuildCustomFields(Integer buildId, Integer testProjectId, Integer testPlanId, Map<String, String> customFields) {

        Map<String, Object> responseMap =null;

        try {
            Map<String, Object> executionData = new HashMap<>();

            executionData.put(TestLinkParams.BUILD_ID.toString(), buildId);
            executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), testProjectId);
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            executionData.put(TestLinkParams.CUSTOM_FIELDS.toString(), customFields);

            Object response = this.executeXmlRpcCall(TestLinkMethods.UPDATE_BUILD_CUSTOM_FIELDS.toString(),
                    executionData);
            if (response instanceof Map<?, ?>) {
                responseMap = Util.castToMap(response);
            } else if (! (response instanceof String) ) {
                responseMap = Util.castToMap(((Object[]) response)[0]);
            }
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error updating Build custom fields. " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return responseMap;

    }
}
