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

import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkTables;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
class ReqSpecService extends BaseService {

    /**
     * @param xmlRpcClient XML RPC Client.
     * @param devKey TestLink User DevKey.
     */
    ReqSpecService(XmlRpcClient xmlRpcClient, String devKey) {
        super(xmlRpcClient, devKey);
    }

    /**
     * @param reqSpecId
     * @param title
     * @param description
     * @param fileName
     * @param fileType
     * @param content
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Attachment uploadRequirementSpecificationAttachment(Integer reqSpecId, String title, String description,
            String fileName, String fileType, String content) throws TestLinkAPIException {
        Attachment attachment;

        Integer id = 0;

        attachment = new Attachment(id, reqSpecId, TestLinkTables.REQUIREMENT_SPECIFICATIONS.toString(), title,
                description, fileName, null, fileType, content);

        try {
            Map<String, Object> executionData = Util.getRequirementSpecificationAttachmentMap(attachment);
            Object response = this.executeXmlRpcCall(
                    TestLinkMethods.UPLOAD_REQUIREMENT_SPECIFICATION_ATTACHMENT.toString(), executionData);
            Map<String, Object> responseMap = (Map<String, Object>) response;
            id = Util.getInteger(responseMap, TestLinkResponseParams.ID.toString());
            attachment.setId(id);
        } catch (XmlRpcException xmlrpcex) {
            throw new TestLinkAPIException(
                    "Error uploading attachment for requirement specification: " + xmlrpcex.getMessage(), xmlrpcex);
        }

        return attachment;
    }

}
