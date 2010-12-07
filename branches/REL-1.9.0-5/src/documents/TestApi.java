package testlink.api.java.client.tests;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIConst;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIHelper;
import testlink.api.java.client.TestLinkAPIResults;
import testlink.api.java.client.TestLinkTestProject;


public class TestApi
{        
	
	public static final String SERVER_URL = "http://localhost/testlink19/lib/api/xmlrpc.php";
	public static final String DEV_KEY = "831e2e35461fc7dd381e0f5c762cddf5";
	
	public static void test()

	{
		TestLinkAPIClient apiClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);

		String projectName = "hello project";
		String projectid = "HP";
		String suiteName = "hello test suite";
		String testCaseName = "hello test case";
		String planName = "test plan auto";
		String buildName = "hello build";
		
	
		try 
		{
    		TestLinkAPIResults projects = apiClient.getProjects();
    		System.out.println(projects);

    		
    		System.out.println("------------------------------------------------");
    		
			TestLinkTestProject project = apiClient.createTestProject(projectName, projectid, "no desc", true, true, true, true, true, true);
			int idP = project.getProjectID();
    		System.out.println("id projet : "+idP);
    		Integer testProjectID = TestLinkAPIHelper.getProjectID(apiClient, projectName);
    		System.out.println("id projet : "+testProjectID);

    		
			System.out.println("------------------------------------------------");
			
    		
			int idS = apiClient.createTestSuite(projectName, suiteName, "no desc");
    		System.out.println("id test suite : "+idS);
    		System.out.println("id test suite : "+TestLinkAPIHelper.getSuiteID(apiClient, projectName, suiteName));
    		
    		
    		System.out.println("------------------------------------------------");

    		/* TODO 
    		 * ************************************* *
    		 * ************************************* *
    		 * The test case's steps are not created *
    		 * ************************************* *
    		 * ************************************* *
    		 */
    		List<HashMap<String, Object>> steps = new ArrayList<HashMap<String, Object>>();
    		HashMap<String, Object> step1 = new HashMap<String, Object>();
    		step1.put("step_number", 1);
    		step1.put("actions", "<p>step 1 : see a person</p>");
    		step1.put("expected_results", "A person");
    		step1.put("execution_type", TestLinkAPIConst.TESTCASE_EXECUTION_TYPE_AUTO);
    		steps.add(step1);
    		
    		HashMap<String, Object> step2 = new HashMap<String, Object>();
    		step2.put("step_number", 1);
    		step2.put("actions", "<p>step 2 : say hello</p>");
    		step2.put("expected_results", "A person happy");
    		step2.put("execution_type", TestLinkAPIConst.TESTCASE_EXECUTION_TYPE_MANUAL);
    		steps.add(step2);
    		int idC = apiClient.createTestCase("admin", 
					projectName, 
					suiteName, 
					testCaseName, 
					"coucou", 
					steps, 
					"satisfaction", 
					TestLinkAPIConst.MEDIUM);
    		
    		
    		
    		System.out.println("id test Case : "+idC);
    		Integer testCaseID = TestLinkAPIHelper.getTestCaseID(apiClient, testProjectID, testCaseName);
			System.out.println("id test Case : "+testCaseID);
    		System.out.println("info test Case : "+TestLinkAPIHelper.getTestCaseInfo(apiClient, testProjectID,testCaseID));
    		
    		
    		System.out.println("------------------------------------------------");

			
			callCreatePlan(DEV_KEY, projectName, planName, "api test");
			Integer testPlanID = TestLinkAPIHelper.getPlanID(apiClient, testProjectID, planName);
			System.out.println("id test plan : "+testPlanID);
			System.out.println("info test plan : "+TestLinkAPIHelper.getPlanInfo(apiClient, testProjectID, planName));

			
			System.out.println("------------------------------------------------");
			
			try{
				apiClient.addTestCaseToTestPlan(testProjectID,
						testPlanID,
    				testCaseID,
    				projectid+"-1",1,2,TestLinkAPIConst.MEDIUM    				
    				);

    		}catch (Exception e) {
				System.err.println("error : addTestCaseToTestPlan");
			}
        		
    		System.out.println("------------------------------------------------");
    		
    		
			apiClient.createBuild(projectName, planName, buildName, "no notes");
    		
    		System.out.println("id build : "+TestLinkAPIHelper.getBuildID(apiClient, testPlanID, buildName));
    		
    		
    		System.out.println("------------------------------------------------");
    		
    		apiClient.reportTestCaseResult(projectName, 
    				planName, 
    				projectid+"-1", 
    				buildName, 
    				"test api", 
    				TestLinkAPIConst.TEST_PASSED);
    		
            
		} catch (TestLinkAPIException e) {
//			e.printStackTrace();
		}

	}

	public static Object[] callCreatePlan(String DEV_KEY, String Projectname, 
			String testplanname, String notes) {
		try {
			XmlRpcClient rpcClient;
			XmlRpcClientConfigImpl config;
			config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(SERVER_URL));
			rpcClient = new XmlRpcClient();
			rpcClient.setConfig(config);

			ArrayList<Object> params = new ArrayList<Object>();
			Hashtable<String, Object> methodData = new Hashtable<String, Object>();
			methodData.put("devKey", DEV_KEY);
			methodData.put("testprojectname", Projectname);
			methodData.put("testplanname", testplanname);
			methodData.put("notes", notes);
			params.add(methodData);
			Object[] result = (Object[]) rpcClient.execute("tl.createTestPlan",
					params);
			return result;
		} catch (Exception e) {
			System.out.println("erreur : "+e.getMessage());
			System.out.println(e.getStackTrace()[0]);	
			return null;
		}
	}


	public static void main(String[] args) 
	{
		// Substitute this for a valid tcid and tpid within your project

		TestApi.test();        
	}

}