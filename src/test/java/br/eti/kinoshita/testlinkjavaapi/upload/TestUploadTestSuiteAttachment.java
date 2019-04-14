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
package br.eti.kinoshita.testlinkjavaapi.upload;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
public class TestUploadTestSuiteAttachment extends BaseTest {

    @DataProvider(name = "testSuiteAttachmentData")
    public Object[][] createData() {
        return new Object[][] { { 2, "Attachment title", "Attachment description", "attachmentFileName.txt",
                "text/plain", "QnJ1bm8=" } };
    }

    @Test(dataProvider = "testSuiteAttachmentData")
    public void testUploadTestSuiteAttachment(Integer testSuiteId, String title, String description, String fileName,
            String fileType, String content) {
        this.loadXMLRPCMockData("tl.uploadTestSuiteAttachment.xml");

        Attachment attachment = null;

        try {
            attachment = this.api.uploadTestSuiteAttachment(testSuiteId, title, description, fileName, fileType,
                    content);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(attachment);

        // TBD: open an issue because php XMLRPC API is not returning the ID.
        // assertTrue( attachment.getId() > 0);
    }

}
