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
package br.eti.kinoshita.testlink4j.util;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
public class AttachmentUtil
{
	
	private AttachmentUtil()
	{
		
	}
	
	public static final String getBase64EncodedContent( File file ) 
	throws IOException
	{
		
		AttachmentUtil.verifyAttachmentFile( file );
		
		String content = null;
		
		// TBD: read file content as Base64
		
		return content;
	}

	/**
	 * @param file
	 */
	private static void verifyAttachmentFile( File file ) 
	throws IOException
	{
		if ( file == null || !file.isFile() || !file.canRead() )
		{
			throw new IOException("Invalid attachment file ["+file+"]. Check if file exists and permissions set."); 
		}
	}

}
