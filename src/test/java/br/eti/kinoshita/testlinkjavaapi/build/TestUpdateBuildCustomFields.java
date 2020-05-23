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
package br.eti.kinoshita.testlinkjavaapi.build;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * @author Mohammad Azim Khan - https://github.com/mazimkhan
 * @since 1.9.16-0
 */
public class TestUpdateBuildCustomFields extends BaseTest {

    @DataProvider(name = "buildcfdata")
    public Object[][] createData() {
        return new Object[][] {
                { 2, 1, 3, "new", "Yahoo" }
        };
    }

    @Test(dataProvider = "buildcfdata")
    public void testUpdateBuildCustomFields(Integer buildId, Integer testProjectId, Integer testPlanId, String cfname, String cfvalue) {
        this.loadXMLRPCMockData("tl.updateBuildCustomFields.xml");

        java.util.Map<String, String> customFields = new HashMap<>();
        customFields.put(cfname, cfvalue);

        java.util.Map<String, java.lang.Object> response = null;

        try {
            response = api.updateBuildCustomFields(buildId, testProjectId, testPlanId, customFields);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(response);
        System.out.println(response.toString());
        Assert.assertTrue(Integer.parseInt(response.get("buildid").toString()) > 0);
        Assert.assertTrue(Integer.parseInt(response.get("testplanid").toString()) > 0);
        Assert.assertTrue(Integer.parseInt(response.get("testprojectid").toString()) > 0);
    }

}
