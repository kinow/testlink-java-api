/*
 * The MIT License
 *
 * Copyright (c) 2010 Bruno P. Kinoshita http://www.kinoshita.eti.br
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * TestLink tests base class.
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
public class BaseTest {

    /**
     * API.
     */
    protected TestLinkAPI api;

    /**
     * Test HTTP server. Used for mocked tests.
     */
    protected HttpTestServer server;

    /**
     * Whether the tests are being mocked, and XML with the prepared response are being used.
     */
    protected boolean mocked = true;

    /**
     * Set up method that creates the instance of the TestLink API.
     * @throws Exception for convenience
     */
    @BeforeClass
    protected void setUp() throws Exception {
        this.mocked = "true".equals(System.getProperty("mocked", "true"));

        if (!mocked) {
            this.api = new TestLinkAPI(new URL("http://localhost/testlink-1.9.6/lib/api/xmlrpc.php"),
                    "09b83b6813a55ef6f7e2d7d63cb6f65c");
        } else {
            this.server = new HttpTestServer();
            this.loadXMLRPCMockData("tl.checkDevKey.xml");
            this.server.start();

            this.api = new TestLinkAPI(
                    new URL("http://localhost:" + this.server.getPort() + "/testlink/lib/api/xmlrpc.php"), "devKey");
        }
    }

    public void loadXMLRPCMockData(String xmlFile) {
        if (mocked) {
            URL url = getClass().getResource("/br/eti/kinoshita/testlinkjavaapi/testdata/" + xmlFile);
            File file = FileUtils.toFile(url);
            String mockXml;
            try {
                mockXml = FileUtils.readFileToString(file, Charset.defaultCharset());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.server.setMockResponseBody(mockXml);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        if (this.server != null) {
            this.server.stop();
        }
    }

}
