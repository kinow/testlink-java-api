package br.eti.kinoshita.testlinkjavaapi;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;

public class T {

	public static void main3(String[] args) throws Exception {
		TestLinkAPI api = new TestLinkAPI(new URL("http://localhost/testlink-1.9.3/lib/api/xmlrpc.php"), "9d7db1e2df412059fc14ffcf42d601f9");
		
		ReportTCResultResponse response = api.reportTCResult(
				3, 
				null, 
				5, 
				ExecutionStatus.BLOCKED, 
				4, 
				"tdc-1.0", 
				"My notes", 
				null, 
				null, 
				null, 
				"EC1", 
				null, 
				null);
		
		System.out.println(response);
		
		System.out.println("OK!");
	}
	
	public static void main(String[] args) 
	throws Exception
	{
		TestLinkAPI api = new TestLinkAPI(new URL("http://localhost/testlink-1.9.3/lib/api/xmlrpc.php"), "9d7db1e2df412059fc14ffcf42d601f9");
		
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put("devKey", "9d7db1e2df412059fc14ffcf42d601f9");
		executionData.put("testcaseid", 3);
		executionData.put("testcaseexternalid", null);
		executionData.put("version", null);
		HashMap<String, Object> os = (HashMap<String, Object>) ((Object[])api.executeXmlRpcCall("tl.getTestCase", executionData))[0];
		System.out.println(((Object[])api.executeXmlRpcCall("tl.getTestCase", executionData))[0]);
		System.out.println("# STEP 0 : " + ((Object[])os.get("steps"))[0]);
		
		TestCase tc = api.getTestCase(3, null, null);
		System.out.println("# STEPS SIZE: " + tc.getSteps().size());
		
		System.out.println();
		executionData.clear();
		executionData.put("devKey", "9d7db1e2df412059fc14ffcf42d601f9");
		executionData.put("testplanid", 5);
		executionData.put("testcaseid", 3);
		executionData.put("buildid", "");
		executionData.put("keywordid", "");
		executionData.put("keywords", "");
		executionData.put("executed", "");
		executionData.put("assignedto", "");
		executionData.put("executestatus", "");
		executionData.put("executiontype", "");
		executionData.put("getstepinfo", "");
		Object o = ((HashMap<?,?>)api.executeXmlRpcCall("tl.getTestCasesForTestPlan", executionData)).get("3");
		Object[] arr = ((Object[])o);
		System.out.println(arr[0]);
		
		System.out.println();
		executionData.clear();
		executionData.put("devKey", "9d7db1e2df412059fc14ffcf42d601f9");
		executionData.put("testsuiteid", 2);
		executionData.put("deep", null);
		executionData.put("details", null);
		System.out.println(((Object[])api.executeXmlRpcCall("tl.getTestCasesForTestSuite", executionData))[0]);
		
		System.out.println("OK!");
	}
	
}
