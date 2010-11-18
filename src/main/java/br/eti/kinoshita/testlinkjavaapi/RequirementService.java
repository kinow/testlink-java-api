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

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;

import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Requirement;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkTables;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
class RequirementService 
extends BaseService
{

	/**
	 * @param url
	 * @param devKey
	 * @throws MalformedURLException
	 */
	public RequirementService(String url, String devKey)
			throws MalformedURLException
	{
		super(url, devKey);
	}

	/**
	 * @param requirementId
	 * @param title
	 * @param description
	 * @param fileName
	 * @param fileType
	 * @param content
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Attachment uploadRequirementAttachment( 
		Integer requirementId,
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
			requirementId, 
			TestLinkTables.requirements.toString(), 
			title, 
			description, 
			fileName, 
			null, 
			fileType, 
			content
		);
		
		try
		{
			Map<String, Object> executionData = Util.getRequirementAttachmentMap(attachment);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.uploadRequirementAttachment.toString(), executionData);
			Map<String, Object> responseMap = (Map<String, Object>)response;
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			attachment.setId(id);
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error uploading attachment for requirement: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return attachment;
	}

	/**
	 * @param testCaseId
	 * @param testProjectId
	 * @param requirements
	 */
	protected void assignRequirements(
			Integer testCaseId, 
			Integer testProjectId,
			List<Requirement> requirements
	) 
	throws TestLinkAPIException
	{
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put( TestLinkParams.testCaseId.toString(), testCaseId );
			executionData.put( TestLinkParams.testProjectId.toString(), testProjectId );
			executionData.put( TestLinkParams.requirements.toString(), Util.getRequirementsGroupedByReqSpecMap(requirements) );
			this.executeXmlRpcCall(
					TestLinkMethods.assignRequirements.toString(), executionData);
			
			// the verification is done inside super.executeXml...
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error assigning requirement for test case: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
	}	

}
