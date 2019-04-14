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
package br.eti.kinoshita.testlinkjavaapi.testcase;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.constants.ResponseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Rana Mansoor
 */
public class TestGetTestCaseCustomFieldExecutionValue extends BaseTest {

    @DataProvider(name = "testCaseWithCustomFieldExecution")
    public Object[][] createData() {
        return new Object[][]{{4, 1, 1, 2, 3, "SampleCustomField"}, {6, 1, 1, 2, 3, "SampleCustomField"}};
    }

    @Test(dataProvider = "testCaseWithCustomFieldExecution")
    public void testGetTestCaseCustomFieldExecutionValueFull(Integer testCaseId, Integer versionNumber, Integer executionId,
                                                             Integer testPlanId, Integer testProjectId, String customFieldName) {
        this.loadXMLRPCMockData("tl.getTestCaseCustomFieldExecutionValue.xml");

        CustomField customField = null;
        try {
            customField = this.api.getTestCaseCustomFieldExecutionValue(testCaseId, null, versionNumber,
                    executionId, testPlanId, testProjectId, customFieldName, ResponseDetails.FULL);


        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertNotNull(customField);

        Assert.assertNotNull(customField.getValue());

        Assert.assertTrue(customField.getValue().length() > 0);
    }

    @Test(dataProvider = "testCaseWithCustomFieldExecution")
    public void testGetTestCaseCustomFieldExecutionValueSimple(Integer testCaseId, Integer versionNumber, Integer executionId,
                                                               Integer testPlanId, Integer testProjectId, String customFieldName) {
        this.loadXMLRPCMockData("tl.getTestCaseCustomFieldExecutionValue.xml");

        CustomField customField = null;
        try {
            customField = this.api.getTestCaseCustomFieldExecutionValue(testCaseId, null, versionNumber,
                    executionId, testPlanId, testProjectId, customFieldName, ResponseDetails.SIMPLE);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertNotNull(customField);

        Assert.assertNotNull(customField.getValue());

        Assert.assertTrue(customField.getValue().length() > 0);
    }

    @Test(dataProvider = "testCaseWithCustomFieldExecution")
    public void testGetTestCaseCustomFieldExecutionValueValue(Integer testCaseId, Integer versionNumber, Integer executionId,
                                                              Integer testPlanId, Integer testProjectId, String customFieldName) {
        this.loadXMLRPCMockData("tl.getTestCaseCustomFieldExecutionValue.xml");

        CustomField customField = null;
        try {
            customField = this.api.getTestCaseCustomFieldExecutionValue(testCaseId, null, versionNumber, executionId,
                    testPlanId, testProjectId, customFieldName, ResponseDetails.VALUE);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertNotNull(customField);

        Assert.assertNotNull(customField.getValue());

        Assert.assertTrue(customField.getValue().length() > 0);
    }

}
