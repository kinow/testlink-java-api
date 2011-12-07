/*
 * The MIT License
 *
 * Copyright (c) <2010> <Bruno P. Kinoshita>
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
package br.eti.kinoshita.testlinkjavaapi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.model.Execution;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.Requirement;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;

/**
 * 
 * <p>Utility class with methods to handle the response or prepare 
 * the request to the PHP XML-RPC API. This class is able to convert 
 * from a Map to an Object and vice-versa.</p>
 * 
 * <p>
 * <ul>
 * <li>20101129 - BUGID: 3122394 - kinow - 
 * 			Invalid type when passing ExecutionType as param</li>
 * <li>20101130 - BUGID: 3123764 - kinow - 
 * 		reportTCresult not returning execution data</li>
 * </p>
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class Util
{
	public static final Object[] EMPTY_ARRAY = new Object[0];

	private Util(){}
	
	/**
	 * @param project
	 * @return Map of Test Project
	 */
	public static final Map<String, Object> getTestProjectMap(TestProject project)
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.testProjectName.toString(), project.getName());
		executionData.put(TestLinkParams.testCasePrefix.toString(), project.getPrefix());
		executionData.put(TestLinkParams.notes.toString(), project.getNotes());
		
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(TestLinkParams.enableRequirements.toString(), project.isEnableRequirements());
		options.put(TestLinkParams.enableTestPriority.toString(), project.isEnableTestPriority());
		options.put(TestLinkParams.enableAutomation.toString(), project.isEnableAutomation());
		options.put(TestLinkParams.enableInventory.toString(), project.isEnableInventory());
		
		executionData.put(TestLinkParams.options.toString(), options);
		
		executionData.put(TestLinkParams.active.toString(), project.isActive());
		executionData.put(TestLinkParams.public_.toString(), project.isPublic());

		return executionData;
	}

	/**
	 * Extracts a Test Project from a Map.
	 * 
	 * @param map Map with properties of a Test Project.
	 * @return Test Project.
	 */
	@SuppressWarnings("unchecked")
	public static final TestProject getTestProject(Map<String, Object> map)
	{
		TestProject testProject = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					testProject = new TestProject();
					testProject.setId( id );
					
					testProject.setName( getString(map, TestLinkResponseParams.name.toString()) );
					testProject.setPrefix( getString(map, TestLinkResponseParams.prefix.toString() ) );
					testProject.setNotes( getString(map, TestLinkResponseParams.notes.toString() ) );
					
					Map<String, Object> optMap = (Map<String, Object>)map.get(TestLinkResponseParams.opt.toString());
					testProject.setEnableAutomation( getBoolean(optMap, TestLinkResponseParams.automationEnabled.toString()));
					testProject.setEnableRequirements( getBoolean(optMap, TestLinkResponseParams.requirementsEnabled.toString()));
					testProject.setEnableTestPriority( getBoolean(optMap, TestLinkResponseParams.testPriorityEnabled.toString()));
					testProject.setEnableInventory( getBoolean(optMap, TestLinkResponseParams.inventoryEnabled.toString()));
					
					testProject.setActive( getBoolean(map, TestLinkResponseParams.active.toString()));
					testProject.setPublic( getBoolean(map, TestLinkResponseParams.isPublic.toString()));
				}
				
			}			
		}
		return testProject;
	}
	
	/**
	 * @param map
	 * @param key
	 * @return Boolean value.
	 */
	public static Boolean getBoolean( Map<String, Object> map, String key )
	{
		Boolean booleanObj = null;
		Integer integer = getInteger(map, key);
		if ( integer != null )
		{
			if ( integer == 0 )
			{
				booleanObj = Boolean.FALSE;
			} else 
			{
				booleanObj = Boolean.TRUE;
			}
		}
		return booleanObj;
	}

	/**
	 * @param map
	 * @param key
	 * @return String value.
	 */
	public static final String getString( Map<String, Object> map, String key )
	{
		String string = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get(key);
			if ( o != null )
			{
				string =  o.toString();
			}
		}
		return string;
	}
	
	
	/**
	 * @param map
	 * @param key
	 * @return Integer value.
	 */
	public static final Integer getInteger( Map<String, Object> map, String key )
	{
		Integer integer = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get(key);
			if ( o != null )
			{
				try
				{
					integer = Integer.parseInt( o.toString() );
				} catch (NumberFormatException nfe)
				{
					integer = null;
				}
			}
		}
		return integer;
	}

	/**
	 * @param plan
	 * @return Map of Test Plan.
	 */
	public static final Map<String, Object> getTestPlanMap( TestPlan plan )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.testPlanName.toString(), plan.getName());
		executionData.put(TestLinkParams.testProjectName.toString(), plan.getProjectName());
		executionData.put(TestLinkParams.notes.toString(), plan.getNotes());
		executionData.put(TestLinkParams.active.toString(), plan.isActive());
		executionData.put(TestLinkParams.public_.toString(), plan.isPublic());
		return executionData;
	}

	/**
	 * @param map
	 * @return Test Plan.
	 */
	public static final TestPlan getTestPlan( Map<String, Object> map )
	{
		TestPlan testPlan = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					testPlan = new TestPlan();
					testPlan.setId( id );
					
					testPlan.setName( getString(map, TestLinkResponseParams.name.toString()) );
					testPlan.setProjectName( getString(map, TestLinkResponseParams.projectName.toString() ) );
					testPlan.setNotes( getString(map, TestLinkResponseParams.notes.toString() ) );
					
					testPlan.setActive( getBoolean(map, TestLinkResponseParams.active.toString()));
					testPlan.setPublic( getBoolean(map, TestLinkResponseParams.isPublic.toString()));
				}
				
			}			
		}
		return testPlan;
	}

	/**
	 * @param map
	 * @return Platform.
	 */
	public static final Platform getPlatform(Map<String, Object> map) 
	{
		Platform platform = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					platform = new Platform();
					platform.setId( id );
					
					platform.setName( getString(map, TestLinkResponseParams.name.toString()) );
					platform.setNotes( getString(map, TestLinkResponseParams.notes.toString()) );
				}
				
			}			
		}
		return platform;
	}

	/**
	 * @param testCase
	 * @return Map of Test Case.
	 */
	public static final Map<String, Object> getTestCaseMap(TestCase testCase) 
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.testCaseName.toString(), testCase.getName());
		executionData.put(TestLinkParams.testSuiteId.toString(), testCase.getTestSuiteId());
		executionData.put(TestLinkParams.testProjectId.toString(), testCase.getTestProjectId());
		executionData.put(TestLinkParams.authorLogin.toString(), testCase.getAuthorLogin());
		executionData.put(TestLinkParams.summary.toString(), testCase.getSummary());
				
		List<Map<String, Object>> steps = getTestCaseStepsMap(testCase.getSteps());
		executionData.put(TestLinkParams.steps.toString(), steps);
		
		executionData.put(TestLinkParams.preconditions.toString(), testCase.getPreconditions());
		executionData.put(TestLinkParams.importance.toString(), Util.getStringValueOrNull(testCase.getTestImportance()));
		executionData.put(TestLinkParams.execution.toString(), Util.getStringValueOrNull( testCase.getExecutionType()));
		executionData.put(TestLinkParams.order.toString(), testCase.getOrder());
		executionData.put(TestLinkParams.internalId.toString(), testCase.getInternalId());
		executionData.put(TestLinkParams.checkDuplicatedName.toString(), testCase.getCheckDuplicatedName());
		executionData.put(TestLinkParams.actionOnDuplicatedName.toString(), testCase.getActionOnDuplicatedName());

		return executionData;
	}
	
	/**
	 * 
	 * @param testCaseSteps
	 * @return A list whit one Map for each TestCaseStep
	 * @since 1.9.4-1
	 */
	public static final List<Map<String, Object>> getTestCaseStepsMap(List<TestCaseStep> testCaseSteps)
	{
		List<Map<String, Object>> steps = new ArrayList<Map<String, Object>>();
		
		if ( testCaseSteps != null && testCaseSteps.size() > 0 )
		{
			/*
			for(TestCaseStep step : testCaseSteps)
				steps.add(getTestCaseStepMap(step));
			*/
			
			// Why uses an iterator over a foreach?
			for (
				Iterator<TestCaseStep> iterator = testCaseSteps.iterator(); 
				iterator.hasNext();
				) 
			{
				TestCaseStep testCaseStep = iterator.next();
				Map<String, Object> testCaseStepMap = getTestCaseStepMap(testCaseStep, true);
				steps.add( testCaseStepMap );
			}
		}
		
		return steps;
	}
	
	/**
	 * 
	 * @param testCaseSteps
	 * @return A list with the step's id
	 * @since 1.9.4-1
	 */
	public static final List<Integer> getTestCaseStepsIdList(List<TestCaseStep> testCaseSteps)
	{
		List<Integer> steps = new ArrayList<Integer>();
		
		if ( testCaseSteps != null && testCaseSteps.size() > 0 )
		{
			/*
			for (TestCaseStep step : testCaseSteps)
				steps.add(step.getId());
			*/
			
			// Why uses an iterator over a foreach?
			for (
				Iterator<TestCaseStep> iterator = testCaseSteps.iterator(); 
				iterator.hasNext();
				) 
			{
				TestCaseStep testCaseStep = iterator.next();
				steps.add( testCaseStep.getNumber() );
			}
		}
		
		return steps;
	}

	/**
	 * @param Test Case Step map
	 * @return Test Case
	 */
	public static TestCaseStep getTestCaseStep( Map<String, Object> map )
	{
		TestCaseStep step = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					step = new TestCaseStep();
					step.setId( id );
					step.setActions( getString(map, TestLinkResponseParams.actions.toString()));
					step.setActive( getBoolean(map, TestLinkResponseParams.active.toString()) );
					Integer executionTypeValue = getInteger( map, TestLinkResponseParams.executionType.toString() );
					ExecutionType execution = ExecutionType.getExecutionType( executionTypeValue );
					step.setExecutionType( execution );
					step.setExpectedResults( getString(map, TestLinkResponseParams.expectedResults.toString()) );
					step.setNumber( getInteger(map, TestLinkResponseParams.stepNumber.toString()));
				}
				
			}			
		}
		return step;
	}
	
	/**
	 * 
	 * @param testCaseStep
	 * @return Map of Test Case Step.
	 */
	public static final Map<String, Object> getTestCaseStepMap(TestCaseStep testCaseStep) 
	{
		return getTestCaseStepMap(testCaseStep, false);
	}
	
	/**
	 * @param testCaseStep
	 * @param internal the API uses different names for the the same parameter in different methods.
	 * @return Map of Test Case Step.
	 */
	public static final Map<String, Object> getTestCaseStepMap(TestCaseStep testCaseStep, boolean internal) 
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.stepNumber.toString(), testCaseStep.getNumber());
		executionData.put(TestLinkParams.actions.toString(), testCaseStep.getActions());
		executionData.put(TestLinkParams.expectedResults.toString(), testCaseStep.getExpectedResults());
		if (internal)
			executionData.put(TestLinkParams.stepExecutionType.toString(), testCaseStep.getExecutionType().getValue());
		else
			executionData.put(TestLinkParams.executionType.toString(), testCaseStep.getExecutionType());
		
		return executionData;
	}

	/**
	 * @param testSuite
	 * @return Map of Test Suite Map.
	 */
	public static final Map<String, Object> getTestSuiteMap( TestSuite testSuite )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.testSuiteName.toString(), testSuite.getName());
		executionData.put(TestLinkParams.testProjectId.toString(), testSuite.getTestProjectId());
		executionData.put(TestLinkParams.parentId.toString(), testSuite.getParentId());
		executionData.put(TestLinkParams.details.toString(), testSuite.getDetails());
		executionData.put(TestLinkParams.order.toString(), testSuite.getOrder());
		executionData.put(TestLinkParams.checkDuplicatedName.toString(), testSuite.getCheckDuplicatedName());
		executionData.put(TestLinkParams.actionOnDuplicatedName.toString(), testSuite.getActionOnDuplicatedName());
		return executionData;
	}

	/**
	 * @param map
	 * @return Test Suite.
	 */
	public static final TestSuite getTestSuite(Map<String, Object> map)
	{
		TestSuite testSuite = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					testSuite = new TestSuite();
					testSuite.setId( id );
					
					testSuite.setDetails( getString(map, TestLinkResponseParams.details.toString()) );
					testSuite.setName( getString(map, TestLinkResponseParams.name.toString() ) );
					testSuite.setParentId( getInteger(map, TestLinkResponseParams.parentId.toString() ) );
					testSuite.setOrder( getInteger(map, TestLinkResponseParams.order.toString() ) );
				}
				
			}			
		}
		return testSuite;
	}

	/**
	 * @param map
	 * @return Test Case.
	 */
	@SuppressWarnings("unchecked")
	public static final TestCase getTestCase(Map<String, Object> map) 
	{
		TestCase testCase = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					testCase = new TestCase();
					testCase.setId( id );
					testCase.setVersionId( getInteger(map, TestLinkResponseParams.testCaseVersionId.toString()) );
					testCase.setVersion( getInteger(map, TestLinkResponseParams.version.toString() ) );
					testCase.setPreconditions( getString(map, TestLinkResponseParams.preconditions.toString()));
					testCase.setSummary( getString(map, TestLinkResponseParams.summary.toString()) );
					testCase.setParentId( getInteger(map, TestLinkResponseParams.parentId.toString() ) );
					testCase.setOrder( getInteger(map, TestLinkResponseParams.order.toString() ) );
					testCase.setExecutionOrder( getInteger(map, TestLinkResponseParams.executionOrder.toString()));
					testCase.setName( getString(map, TestLinkResponseParams.name.toString()) );
 					Integer executionTypeValue = getInteger( map, TestLinkResponseParams.executionType.toString() );
					ExecutionType execution = ExecutionType.getExecutionType( executionTypeValue );
					testCase.setExecutionType( execution );
					ExecutionStatus executionStatus = ExecutionStatus.NOT_RUN;
					String executionStatusText = getString(map, TestLinkResponseParams.execStatus.toString() );
					if ( StringUtils.isNotBlank(executionStatusText) )
					{
						executionStatus = ExecutionStatus.getExecutionStatus(executionStatusText.charAt(0));
					} 
					testCase.setExecutionStatus(executionStatus);
					testCase.setTestProjectId(getInteger(map, TestLinkParams.testProjectId.toString()));
					testCase.setTestSuiteId(getInteger(map, "testsuite_id")); // TBD: inconsistent parameter name
					// TODO: check if TL 2.0 allows it
//					CustomField[] customFields = (CustomField[])getArray(map, TestLinkResponseParams.customFields.toString());
//					if ( customFields != null )
//					{
//						for (int i = 0; i < customFields.length; i++)
//						{
//							CustomField customField = customFields[i];
//							testCase.getCustomFields().add( customField );
//						}
//					}
					Object[] stepsArray = (Object[]) getArray(map, TestLinkResponseParams.steps.toString());
					if ( stepsArray != null && stepsArray.length > 0 ) 
					{
						for( Object stepObject : stepsArray )
						{
							Map<String, Object> stepMap = (Map<String, Object>)stepObject;
							TestCaseStep step = Util.getTestCaseStep(stepMap);
							testCase.getSteps().add( step );
						}
					}
				}
				
			}			
		}
		return testCase;
	}

	/**
	 * @param map
	 * @param key
	 * @return Array of objects.
	 */
	public static Object[] getArray( Map<String, Object> map, String key )
	{
		Object[] array = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get(key);
			array = castToArray(o);
		}
		return array;
	}
	
	/**
	 * 
	 * @param object
	 * @return Array of objects
	 */
	public static Object[] castToArray(Object object)
	{
		Object[] array = null;
		
		if ( object != null )
		{
			if (object instanceof String)
				array = EMPTY_ARRAY;
			else
				array = (Object[])object;
		}
		
		return array;
	}

	/**
	 * @param build
	 * @return Map of Build.
	 */
	public static final Map<String, Object> getBuildMap(Build build) 
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.testPlanId.toString(), build.getTestPlanId());
		executionData.put(TestLinkParams.buildName.toString(), build.getName());
		executionData.put(TestLinkParams.buildNotes.toString(), build.getNotes());
		return executionData;
	}

	/**
	 * @param attachment
	 * @return Map of Attachment.
	 */
	public static final Map<String, Object> getAttachmentMap( Attachment attachment )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.fkId.toString(), attachment.getFkId());
		executionData.put(TestLinkParams.fkTable.toString(), attachment.getFkTable());
		executionData.put(TestLinkParams.title.toString(), attachment.getTitle());
		executionData.put(TestLinkParams.description.toString(), attachment.getDescription());
		executionData.put(TestLinkParams.fileName.toString(), attachment.getFileName());
		executionData.put(TestLinkParams.fileType.toString(), attachment.getFileType());
		executionData.put(TestLinkParams.content.toString(), attachment.getContent());
		return executionData;
	}

	/**
	 * @param attachment
	 * @return Map of Test Case Attachment.
	 */
	public static final Map<String, Object> getTestCaseAttachmentMap(
			Attachment attachment )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.testCaseId.toString(), attachment.getFkId());
		executionData.put(TestLinkParams.fkTable.toString(), attachment.getFkTable());
		executionData.put(TestLinkParams.title.toString(), attachment.getTitle());
		executionData.put(TestLinkParams.description.toString(), attachment.getDescription());
		executionData.put(TestLinkParams.fileName.toString(), attachment.getFileName());
		executionData.put(TestLinkParams.fileType.toString(), attachment.getFileType());
		executionData.put(TestLinkParams.content.toString(), attachment.getContent());
		return executionData;
	}

	/**
	 * @param attachment
	 * @return Map of Test Suite Attachment.
	 */
	public static final Map<String, Object> getTestSuiteAttachmentMap(
			Attachment attachment )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.testSuiteId.toString(), attachment.getFkId());
		executionData.put(TestLinkParams.fkTable.toString(), attachment.getFkTable());
		executionData.put(TestLinkParams.title.toString(), attachment.getTitle());
		executionData.put(TestLinkParams.description.toString(), attachment.getDescription());
		executionData.put(TestLinkParams.fileName.toString(), attachment.getFileName());
		executionData.put(TestLinkParams.fileType.toString(), attachment.getFileType());
		executionData.put(TestLinkParams.content.toString(), attachment.getContent());
		return executionData;
	}

	/**
	 * @param attachment
	 * @return Map of Test Project Attachment.
	 */
	public static final Map<String, Object> getTestProjectAttachmentMap(
			Attachment attachment )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.testProjectId.toString(), attachment.getFkId());
		executionData.put(TestLinkParams.fkTable.toString(), attachment.getFkTable());
		executionData.put(TestLinkParams.title.toString(), attachment.getTitle());
		executionData.put(TestLinkParams.description.toString(), attachment.getDescription());
		executionData.put(TestLinkParams.fileName.toString(), attachment.getFileName());
		executionData.put(TestLinkParams.fileType.toString(), attachment.getFileType());
		executionData.put(TestLinkParams.content.toString(), attachment.getContent());
		return executionData;
	}

	/**
	 * @param attachment
	 * @return Map of Requirement Attachment.
	 */
	public static final Map<String, Object> getRequirementAttachmentMap(
			Attachment attachment )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.requirementId.toString(), attachment.getFkId());
		executionData.put(TestLinkParams.fkTable.toString(), attachment.getFkTable());
		executionData.put(TestLinkParams.title.toString(), attachment.getTitle());
		executionData.put(TestLinkParams.description.toString(), attachment.getDescription());
		executionData.put(TestLinkParams.fileName.toString(), attachment.getFileName());
		executionData.put(TestLinkParams.fileType.toString(), attachment.getFileType());
		executionData.put(TestLinkParams.content.toString(), attachment.getContent());
		return executionData;
	}

	/**
	 * @param attachment
	 * @return Map of Requirement Specification Attachment.
	 */
	public static final Map<String, Object> getRequirementSpecificationAttachmentMap(
			Attachment attachment )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.reqSpecId.toString(), attachment.getFkId());
		executionData.put(TestLinkParams.fkTable.toString(), attachment.getFkTable());
		executionData.put(TestLinkParams.title.toString(), attachment.getTitle());
		executionData.put(TestLinkParams.description.toString(), attachment.getDescription());
		executionData.put(TestLinkParams.fileName.toString(), attachment.getFileName());
		executionData.put(TestLinkParams.fileType.toString(), attachment.getFileType());
		executionData.put(TestLinkParams.content.toString(), attachment.getContent());
		return executionData;
	}

	/**
	 * @param attachment
	 * @return Map of Execution Attachment
	 */
	public static final Map<String, Object> getExecutionAttachmentMap(
			Attachment attachment )
	{
		Map<String, Object> executionData = new HashMap<String, Object>();
		executionData.put(TestLinkParams.executionId.toString(), attachment.getFkId());
		executionData.put(TestLinkParams.fkTable.toString(), attachment.getFkTable());
		executionData.put(TestLinkParams.title.toString(), attachment.getTitle());
		executionData.put(TestLinkParams.description.toString(), attachment.getDescription());
		executionData.put(TestLinkParams.fileName.toString(), attachment.getFileName());
		executionData.put(TestLinkParams.fileType.toString(), attachment.getFileType());
		executionData.put(TestLinkParams.content.toString(), attachment.getContent());
		return executionData;
	}
	
	
	/**
	 * @param map
	 * @return Attachment.
	 */
	public static final Attachment getAttachment( Map<String, Object> map )
	{
		Attachment attachment = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					attachment = new Attachment();
					attachment.setId( id );
					
					attachment.setFileName( getString(map, TestLinkResponseParams.name.toString()) );
					attachment.setFileType( getString(map, TestLinkResponseParams.fileType.toString()) );
					attachment.setTitle( getString(map, TestLinkResponseParams.title.toString()) );
					// TBD: put the date too...
					attachment.setContent( getString(map, TestLinkResponseParams.content.toString()) );
					// TBD: description not beign returned
					// attachment.setDescription( getString(map, TestLinkResponseParams.description.toString() ) );
					// TBD: returning name instead of file_name
					// TBD: file size not beign returned
					
				}
				
			}			
		}
		return attachment;
	}

	/**
	 * @param requirements
	 * @return List with Maps of Requirements Grouped By ReSpec.
	 */
	public static final List<Map<String, Object>> getRequirementsGroupedByReqSpecMap( 
		List<Requirement> requirements )
	{
		List<Map<String, Object>> requirementsGroupedByReqSpecMap = new ArrayList<Map<String, Object>>();
		
		Map<Integer, List<Integer>> tempMap = new HashMap<Integer, List<Integer>>();
		for (Iterator<Requirement> iterator = requirements.iterator(); iterator.hasNext();) 
		{
			Requirement requirement = iterator.next();
			List<Integer> requirementsArray = tempMap.get(requirement.getReqSpecId());
			if ( requirementsArray == null )
			{
				requirementsArray = new ArrayList<Integer>();
			}
			requirementsArray.add( requirement.getId() );
			tempMap.put(requirement.getReqSpecId(), requirementsArray);
		}
		
		Set<Entry<Integer, List<Integer>>> entrySet = tempMap.entrySet();
		
		for (Entry<Integer, List<Integer>> entry : entrySet) 
		{
			Map<String, Object> finalMap = new HashMap<String, Object>();
			finalMap.put("req_spec", entry.getKey());
			finalMap.put("requirements", entry.getValue());
			requirementsGroupedByReqSpecMap.add( finalMap );
		}
		
		return requirementsGroupedByReqSpecMap;
	}

	/**
	 * @param map
	 * @return Execution.
	 */
	public static final Execution getExecution(Map<String, Object> map) 
	{
		Execution execution = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					execution = new Execution();
					execution.setId( id );
					
					execution.setBuildId( getInteger(map, TestLinkResponseParams.buildId.toString()) );
					execution.setTesterId( getInteger(map, TestLinkResponseParams.testerId.toString()) );
					String statusText = getString(map, TestLinkResponseParams.status.toString());
					ExecutionStatus status = ExecutionStatus.getExecutionStatus(statusText.charAt(0));
					execution.setStatus( status );
					execution.setTestPlanId( getInteger(map, TestLinkResponseParams.testPlanId.toString()) );
					execution.setTestCaseVersionId( getInteger(map, TestLinkResponseParams.testCaseVersionId.toString()) );
					execution.setTestCaseVersionNumber( getInteger(map, TestLinkResponseParams.testcaseVersionNumber.toString()) );
					Integer executionTypeText = getInteger( map, TestLinkResponseParams.executionType.toString() );
					ExecutionType executionType = ExecutionType.getExecutionType(executionTypeText);
					execution.setExecutionType(executionType);
					execution.setNotes( getString(map, TestLinkResponseParams.notes.toString()) );					
				}
				
			}			
		}
		return execution;
	}

	/**
	 * @param map
	 * @return Build.
	 */
	public static final Build getBuild( Map<String, Object> map )
	{
		Build build = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					build = new Build();
					build.setId( id );
					
					build.setName( getString(map, TestLinkResponseParams.name.toString()) );
					build.setNotes( getString(map, TestLinkResponseParams.notes.toString()) );
					build.setTestPlanId( getInteger(map, TestLinkResponseParams.testPlanId.toString()) );
					build.setName( getString(map, TestLinkResponseParams.name.toString()) );
					// TBD: add is open, release date, closed on date and active to Build entity
				}
				
			}			
		}
		return build;
	}
	
	/**
	 * @param map
	 * @return ReportTCResultResponse object.
	 */
	public static final ReportTCResultResponse getReportTCResultResponse(
			Map<String, Object> map )
	{
		ReportTCResultResponse reportTCResultResponse = null;
		if ( map != null && map.size() > 0 )
		{
			Object o = map.get( TestLinkResponseParams.id.toString());
			if ( o != null )
			{
				Integer id = Integer.parseInt( o.toString() );
				
				if ( id > 0 )
				{
					reportTCResultResponse = new ReportTCResultResponse();
					reportTCResultResponse.setExecutionId( id );
					
					reportTCResultResponse.setOperation( getString(map, TestLinkResponseParams.operation.toString()) );
					reportTCResultResponse.setOverwrite( getBoolean(map, TestLinkResponseParams.overwrite.toString()) );
					reportTCResultResponse.setStatus( getBoolean(map, TestLinkResponseParams.status.toString()) );
					reportTCResultResponse.setMessage( getString(map, TestLinkResponseParams.message.toString()) );
					reportTCResultResponse.setBugIDStatus( getBoolean(map, TestLinkResponseParams.bugIDStatus.toString()) );
					reportTCResultResponse.setCustomFieldStatus( getBoolean(map, TestLinkResponseParams.customFieldStatus.toString()) );
				}
				
			}			
		}
		return reportTCResultResponse;
	}

	/**
	 * @param map
	 * @return Custom Field.
	 */
	public static final CustomField getCustomField( Map<String, Object> map )
	{
		CustomField customField = null;
		if ( map != null && map.size() > 0 )
		{
			// Sometimes we are working with CFs without ID
			customField = new CustomField();
			customField.setId( getInteger( map, TestLinkResponseParams.id.toString()) );
			customField.setDefaultValue( getString(map, TestLinkResponseParams.defaultValue.toString()) );
			customField.setDisplayOrder( getInteger(map, TestLinkResponseParams.displayOrder.toString()) );
			customField.setEnableOnDesign( getBoolean(map, TestLinkResponseParams.enableOnDesign.toString()) );
			customField.setEnableOnExecution( getBoolean(map, TestLinkResponseParams.enableOnExecution.toString()) );
			customField.setEnableOnTestPlanDesign( getBoolean(map, TestLinkResponseParams.enableOnTestPlanDesign.toString()) );
			customField.setLabel( getString(map, TestLinkResponseParams.label.toString()) );
			customField.setLengthMax( getInteger(map, TestLinkResponseParams.lengthMax.toString()) );
			customField.setLengthMin( getInteger(map, TestLinkResponseParams.lengthMin.toString()) );
			customField.setLocation( getInteger(map, TestLinkResponseParams.location.toString()) );
			customField.setName( getString(map, TestLinkResponseParams.name.toString()) );
			customField.setPossibleValues( getString(map, TestLinkResponseParams.possibleValues.toString()) );
			customField.setShowOnDesign( getBoolean(map, TestLinkResponseParams.showOnDesign.toString()) );
			customField.setShowOnExecution( getBoolean(map, TestLinkResponseParams.showOnExecution.toString()) );
			customField.setShowOnTestPlanDesign( getBoolean(map, TestLinkResponseParams.showOnTestPlanDesign.toString()) );
			customField.setType( getInteger(map, TestLinkResponseParams.type.toString()) );
			customField.setValidRegexp( getString(map, TestLinkResponseParams.validRegexp.toString()) );
			customField.setValue( getString(map, TestLinkResponseParams.value.toString() ));
	
		}
		return customField;
	}
	
	/**
	 * Puts a boolean value into a map if the value is not null.
	 * 
	 * @param map Map.
	 * @param key Key.
	 * @param boolValue Boolean value.
	 */
	public static final void putIfNotNullAndTrue(
			Map<String, Object> map,
			String key, 
			Boolean boolValue) 
	{
		if ( boolValue != null && boolValue == true )
		{
			map.put( key, 0 );
		}
	}
	
	public static final String getStringValueOrNull( Object o )
	{
		String value = null;
		if ( o != null )
		{
			value = o.toString();
		}
		return value;
	}
	
}
