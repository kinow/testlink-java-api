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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;

/**
 * See author's page for further information.
 * 
 * @author Sujit Pal - http://sujitpal.blogspot.com/2010/05/debugging-xml-with-apache-xmlrpc.html
 * @since 1.9.1-1
 */
public class CustomLoggingUtils {

	  public static void logRequest(Logger logger, 
	      RequestEntity requestEntity) throws XmlRpcException {
	    ByteArrayOutputStream bos = null;
	    try {
	      logger.debug("---- Request ----");
	      bos = new ByteArrayOutputStream();
	      requestEntity.writeRequest(bos);
	      logger.debug(toPrettyXml(logger, bos.toString()));
	    } catch (IOException e) {
	      throw new XmlRpcException(e.getMessage(), e);
	    } finally {
	      IOUtils.closeQuietly(bos);
	    }
	  }

	  public static void logRequest(Logger logger, String content) {
	    logger.debug("---- Request ----");
	    logger.debug(toPrettyXml(logger, content));
	  }

	  public static String logResponse(Logger logger, InputStream istream) 
	      throws XmlRpcException {
	    BufferedReader reader = null;
	    try {
	      reader = new BufferedReader(new InputStreamReader(istream));
	      String line = null;
	      StringBuilder respBuf = new StringBuilder();
	      while ((line = reader.readLine()) != null) {
	        respBuf.append(line);
	      }
	      String response = respBuf.toString();
	      logger.debug("---- Response ----");
	      logger.debug(toPrettyXml(logger, respBuf.toString()));
	      return response;
	    } catch (IOException e) {
	      throw new XmlRpcException(e.getMessage(), e);
	    } finally {
	      IOUtils.closeQuietly(reader);
	    }
	  }

	  public static void logResponse(Logger logger, String content) {
	    logger.debug("---- Response ----");
	    logger.debug(toPrettyXml(logger, content));
	  }

	  private static String toPrettyXml(Logger logger, String xml) {
	    try {
	      Transformer transformer = 
	        TransformerFactory.newInstance().newTransformer();
	      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	      transformer.setOutputProperty(
	        "{http://xml.apache.org/xslt}indent-amount", "2");
	      StreamResult result = new StreamResult(new StringWriter());
	      StreamSource source = new StreamSource(new StringReader(xml));
	      transformer.transform(source, result);
	      return result.getWriter().toString();
	    } catch (Exception e) {
	      logger.warn("Can't parse XML");
	      return xml;
	    }
	  }
	}
