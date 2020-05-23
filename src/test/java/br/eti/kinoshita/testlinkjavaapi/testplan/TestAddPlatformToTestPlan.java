package br.eti.kinoshita.testlinkjavaapi.testplan;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * Tests the method addPlatformToTestPlan.
 *
 * @since 1.9.6-0
 */
public class TestAddPlatformToTestPlan extends BaseTest {

    @DataProvider(name = "addPlatformToTestPlan")
    public Object[][] createData() {
        return new Object[][] { { 1, 10, "browser" } };
    }

    @Test(dataProvider = "addPlatformToTestPlan")
    public void testGetProjectPlatforms(Integer testProjectId, Integer testPlanId, String platformName) {
        this.loadXMLRPCMockData("tl.addPlatformToTestPlan.xml");
        Map<String, Object> map = null;

        try {
            map = api.addPlatformToTestPlan(testProjectId, testPlanId, platformName);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(map);

        Assert.assertNotNull(map.get("msg"));
    }

}
