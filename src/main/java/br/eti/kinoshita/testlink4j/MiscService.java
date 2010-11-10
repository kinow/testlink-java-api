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
package br.eti.kinoshita.testlink4j;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;

import br.eti.kinoshita.testlink4j.model.Attachment;
import br.eti.kinoshita.testlink4j.model.Execution;
import br.eti.kinoshita.testlink4j.util.TestLinkMethods;
import br.eti.kinoshita.testlink4j.util.TestLinkParams;
import br.eti.kinoshita.testlink4j.util.TestLinkResponseParams;
import br.eti.kinoshita.testlink4j.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
class MiscService 
extends BaseService
{

	/**
	 * @param url
	 * @param devKey
	 * @throws MalformedURLException
	 */
	public MiscService(String url, String devKey) 
	throws MalformedURLException
	{
		super(url, devKey);
	}

	protected Boolean checkDevKey(String devKey) 
	throws TestLinkAPIException
	{
		Boolean statusOk = false;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.devKey.toString(), devKey);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.checkDevKey.toString(), executionData);
			statusOk = Boolean.valueOf( response.toString() );
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error verifying developer key: " + xmlrpcex.getMessage(), xmlrpcex);
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
	protected Boolean doesUserExist(String user) 
	throws TestLinkAPIException
	{
		Boolean userExist = false;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.user.toString(), user);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.doesUserExist.toString(), executionData);
			userExist = Boolean.valueOf( response.toString() );
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error verifying if user exists: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return userExist;
	}

	/**
	 * Says hello.
	 * 
	 * @return
	 * @throws TestLinkAPIException 
	 */
	protected String sayHello() 
	throws TestLinkAPIException 
	{
		String message = null;
		
		try
		{
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.sayHello.toString(), null);
			message = (String) response ;
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error saying hello: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return message;
	}

	/**
	 * @return
	 * @throws TestLinkAPIException
	 */
	protected String about() 
	throws TestLinkAPIException
	{
		String message = null;
		
		try
		{
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.about.toString(), null);
			message = (String) response ;
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error in about method: " + xmlrpcex.getMessage(), xmlrpcex);
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
	protected Boolean setTestMode(Boolean testMode) 
	throws TestLinkAPIException
	{
		Boolean result = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testMode.toString(), testMode);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.setTestMode.toString(), executionData);
			result = (Boolean) response ;
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error setting test mode: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return result;
	}

	/**
	 * @param str
	 * @return
	 * @throws TestLinkAPIException
	 */
	protected String repeat(String str) 
	throws TestLinkAPIException
	{
		String repeatMessage = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.str.toString(), str);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.repeat.toString(), executionData);
			repeatMessage = (String) response ;
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error setting test mode: " + xmlrpcex.getMessage(), xmlrpcex);
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
	 * @param file
	 * @return Attachment
	 * @throws TestLinkAPIException
	 */
	@SuppressWarnings("unchecked")
	protected Attachment uploadAttachment( 
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
		Attachment attachment = null;
		
		Integer id = 0;
		
		attachment = new Attachment(
			id, 
			fkId, 
			fkTable, 
			title, 
			description, 
			fileName, 
			null, 
			fileType, 
			content
		);
		
		try
		{
			Map<String, Object> executionData = Util.getAttachmentMap(attachment);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.uploadAttachment.toString(), executionData);
			Map<String, Object> responseMap = (Map<String, Object>)response;
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			attachment.setId(id);
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error uploading attachment: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return attachment;
	}

	/**
	 * @param nodeId
	 */
	@SuppressWarnings("unchecked")
	protected String[] getFullPath(Integer nodeId) 
	throws TestLinkAPIException
	{
		
		String[] names = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put( TestLinkParams.nodeId.toString(), nodeId );
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getFullPath.toString(), executionData);
			if ( response instanceof Map<?, ?> )
			{
				Map<String, Object> responseMap = (Map<String, Object>)response;
				if ( responseMap.size() > 0 )
				{
					Object value = responseMap.get(nodeId.toString());
					Object values[] = (Object[]) value;
					names = new String[ values.length ];
					for (int i = 0; i < values.length; i++) 
					{
						names[i] = values[i].toString();
					}
				}
			}
			
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error uploading attachment: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return names;
		
	}

	/**
	 * @param testPlanId
	 * @param testCaseId
	 * @param testCaseExternalId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Execution getLastExecutionResult(
		Integer testPlanId,
		Integer testCaseId, 
		Integer testCaseExternalId) 
	throws TestLinkAPIException
	{
		
		Execution execution = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put( TestLinkParams.testPlanId.toString(), testPlanId );
			executionData.put( TestLinkParams.testCaseId.toString(), testCaseId );
			executionData.put( TestLinkParams.testCaseExternalId.toString(), testCaseExternalId );
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getLastExecutionResult.toString(), executionData);
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			if ( responseMap instanceof Map<?, ?> && responseMap.size() > 0  )
			{
				execution = Util.getExecution(responseMap);
			}
			
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving last execution result: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return execution;
		
	}
	
}
