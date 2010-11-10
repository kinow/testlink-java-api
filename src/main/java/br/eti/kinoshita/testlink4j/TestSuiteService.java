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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.xmlrpc.XmlRpcException;

import br.eti.kinoshita.testlink4j.model.Attachment;
import br.eti.kinoshita.testlink4j.model.TestSuite;
import br.eti.kinoshita.testlink4j.util.TestLinkMethods;
import br.eti.kinoshita.testlink4j.util.TestLinkParams;
import br.eti.kinoshita.testlink4j.util.TestLinkResponseParams;
import br.eti.kinoshita.testlink4j.util.TestLinkTables;
import br.eti.kinoshita.testlink4j.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
class TestSuiteService 
extends BaseService
{

	/**
	 * @param url
	 * @param devKey
	 * @throws MalformedURLException
	 */
	public TestSuiteService(String url, String devKey)
			throws MalformedURLException
	{
		super(url, devKey);
		// TODO Auto-generated constructor stub
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
	@SuppressWarnings("unchecked")
	protected TestSuite createTestSuite( 
		Integer testProjectId, 
		String name,
		String details, 
		Integer parentId, 
		Integer order,
		Boolean checkDuplicatedName,
		String actionOnDuplicatedName ) 
	throws TestLinkAPIException
	{
		TestSuite testSuite = null;
		
		Integer id = 0;
		
		testSuite = new TestSuite(
			id, 
			testProjectId, 
			name, 
			details, 
			parentId, 
			order, 
			checkDuplicatedName, 
			actionOnDuplicatedName
		);
		
		try
		{
			Map<String, Object> executionData = Util.getTestSuiteMap(testSuite);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.createTestSuite.toString(), executionData);
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			testSuite.setId( id );
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error creating test suite: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return testSuite;
	}

	/**
	 * @param testSuiteId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected TestSuite[] getTestSuiteByID(List<Integer> testSuiteIds) 
	throws TestLinkAPIException
	{
		TestSuite[] testSuites = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testSuiteId.toString(), testSuiteIds);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestSuiteByID.toString(), executionData);
			if ( response instanceof Object[] )
			{
				Object[] responseArray = (Object[])response;
				testSuites = new TestSuite[responseArray.length];
				
				for (int i = 0; i < responseArray.length; i++) 
				{
					Map<String, Object> responseMap = (Map<String, Object>)responseArray[i];
					testSuites[i] = Util.getTestSuite(responseMap);
				}
			} else if ( response instanceof Map<?, ?> )
			{
				testSuites = new TestSuite[1];
				Map<String, Object> responseMap = (Map<String, Object>)response;
				testSuites[0] = Util.getTestSuite(responseMap);
			}
			
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving test suites by id: " + xmlrpcex.getMessage(), xmlrpcex);
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
	@SuppressWarnings("unchecked")
	protected Attachment uploadTestSuiteAttachment( 
		Integer testSuiteId,
		String title, 
		String description, 
		String fileName, 
		String fileType,
		String content )
	throws TestLinkAPIException
	{
		Attachment attachment = null;
		
		Integer id = 0;
		
		attachment = new Attachment(
			id, 
			testSuiteId, 
			TestLinkTables.nodesHierarchy.toString(), 
			title, 
			description, 
			fileName, 
			null, 
			fileType, 
			content
		);
		
		try
		{
			Map<String, Object> executionData = Util.getTestSuiteAttachmentMap(attachment);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.uploadTestSuiteAttachment.toString(), executionData);
			Map<String, Object> responseMap = (Map<String, Object>)response;
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			attachment.setId(id);
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error uploading attachment for test suite: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return attachment;
	}
	
	/**
	 * Get list of TestSuites from a test plan
	 * @param testPlanId
	 * @throws TestLinkAPIException
	 */
	@SuppressWarnings("unchecked")
	protected TestSuite[] getTestSuitesForTestPlan(Integer testPlanId)
	throws TestLinkAPIException
	{
		TestSuite[] testSuites = null;
		
		try 
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testPlanId.toString(), testPlanId);
			
			Object response = this.executeXmlRpcCall( TestLinkMethods.getTestSuitesForTestPlan.toString(), executionData );
		
			Object[] responseArray = (Object[])response;
			testSuites = new TestSuite[ responseArray.length ];
			
			for (int i = 0; i < responseArray.length; i++) 
			{
				Map<String, Object> responseMap = (Map<String, Object>)responseArray[i];
				testSuites[i] = Util.getTestSuite( responseMap );
			}
		
		} 
		catch (XmlRpcException xmlrpcex) 
		{
			throw new TestLinkAPIException(
					"Error retrieving test suites by Test Plan ID: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return testSuites;
	}

	
	/**
	 * Get list of TestSuites which are DIRECT children of a given TestSuite
	 * 
	 * @param testSuiteId
	 * @throws TestLinkAPIException
	 */
	@SuppressWarnings("unchecked")
	protected TestSuite[] getTestSuitesForTestSuite(Integer testSuiteId)
	throws TestLinkAPIException
	{
		TestSuite[] testSuites = null;
		
		try {
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testSuiteId.toString(), testSuiteId);
			Object response = this.executeXmlRpcCall( TestLinkMethods.getTestSuitesForTestSuite.toString(), executionData );

			Map<String, Object> responseMap = (Map<String, Object>)response;
			Set<Entry<String, Object>> entrySet = responseMap.entrySet();
			
			testSuites = new TestSuite[ entrySet.size() ];
			boolean singleElement = false;
			int index = 0;
			for (Entry<String, Object> entry : entrySet) 
			{
				String key = entry.getKey();
				Object o = entry.getValue();
				if ( o instanceof String ) 
				{
					// TBD: think something wiser
					singleElement = true;
					break;
				}
				Map<String, Object> testSuiteMap = (Map<String, Object>) entry.getValue();
				testSuiteMap.put(TestLinkResponseParams.id.toString(), key);
				testSuites[index] = Util.getTestSuite( testSuiteMap );
				index += 1;
			}
			
			if ( singleElement ) 
			{
				testSuites = new TestSuite[1];
				testSuites[0] = Util.getTestSuite( responseMap );
			}
		} 
		catch (XmlRpcException xmlrpcex) 
		{
			throw new TestLinkAPIException(
					"Error retrieving test suites which are DIRECT children of a given TestSuite: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return testSuites;
	}
	
	
	/**
	 * Get set of test suites AT TOP LEVEL of tree on a Test Project
	 * @param testProjectId
	 * @throws TestLinkAPIException
	 */
	@SuppressWarnings("unchecked")
	protected TestSuite[] getFirstLevelTestSuitesForTestProject(Integer testProjectId)
	throws TestLinkAPIException
	{
		TestSuite[] testSuites = null;
		
		try {
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testProjectId.toString(), testProjectId);
			Object response = this.executeXmlRpcCall( TestLinkMethods.getFirstLevelTestSuitesForTestProject.toString(), executionData );
			
			Object[] responseArray = (Object[])response;
			testSuites = new TestSuite[ responseArray.length ];
			
			for (int i = 0; i < responseArray.length; i++) 
			{
				Map<String, Object> responseMap = (Map<String, Object>)responseArray[i];
				testSuites[i] = Util.getTestSuite( responseMap );
			}
		}
		catch (XmlRpcException xmlrpcex) 
		{
			throw new TestLinkAPIException(
					"Error retrieving test suites AT TOP LEVEL of tree on a Test Project: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return testSuites;
	}
}
