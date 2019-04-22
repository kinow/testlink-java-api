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

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * <ul>
 * <li>20101130 - BUGID: 3123764 - kinow - reportTCresult not returning execution data</li>
 * </ul>
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 */
public class TestReportTCResult extends BaseTest {

    @DataProvider(name = "buildData")
    public Object[][] createData() {
        return new Object[][] { { 4, 10, 1, "Sample build", "Build notes.", 2, "Post" } };
    }

    @Test(dataProvider = "buildData")
    public void testReportTCResult(Integer testCaseId, Integer testPlanId, Integer buildId, String buildName,
            String notes, Integer platformId, String platformName) {
        this.loadXMLRPCMockData("tl.reportTCResult.xml");

        ReportTCResultResponse response = null;
        try {
            response = this.api.reportTCResult(testCaseId, null, testPlanId, ExecutionStatus.FAILED, null, buildId,
                    buildName, notes, null, true, null, platformId, platformName, null, true, null, null);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(response);

        Assert.assertTrue(response.getExecutionId() > 0);
    }

    @Test(dataProvider = "buildData")
    public void testSetTestCaseExecutionResult(Integer testCaseId, Integer testPlanId, Integer buildId,
            String buildName, String notes, Integer platformId, String platformName) {
        this.loadXMLRPCMockData("tl.reportTCResult.xml");

        ReportTCResultResponse response = null;
        try {
            response = this.api.setTestCaseExecutionResult(testCaseId, null, testPlanId, ExecutionStatus.PASSED, null,
                    buildId, buildName, notes, null, true, null, platformId, platformName, null, true, null, null);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(response);

        Assert.assertTrue(response.getExecutionId() > 0);
    }

}
