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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import br.eti.kinoshita.testlinkjavaapi.model.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.util.Util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
abstract class BaseService
{
	
	private static final Integer FALSE_IN_PHP = 0;
	
	/**
	 * XML-RPC client.
	 */
	private XmlRpcClient xmlRpcClient;
	
	/**
	 * TestLink User devkey.
	 */
	private String devKey;
	
	/**
	 * @param xmlRpcClient XML-RPC Client.
	 * @param devKey TestLink user DevKey.
	 */
	public BaseService( XmlRpcClient xmlRpcClient, String devKey ) 
	{
		this.xmlRpcClient = xmlRpcClient;
		this.devKey = devKey;
	}

	/**
	 * Executes the XML-RPC call to the method in the server, passing the 
	 * execution data map.
	 * 
	 * @param methodName name of the method.
	 * @param executionData execution data map. 
	 * @return Server response.
	 * @throws XmlRpcException
	 */
	public Object executeXmlRpcCall( 
			String methodName, 
			Map<String, Object> executionData ) 
	throws XmlRpcException, TestLinkAPIException
	{
		List<Object> params = new ArrayList<Object>();
		
		if ( executionData != null )
		{
			if ( executionData.get(TestLinkParams.devKey.toString()) == null )
			{
				executionData.put(TestLinkParams.devKey.toString(), this.devKey);
			}
			params.add( executionData );
		}
		
		Object o = this.xmlRpcClient.execute( methodName, params );
		
		this.checkResponseError( o );
		
		return o;
	}

	/**
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	protected void checkResponseError( Object response ) 
	throws TestLinkAPIException
	{
		if ( response instanceof Object[] ) // may be an array of errors (IXError)
		{
			Object[] responseArray = Util.castToArray(response);
			
			for (int i = 0; i < responseArray.length; i++)
			{
				Object maybeAMap = responseArray[i];
				if ( maybeAMap instanceof Map<?, ?> ) // may be a map with error code and message
				{
					Map<String, Object> errorMap = (Map<String, Object>)maybeAMap;
					Integer code = Util.getInteger(errorMap, "code");
					String message = Util.getString(errorMap, "message");
					
					if ( code != null )
					{
						throw new TestLinkAPIException(code, message);
					}

					
				} // endif
			} // endfor
			
		} // endif
		
		else if ( response instanceof Map<?, ?> )
		{
			Map<String, Object> errorMap = (Map<String, Object>)response;
			
			Integer statusOk = Util.getInteger(errorMap, "status_ok");
			String message = Util.getString(errorMap, "msg");
			
			if ( statusOk != null && statusOk == FALSE_IN_PHP )
			{
				throw new TestLinkAPIException(statusOk, message);
			}
		}
	}
	
}
