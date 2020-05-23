package br.eti.kinoshita.testlinkjavaapi.testplan;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * Tests the method removePlatformFromTestPlan.
 *
 * @since 1.9.6-0
 */
public class TestRemovePlatformFromTestPlan extends BaseTest {

    @DataProvider(name = "removePlatformFromTestPlan")
    public Object[][] createData() {
        return new Object[][] { { 1, 10, "browser" } };
    }

    @Test(dataProvider = "removePlatformFromTestPlan")
    public void testGetProjectPlatforms(Integer testProjectId, Integer testPlanId, String platformName) {
        this.loadXMLRPCMockData("tl.removePlatformFromTestPlan.xml");
        Map<String, Object> map = null;

        try {
            map = api.removePlatformFromTestPlan(testProjectId, testPlanId, platformName);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertNotNull(map);

        Assert.assertNotNull(map.get("msg"));
    }

}
