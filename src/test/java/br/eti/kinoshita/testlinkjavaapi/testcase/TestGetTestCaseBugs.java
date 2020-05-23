package br.eti.kinoshita.testlinkjavaapi.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;

/**
 * From: https://github.com/kinow/testlink-java-api/issues/82
 */
public class TestGetTestCaseBugs extends BaseTest {
    @DataProvider(name = "testGetTestCaseBugsData")
    public Object[][] createDate() {
        return new Object[][] { { 1, 5, 3, 2 } };
    }

    @Test(dataProvider = "testGetTestCaseBugsData")
    public void addTestCaseKeyWords(Integer testCaseExternalId, Integer testPlanId, Integer testCaseId,
            Integer buildId) {
        this.loadXMLRPCMockData("tl.getTestCaseBugs.xml");
        List<Integer> o = api.getTestCaseBugs(testPlanId, testCaseId, testCaseExternalId, null, null, buildId, null);

        assertTrue(o.size() > 0);
        assertEquals(1, (int) o.get(0));
    }
}
