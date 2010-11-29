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
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;

import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkTables;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * This class is responsible for managing test projects. 
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
class TestProjectService 
extends BaseService
{

	/**
	 * @param url
	 * @throws MalformedURLException
	 */
	public TestProjectService(String url, String devKey)
			throws MalformedURLException
	{
		super(url, devKey);
	}

	/**
	 * Creates a Test Project.
	 * 
	 * @return Created Test Project object.
	 */
	@SuppressWarnings("unchecked")
	protected TestProject createTestProject( 
		String testProjectName, 
		String testProjectPrefix, 
		String notes, 
		Boolean enableRequirements, 
		Boolean enableTestPriority, 
		Boolean enableAutomation, 
		Boolean enableInventory, 
		Boolean isActive, 
		Boolean isPublic
	) 
	throws TestLinkAPIException
	{
		TestProject testProject = null;
		
		Integer id = 0;
		
		testProject = new TestProject(
				id, 
				testProjectName, 
				testProjectPrefix, 
				notes, 
				enableRequirements, 
				enableTestPriority, 
				enableAutomation, 
				enableInventory, 
				isActive, 
				isPublic);
		
		try
		{
			Map<String, Object> executionData = Util.getTestProjectMap(testProject);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.createTestProject.toString(), executionData);
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			testProject.setId( id );
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error creating test project: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return testProject;
	}
	
	@SuppressWarnings("unchecked")
	protected TestProject getTestProjectByName(String projectName) 
	throws TestLinkAPIException
	{
		TestProject testProject = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testProjectName.toString(), projectName);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestProjectByName.toString(), executionData);
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			testProject = Util.getTestProject( responseMap );
		}
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error creating test project: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return testProject;
	}
	
	/**
	 * 
	 * @return
	 * @throws TestLinkAPIException
	 */
	@SuppressWarnings("unchecked")
	protected TestProject[] getProjects() 
	throws TestLinkAPIException
	{
		TestProject[] projects = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getProjects.toString(), executionData);
			Object[] responseArray = (Object[])response;
			projects = new TestProject[responseArray.length];
			for (int i = 0; i < responseArray.length; i++)
			{
				Map<String, Object> projectMap = (Map<String, Object>)responseArray[i];
				TestProject testProject = Util.getTestProject(projectMap);
				projects[i] = testProject;
			}
			
		}
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving test projects: " + xmlrpcex.getMessage(), xmlrpcex);
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
	@SuppressWarnings("unchecked")
	protected TestPlan[] getProjectTestPlans(Integer projectId) 
	throws TestLinkAPIException
	{
		TestPlan[] testPlans = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testProjectId.toString(), projectId);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getProjectTestPlans.toString(), executionData);
			Object[] responseArray = (Object[])response;
			testPlans = new TestPlan[responseArray.length];
			for (int i = 0; i < responseArray.length; i++)
			{
				Map<String, Object> planMap = (Map<String, Object>)responseArray[i];
				TestPlan testPlan = Util.getTestPlan(planMap);
				testPlans[i] = testPlan;
			}
			
		}
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving test plans: " + xmlrpcex.getMessage(), xmlrpcex);
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
	@SuppressWarnings("unchecked")
	protected Attachment uploadTestProjectAttachment( 
		Integer testProjectId,
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
			testProjectId, 
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
			Map<String, Object> executionData = Util.getTestProjectAttachmentMap(attachment);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.uploadTestProjectAttachment.toString(), executionData);
			Map<String, Object> responseMap = (Map<String, Object>)response;
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			attachment.setId(id);
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error uploading attachment for test project: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return attachment;
	}
	
}
