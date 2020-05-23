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
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Execution;
import br.eti.kinoshita.testlinkjavaapi.model.User;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
class MiscService extends BaseService {

    /**
     * @param xmlRpcClient XML RPC Client.
     * @param devKey TestLink User DevKey.
     */
    MiscService(XmlRpcClient xmlRpcClient, String devKey) {
        super(xmlRpcClient, devKey);
    }

    protected Boolean checkDevKey(String devKey) throws TestLinkAPIException {
        boolean statusOk;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.DEV_KEY.toString(), devKey);
            Object response = this.executeXmlRpcCall(TestLinkMethods.CHECK_DEV_KEY.toString(), executionData);
            statusOk = Boolean.parseBoolean(response.toString());
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error verifying developer key: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return statusOk;
    }

    /**
     * Checks if the given user exist.
     *
     * @param user
     * @return
     * @throws TestLinkAPIException
     */
    protected Boolean doesUserExist(String user) throws TestLinkAPIException {
        boolean userExist;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.USER.toString(), user);
            Object response = this.executeXmlRpcCall(TestLinkMethods.DOES_USER_EXIST.toString(), executionData);
            userExist = Boolean.parseBoolean(response.toString());
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error verifying if user exists: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return userExist;
    }

    /**
     * Get user by login.
     *
     * @param login
     * @return user
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected User getUserByLogin(String login) throws TestLinkAPIException {
        User user;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.USER.toString(), login);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_USER_BY_LOGIN.toString(), executionData);
            Object[] responseArray = Util.castToArray(response);
            Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];
            user = Util.getUser(responseMap);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error verifying if user exists: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return user;
    }

    /**
     * Says hello.
     *
     * @return
     * @throws TestLinkAPIException
     */
    protected String sayHello() throws TestLinkAPIException {
        String message;

        try {
            Object response = this.executeXmlRpcCall(TestLinkMethods.SAY_HELLO.toString(), null);
            message = (String) response;
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error saying hello: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return message;
    }

    /**
     * @return
     * @throws TestLinkAPIException
     */
    protected String about() throws TestLinkAPIException {
        String message;

        try {
            Object response = this.executeXmlRpcCall(TestLinkMethods.ABOUT.toString(), null);
            message = (String) response;
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error in about method: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return message;
    }

    /**
     * Sets test mode.
     *
     * @param testMode
     * @return true
     * @throws TestLinkAPIException
     */
    protected Boolean setTestMode(Boolean testMode) throws TestLinkAPIException {
        Boolean result;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_MODE.toString(), testMode);
            Object response = this.executeXmlRpcCall(TestLinkMethods.SET_TEST_MODE.toString(), executionData);
            result = (Boolean) response;
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error setting test mode: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return result;
    }

    /**
     * @param str
     * @return
     * @throws TestLinkAPIException
     */
    protected String repeat(String str) throws TestLinkAPIException {
        String repeatMessage;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.STR.toString(), str);
            Object response = this.executeXmlRpcCall(TestLinkMethods.REPEAT.toString(), executionData);
            repeatMessage = (String) response;
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error setting test mode: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return repeatMessage;
    }

    /**
     * @param fkId
     * @param fkTable
     * @param title
     * @param description
     * @param fileName
     * @param fileType
     * @param content
     * @return Attachment
     * @throws TestLinkAPIException
     */
    @SuppressWarnings("unchecked")
    protected Attachment uploadAttachment(Integer fkId, String fkTable, String title, String description,
            String fileName, String fileType, String content) throws TestLinkAPIException {
        Attachment attachment;

        Integer id = 0;

        attachment = new Attachment(id, fkId, fkTable, title, description, fileName, null, fileType, content);

        try {
            Map<String, Object> executionData = Util.getAttachmentMap(attachment);
            Object response = this.executeXmlRpcCall(TestLinkMethods.UPLOAD_ATTACHMENT.toString(), executionData);
            Map<String, Object> responseMap = (Map<String, Object>) response;
            id = Util.getInteger(responseMap, TestLinkResponseParams.ID.toString());
            attachment.setId(id);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error uploading attachment: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return attachment;
    }

    /**
     * @param nodeId
     */
    @SuppressWarnings("unchecked")
    protected String[] getFullPath(Integer nodeId) throws TestLinkAPIException {

        String[] names = null;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.NODE_ID.toString(), nodeId);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_FULL_PATH.toString(), executionData);
            if (response instanceof Map<?, ?>) {
                Map<String, Object> responseMap = (Map<String, Object>) response;
                if (responseMap.size() > 0) {
                    Object value = responseMap.get(nodeId.toString());
                    Object[] values = (Object[]) value;
                    names = new String[values.length];
                    for (int i = 0; i < values.length; i++) {
                        names[i] = values[i].toString();
                    }
                }
            }

        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error uploading attachment: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return names;

    }

    /**
     * @param testPlanId test plan ID
     * @param testCaseId test case ID
     * @param testCaseExternalId test case external ID
     * @param platformId platform ID
     * @param platformName platform name
     * @param buildId build ID
     * @param buildName build name
     * @param options array of options
     * @return Execution
     */
    @SuppressWarnings("unchecked")
    protected Execution getLastExecutionResult(Integer testPlanId, Integer testCaseId, String testCaseExternalId,
            Integer platformId, String platformName, Integer buildId, String buildName, Integer options)
            throws TestLinkAPIException {

        Execution execution = null;

        try {
            Map<String, Object> executionData = new HashMap<>();
            executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), testPlanId);
            executionData.put(TestLinkParams.TEST_CASE_ID.toString(), testCaseId);
            executionData.put(TestLinkParams.TEST_CASE_EXTERNAL_ID.toString(), testCaseExternalId);
            executionData.put(TestLinkParams.PLATFORM_ID.toString(), platformId);
            executionData.put(TestLinkParams.PLATFORM_NAME.toString(), platformName);
            executionData.put(TestLinkParams.BUILD_ID.toString(), buildId);
            executionData.put(TestLinkParams.BUILD_NAME.toString(), buildName);
            executionData.put(TestLinkParams.OPTIONS.toString(), options);
            Object response = this.executeXmlRpcCall(TestLinkMethods.GET_LAST_EXECUTION_RESULT.toString(),
                    executionData);
            Object[] responseArray = Util.castToArray(response);
            Map<String, Object> responseMap = (Map<String, Object>) responseArray[0];
            if (responseMap.size() > 0) {
                execution = Util.getExecution(responseMap);
            }

        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException("Error retrieving last execution result: " + xmlrpcex.getMessage(),
                    xmlrpcex);
        }

        return execution;

    }

}
