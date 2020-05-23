/*
 * The MIT License
 *
 * Copyright (c) <2011> <Mario Fuentes>
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
package br.eti.kinoshita.testlinkjavaapi.testcasesteps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 *
 * @author Mario Fuentes - http://www.rhiscom.com
 * @since 1.9.3-2
 */
public class TestDeleteTestCaseSteps extends BaseTest {
    @DataProvider(name = "testCaseStepData")
    public Object[][] createData() {
        List<TestCaseStep> steps = new ArrayList<>();
        steps.add(new TestCaseStep(1, // ID
                1, // Version
                1, // Step number
                "", // Actions
                "", // Expected Results
                true, // Active?
                ExecutionType.AUTOMATED // Execution type
        ));
        steps.add(new TestCaseStep(1, // ID
                1, // Version
                2, // Step number
                "", // Actions
                "", // Expected Results
                true, // Active?
                ExecutionType.AUTOMATED // Execution type
        ));

        return new Object[][] { { "1", steps } };
    }

    @Test(dataProvider = "testCaseStepData")
    public void testCreateTestCaseSteps(String testCaseExternalId, List<TestCaseStep> steps) {
        this.loadXMLRPCMockData("tl.createTestCaseSteps.xml");

        Map<String, Object> result = null;

        try {
            result = api.deleteTestCaseSteps(testCaseExternalId, 1, steps);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(result);
    }
}
