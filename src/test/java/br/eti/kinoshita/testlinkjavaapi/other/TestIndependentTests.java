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
package br.eti.kinoshita.testlinkjavaapi.other;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.0
 */
public class TestIndependentTests extends BaseTest {

    @DataProvider(name = "validUsers")
    public Object[][] createData() {
        return new Object[][] { { "admin" } };
    }

    @Test
    public void testCheckValidDevKey() {
        this.loadXMLRPCMockData("tl.checkDevKey.xml");

        boolean isValidKey = false;
        try {
            isValidKey = api.checkDevKey("someValue");
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertTrue(isValidKey);
    }

    @Test(expectedExceptions = { TestLinkAPIException.class })
    public void testCheckInvalidDevKey() throws TestLinkAPIException {
        this.loadXMLRPCMockData("tl.checkDevKey_invalid.xml");

        api.checkDevKey("" + System.currentTimeMillis());
    }

    @Test(dataProvider = "validUsers")
    public void testDoesUserExist(String user) {
        Boolean exists = false;

        try {
            exists = api.doesUserExist(user);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertTrue(exists);
    }

    @Test()
    public void testPingAndSayHello() {
        this.loadXMLRPCMockData("tl.ping.xml");

        String pingMessage = null;
        String sayHelloMessage = null;

        try {
            pingMessage = this.api.ping();
            sayHelloMessage = this.api.sayHello();
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(pingMessage);
        Assert.assertNotNull(sayHelloMessage);

        Assert.assertEquals(pingMessage, sayHelloMessage);

        Assert.assertEquals(pingMessage, "Hello!");
    }

    @Test
    public void testAbout() {
        this.loadXMLRPCMockData("tl.about.xml");

        String aboutMessage = null;

        try {
            aboutMessage = this.api.about();
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(aboutMessage);

        Assert.assertTrue(aboutMessage.contains("Testlink API Version:"));
    }

    @Test
    public void testSetTestMode() {
        this.loadXMLRPCMockData("tl.setTestMode.xml");

        Boolean expectedToBeTrue = false;

        try {
            expectedToBeTrue = this.api.setTestMode(Boolean.TRUE);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertTrue(expectedToBeTrue);
    }

    @Test
    public void testRepeat() {
        this.loadXMLRPCMockData("tl.repeat.xml");

        String strRepeated = null;
        try {
            strRepeated = this.api.repeat("B");
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(strRepeated);

        Assert.assertEquals(strRepeated, "You said: B");
    }

}
