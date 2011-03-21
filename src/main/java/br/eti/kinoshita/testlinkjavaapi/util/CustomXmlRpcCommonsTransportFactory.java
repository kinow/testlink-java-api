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
package br.eti.kinoshita.testlinkjavaapi.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcCommonsTransport;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.XmlRpcTransport;

/**
 * See author's page for further information.
 * 
 * @author Sujit Pal - http://sujitpal.blogspot.com/2010/05/debugging-xml-with-apache-xmlrpc.html
 * @since 1.9.1-1
 */
public class CustomXmlRpcCommonsTransportFactory extends
		XmlRpcCommonsTransportFactory
{

	private final Logger logger = Logger.getLogger(CustomXmlRpcCommonsTransportFactory.class.getPackage().getName());

	public CustomXmlRpcCommonsTransportFactory(XmlRpcClient pClient)
	{
		super(pClient);
	}

	@Override
	public XmlRpcTransport getTransport()
	{
		return new LoggingTransport(this);
	}

	private class LoggingTransport extends XmlRpcCommonsTransport
	{

		public LoggingTransport(CustomXmlRpcCommonsTransportFactory pFactory)
		{
			super(pFactory);
		}

		/**
		 * Logs the request content in addition to the actual work.
		 */
		@Override
		protected void writeRequest( final ReqWriter pWriter )
				throws XmlRpcException
		{
			super.writeRequest(pWriter);
			if (logger.isLoggable(Level.FINE))
			{
				CustomLoggingUtils.logRequest(logger, method.getRequestEntity());
			}
		}

		/**
		 * Logs the response from the server, and returns the contents of the
		 * response as a ByteArrayInputStream.
		 */
		@Override
		protected InputStream getInputStream() throws XmlRpcException
		{
			InputStream istream = super.getInputStream();
			if (logger.isLoggable(Level.FINE))
			{
				return new ByteArrayInputStream(CustomLoggingUtils.logResponse(
						logger, istream).getBytes());
			} else
			{
				return istream;
			}
		}
	}
}
