/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkMethods;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
class BuildService 
extends BaseService 
{

	/**
	 * @param xmlRpcClient XML RPC Client.
	 * @param devKey TestLink User DevKey.
	 */
	public BuildService( XmlRpcClient xmlRpcClient, String devKey ) 
	{
		super( xmlRpcClient, devKey );
	}

	@SuppressWarnings("unchecked")
	protected Build createBuild(Integer testPlanId, String buildName, String buildNotes) 
	throws TestLinkAPIException
	{
		Build build = null;
		
		Integer id = 0;
		
		build = new Build(
			id,
			testPlanId, 
			buildName, 
			buildNotes);
	
		try
		{
			Map<String, Object> executionData = Util.getBuildMap(build);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.createBuild.toString(), executionData);
			Object[] responseArray = (Object[])response;
			Map<String, Object> responseMap = (Map<String, Object>)responseArray[0];
			
			id = Util.getInteger(responseMap, TestLinkResponseParams.id.toString());
			build.setId( id );
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error creating build: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return build;
	}

	/**
	 * @param testPlanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Build[] getBuildsForTestPlan( Integer testPlanId ) 
	throws TestLinkAPIException
	{
		Build[] builds = null;
		
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put(TestLinkParams.testPlanId.toString(), testPlanId);
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getBuildsForTestPlan.toString(), executionData);
			if ( response instanceof Object[])
			{
				Object[] responseArray = (Object[])response;
				builds = new Build[ responseArray.length ];
				for (int i = 0; i < responseArray.length; i++)
				{
					Map<String, Object> responseMap = (Map<String, Object>)responseArray[i];
					builds[i] = Util.getBuild( responseMap );
				}
			}
			
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving test plan's builds: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return builds;
	}
	
	/**
	 * @param testPlanId
	 * @return
	 * @throws TestLinkAPIException
	 */
	@SuppressWarnings("unchecked")
	protected Build getLatestBuildForTestPlan( Integer testPlanId ) 
	throws TestLinkAPIException
	{
		Build build = null;
	
		try
		{
			Map<String, Object> executionData = new HashMap<String, Object>();
			executionData.put( TestLinkParams.testPlanId.toString(), testPlanId );
			Object response = this.executeXmlRpcCall(
					TestLinkMethods.getLatestBuildForTestPlan.toString(), executionData);
			if ( response instanceof Map<?, ?> )
			{
				Map<String, Object> responseMap = (Map<String, Object>)response;
				build = Util.getBuild( responseMap );
			}
		} 
		catch ( XmlRpcException xmlrpcex )
		{
			throw new TestLinkAPIException(
					"Error retrieving latest build for test plan: " + xmlrpcex.getMessage(), xmlrpcex);
		}
		
		return build;
	}
	
}
