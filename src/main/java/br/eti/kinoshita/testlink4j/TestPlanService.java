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

import br.eti.kinoshita.testlink4j.model.Platform;
import br.eti.kinoshita.testlink4j.model.TestPlan;
import br.eti.kinoshita.testlink4j.util.TestLinkMethods;
import br.eti.kinoshita.testlink4j.util.TestLinkParams;
import br.eti.kinoshita.testlink4j.util.TestLinkResponseParams;
import br.eti.kinoshita.testlink4j.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
class TestPlanService 
extends BaseService
{

	/**
	 * @param url
	 * @param devKey
	 * @throws MalformedURLException
	 */
	protected TestPlanService(String url, String devKey)
			throws MalformedURLException
	{
		super(url, devKey);
	}

	@SuppressWarnings("unchecked")
	protected TestPlan createTestPlan(
			String planName,
			String projectName,
			String notes, 
			Boolean isActive,  
			Boolean isPublic) 
	throws TestLinkAPIException
	{
		TestPlan testPlan = null;
		
		Integer id = 0;
		
		testPlan = new TestPlan(
				id,
				planName, 
				projectName, 
				notes, 
				isActive, 
				isPublic);
		
		try
		{
			Map<String, Object> executionData = Util.getTestPlanMap(testPlan);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.createTestPlan.toString(), executionData);
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			testPlan.setId( id );
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error creating test plan: " + xmlrpcex.getMessage(), xmlrpcex);
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
	@SuppressWarnings("unchecked")
	protected TestPlan getTestPlanByName(String planName, String projectName) 
	throws TestLinkAPIException 
	{
		TestPlan testPlan = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testPlanName.toString(), planName);
			executionData.put(TestLinkParams.testProjectName.toString(), projectName);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestPlanByName.toString(), executionData);
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			//TBD: check with TL team if we can change it there.
			responseMap.put(TestLinkResponseParams.projectName.toString(), projectName);
			testPlan = Util.getTestPlan( responseMap );
		}
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error creating test project: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return testPlan;
	}

	/**
	 * @param planId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Platform[] getTestPlanPlatforms(Integer planId)
	throws TestLinkAPIException
	{
		Platform[] platforms = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testPlanId.toString(), planId);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTestPlanPlatforms.toString(), executionData);
			Object[] responseArray = (Object[])response;
			platforms = new Platform[responseArray.length];
			for (int i = 0; i < responseArray.length; i++)
			{
				Map<String, Object> projectMap = (Map<String, Object>)responseArray[i];
				Platform platform = Util.getPlatform(projectMap);
				platforms[i] = platform;
			}
			
		}
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving platforms: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return platforms;
	}

	/**
	 * @param testPlanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getTotalsForTestPlan( Integer testPlanId ) 
	throws TestLinkAPIException
	{
		
		Map<String, Object> responseMap = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testPlanId.toString(), testPlanId);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getTotalsForTestPlan.toString(), executionData);
			if ( response instanceof Object[] )
			{
				Object[] responseArray = (Object[])response;
				responseMap = ( Map<String, Object> ) responseArray[0];
			} 
			else if ( response instanceof Map<?, ?> )
			{
				responseMap = ( Map<String, Object> ) response;
			}
		}
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving platforms: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return responseMap;
		
	}

}
