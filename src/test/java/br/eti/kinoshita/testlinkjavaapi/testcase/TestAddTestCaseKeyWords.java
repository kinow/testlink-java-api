package br.eti.kinoshita.testlinkjavaapi.testcase;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spravallika on 25/06/17.
 */
public class TestAddTestCaseKeyWords extends BaseTest {
    @DataProvider(name = "testCaseKeywordsData")
    public Object[][] createDate() {
        return new Object[][]{{"1", "Sanity"}};
    }

    @Test(dataProvider = "testCaseKeywordsData")
    public void addTestCaseKeyWords(String testCaseExternalId, String keyword) {
        this.loadXMLRPCMockData("tl.addTestCaseKeyWords.xml");

        Map<String, List<String>> testcaseKeywordsMap = new HashMap<>();
        testcaseKeywordsMap.put(testCaseExternalId, Lists.newArrayList(keyword));
        Map<String, Object> response = null;
        try {
            response = api.addTestCaseKeywords(testcaseKeywordsMap);
        } catch (TestLinkAPIException e) {
            Assert.fail(e.getMessage(), e);
        }

        Assert.assertEquals(response.get("status_ok").toString(), "true");
        Assert.assertNotNull(response);
    }
}
