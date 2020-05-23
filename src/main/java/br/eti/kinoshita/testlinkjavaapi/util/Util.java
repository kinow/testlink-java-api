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
package br.eti.kinoshita.testlinkjavaapi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkParams;
import br.eti.kinoshita.testlinkjavaapi.constants.TestLinkResponseParams;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.model.Execution;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.Requirement;
import br.eti.kinoshita.testlinkjavaapi.model.Role;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStepResult;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.model.User;

/**
 * Utility class with methods to handle the response or prepare the request to
 * the PHP XML-RPC API. This class is able to convert from a Map to an Object
 * and vice-versa.
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public final class Util {

    private static final Logger LOG = Logger.getLogger(Util.class.getName());

    private Util() {
    }

    /**
     * @param project test project
     * @return Map of Test Project
     */
    public static Map<String, Object> getTestProjectMap(TestProject project) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.TEST_PROJECT_NAME.toString(), project.getName());
        executionData.put(TestLinkParams.TEST_CASE_PREFIX.toString(), project.getPrefix());
        executionData.put(TestLinkParams.NOTES.toString(), project.getNotes());

        Map<String, Object> options = new HashMap<>();
        options.put(TestLinkParams.ENABLE_REQUIREMENTS.toString(), project.isEnableRequirements());
        options.put(TestLinkParams.ENABLE_TEST_PRIORITY.toString(), project.isEnableTestPriority());
        options.put(TestLinkParams.ENABLE_AUTOMATION.toString(), project.isEnableAutomation());
        options.put(TestLinkParams.ENABLE_INVENTORY.toString(), project.isEnableInventory());

        executionData.put(TestLinkParams.OPTIONS.toString(), options);

        executionData.put(TestLinkParams.ACTIVE.toString(), project.isActive());
        executionData.put(TestLinkParams.PUBLIC.toString(), project.isPublic());

        return executionData;
    }

    /**
     * Extracts a Test Project from a Map.
     *
     * @param map Map with properties of a Test Project.
     * @return Test Project.
     */
    @SuppressWarnings("unchecked")
    public static TestProject getTestProject(Map<String, Object> map) {
        TestProject testProject = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    testProject = new TestProject();
                    testProject.setId(id);

                    testProject.setName(getString(map, TestLinkResponseParams.NAME.toString()));
                    testProject.setPrefix(getString(map, TestLinkResponseParams.PREFIX.toString()));
                    testProject.setNotes(getString(map, TestLinkResponseParams.NOTES.toString()));

                    Map<String, Object> optMap = (Map<String, Object>) map.get(TestLinkResponseParams.OPT.toString());
                    testProject.setEnableAutomation(
                            getBoolean(optMap, TestLinkResponseParams.AUTOMATION_ENABLED.toString()));
                    testProject.setEnableRequirements(
                            getBoolean(optMap, TestLinkResponseParams.REQUIREMENTS_ENABLED.toString()));
                    testProject.setEnableTestPriority(
                            getBoolean(optMap, TestLinkResponseParams.TEST_PRIORITY_ENABLED.toString()));
                    testProject.setEnableInventory(
                            getBoolean(optMap, TestLinkResponseParams.INVENTORY_ENABLED.toString()));

                    testProject.setActive(getBoolean(map, TestLinkResponseParams.ACTIVE.toString()));
                    testProject.setPublic(getBoolean(map, TestLinkResponseParams.IS_PUBLIC.toString()));
                }

            }
        }
        return testProject;
    }

    /**
     * @param map a map
     * @param key the desired key
     * @return Boolean value.
     */
    public static Boolean getBoolean(Map<String, Object> map, String key) {
        Boolean booleanObj = null;
        Integer integer = getInteger(map, key);
        if (integer != null) {
            if (integer == 0) {
                booleanObj = Boolean.FALSE;
            } else {
                booleanObj = Boolean.TRUE;
            }
        }
        return booleanObj;
    }

    /**
     * @param map a map
     * @param key the desired key
     * @return String value.
     */
    public static String getString(Map<String, Object> map, String key) {
        String string = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(key);
            if (o != null) {
                string = o.toString();
            }
        }
        return string;
    }

    /**
     * @param map a map
     * @param key the desired key
     * @return Integer value.
     */
    public static Integer getInteger(Map<String, Object> map, String key) {
        Integer integer = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(key);
            if (o != null) {
                try {
                    integer = Integer.parseInt(o.toString());
                } catch (NumberFormatException nfe) {
                    integer = null;
                }
            }
        }
        return integer;
    }

    /**
     * @param plan test plan
     * @return Map of Test Plan.
     */
    public static Map<String, Object> getTestPlanMap(TestPlan plan) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.TEST_PLAN_NAME.toString(), plan.getName());
        executionData.put(TestLinkParams.TEST_PROJECT_NAME.toString(), plan.getProjectName());
        executionData.put(TestLinkParams.NOTES.toString(), plan.getNotes());
        executionData.put(TestLinkParams.ACTIVE.toString(), plan.isActive());
        executionData.put(TestLinkParams.PUBLIC.toString(), plan.isPublic());
        return executionData;
    }

    /**
     * @param map a map
     * @return Test Plan.
     */
    public static TestPlan getTestPlan(Map<String, Object> map) {
        TestPlan testPlan = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    testPlan = new TestPlan();
                    testPlan.setId(id);

                    testPlan.setName(getString(map, TestLinkResponseParams.NAME.toString()));
                    testPlan.setProjectName(getString(map, TestLinkResponseParams.PROJECT_NAME.toString()));
                    testPlan.setNotes(getString(map, TestLinkResponseParams.NOTES.toString()));

                    testPlan.setActive(getBoolean(map, TestLinkResponseParams.ACTIVE.toString()));
                    testPlan.setPublic(getBoolean(map, TestLinkResponseParams.IS_PUBLIC.toString()));
                }

            }
        }
        return testPlan;
    }

    /**
     * @param map a map
     * @return Platform.
     */
    public static Platform getPlatform(Map<String, Object> map) {
        Platform platform = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    platform = new Platform();
                    platform.setId(id);

                    platform.setName(getString(map, TestLinkResponseParams.NAME.toString()));
                    platform.setNotes(getString(map, TestLinkResponseParams.NOTES.toString()));
                }

            }
        }
        return platform;
    }

    /**
     * @param testCase test case
     * @return Map of Test Case.
     */
    public static Map<String, Object> getTestCaseMap(TestCase testCase) {
        Map<String, Object> executionData = new HashMap<>();
        putIfNotNull(executionData, TestLinkParams.TEST_CASE_NAME.toString(), testCase.getName());
        putIfNotNull(executionData, TestLinkParams.TEST_CASE_ID.toString(), testCase.getId());
        putIfNotNull(executionData, TestLinkParams.TEST_SUITE_ID.toString(), testCase.getTestSuiteId());
        putIfNotNull(executionData, TestLinkParams.TEST_PROJECT_ID.toString(), testCase.getTestProjectId());
        putIfNotNull(executionData, TestLinkParams.AUTHOR_LOGIN.toString(), testCase.getAuthorLogin());
        putIfNotNull(executionData, TestLinkParams.SUMMARY.toString(), testCase.getSummary());

        if (testCase.getSteps() != null) {
            List<Map<String, Object>> steps = getTestCaseStepsMap(testCase.getSteps());
            executionData.put(TestLinkParams.STEPS.toString(), steps);
        }
        putIfNotNull(executionData, TestLinkParams.PRECONDITIONS.toString(), testCase.getPreconditions());
        putIfNotNull(executionData, TestLinkParams.STATUS.toString(), Util.getStringValueOrNull(testCase.getTestCaseStatus()));
        putIfNotNull(executionData, TestLinkParams.IMPORTANCE.toString(),
                Util.getStringValueOrNull(testCase.getTestImportance()));
        putIfNotNull(executionData, TestLinkParams.EXECUTION_TYPE.toString(),
                Util.getStringValueOrNull(testCase.getExecutionType()));
        putIfNotNull(executionData, TestLinkParams.ORDER.toString(), testCase.getOrder());
        putIfNotNull(executionData, TestLinkParams.INTERNAL_ID.toString(), testCase.getInternalId());
        putIfNotNull(executionData, TestLinkParams.CHECK_DUPLICATED_NAME.toString(), testCase.getCheckDuplicatedName());
        putIfNotNull(executionData, TestLinkParams.ACTION_ON_DUPLICATED_NAME.toString(),
                testCase.getActionOnDuplicatedName() != null ? testCase.getActionOnDuplicatedName().toString() : null);

        return executionData;
    }

    /**
     *
     * @param testCaseSteps test case steps
     * @return A list whit one Map for each TestCaseStep
     * @since 1.9.4-1
     */
    public static List<Map<String, Object>> getTestCaseStepsMap(List<TestCaseStep> testCaseSteps) {
        List<Map<String, Object>> steps = new ArrayList<>();

        if (testCaseSteps != null && testCaseSteps.size() > 0) {
            /*
             * for(TestCaseStep step : testCaseSteps)
             * steps.add(getTestCaseStepMap(step));
             */

            // Why uses an iterator over a foreach?
            for (TestCaseStep testCaseStep : testCaseSteps) {
                Map<String, Object> testCaseStepMap = getTestCaseStepMap(testCaseStep, true);
                steps.add(testCaseStepMap);
            }
        }

        return steps;
    }

    /**
     *
     * @param testCaseSteps test case steps
     * @return A list with the step's id
     * @since 1.9.4-1
     */
    public static List<Integer> getTestCaseStepsIdList(List<TestCaseStep> testCaseSteps) {
        List<Integer> steps = new ArrayList<>();

        if (testCaseSteps != null && testCaseSteps.size() > 0) {
            /*
             * for (TestCaseStep step : testCaseSteps) steps.add(step.getId());
             */

            // Why uses an iterator over a foreach?
            for (TestCaseStep testCaseStep : testCaseSteps) {
                steps.add(testCaseStep.getNumber());
            }
        }

        return steps;
    }

    /**
     * @param map Case Step map
     * @return Test Case
     */
    public static TestCaseStep getTestCaseStep(Map<String, Object> map) {
        TestCaseStep step = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    step = new TestCaseStep();
                    step.setId(id);
                    step.setActions(getString(map, TestLinkResponseParams.ACTIONS.toString()));
                    step.setActive(getBoolean(map, TestLinkResponseParams.ACTIVE.toString()));
                    Integer executionTypeValue = getInteger(map, TestLinkResponseParams.EXECUTION_TYPE.toString());
                    ExecutionType execution = ExecutionType.getExecutionType(executionTypeValue);
                    step.setExecutionType(execution);
                    step.setExpectedResults(getString(map, TestLinkResponseParams.EXPECTED_RESULTS.toString()));
                    step.setNumber(getInteger(map, TestLinkResponseParams.STEP_NUMBER.toString()));
                }

            }
        }
        return step;
    }

    /**
     * @param testCaseStep test case step
     * @return Map of Test Case Step.
     */
    public static Map<String, Object> getTestCaseStepMap(TestCaseStep testCaseStep) {
        return getTestCaseStepMap(testCaseStep, false);
    }

    /**
     * @param testCaseStep test case step
     * @param internal the API uses different names for the the same parameter
     *            in different methods.
     * @return Map of Test Case Step.
     */
    public static Map<String, Object> getTestCaseStepMap(TestCaseStep testCaseStep, boolean internal) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.STEP_NUMBER.toString(), testCaseStep.getNumber());
        executionData.put(TestLinkParams.ACTIONS.toString(), testCaseStep.getActions());
        executionData.put(TestLinkParams.EXPECTED_RESULTS.toString(), testCaseStep.getExpectedResults());
        if (internal) {
            executionData.put(TestLinkParams.STEP_EXECUTION_TYPE.toString(),
                    testCaseStep.getExecutionType().getValue());
        } else {
            executionData.put(TestLinkParams.EXECUTION_TYPE.toString(), testCaseStep.getExecutionType());
        }

        return executionData;
    }

   /**
    * @param testCaseStepResults test case steps
    * @return A list with one Map for each TestCaseStepResult
    * @since 1.9.19-0
    */
   public static List<Map<String, Object>> getTestCaseStepResultMap(List<TestCaseStepResult> testCaseStepResults) {
       List<Map<String, Object>> steps = new ArrayList<>();

       if (testCaseStepResults != null && testCaseStepResults.size() > 0) {
           for (TestCaseStepResult step : testCaseStepResults) {
               Map<String, Object> testCaseStepMap = new HashMap<>();
               testCaseStepMap.put(TestLinkParams.NOTES.toString(), step.getNotes());
               testCaseStepMap.put(TestLinkParams.STEP_NUMBER.toString(), step.getNumber());
               testCaseStepMap.put(TestLinkParams.RESULT.toString(), step.getResult().toString());
               steps.add(testCaseStepMap);
           }
       }
       return steps;
   }

   /**
     * @param testSuite test suite
     * @return Map of Test Suite Map.
     */
    public static Map<String, Object> getTestSuiteMap(TestSuite testSuite) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.TEST_SUITE_NAME.toString(), testSuite.getName());
        executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), testSuite.getTestProjectId());
        executionData.put(TestLinkParams.PARENT_ID.toString(), testSuite.getParentId());
        executionData.put(TestLinkParams.DETAILS.toString(), testSuite.getDetails());
        executionData.put(TestLinkParams.ORDER.toString(), testSuite.getOrder());
        executionData.put(TestLinkParams.CHECK_DUPLICATED_NAME.toString(), testSuite.getCheckDuplicatedName());
        executionData.put(TestLinkParams.ACTION_ON_DUPLICATED_NAME.toString(),
                testSuite.getActionOnDuplicatedName() != null ? testSuite.getActionOnDuplicatedName().toString()
                        : null);
        return executionData;
    }

    /**
     * @param map a map
     * @return Test Suite.
     */
    public static TestSuite getTestSuite(Map<String, Object> map) {
        TestSuite testSuite = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    testSuite = new TestSuite();
                    testSuite.setId(id);

                    testSuite.setDetails(getString(map, TestLinkResponseParams.DETAILS.toString()));
                    testSuite.setName(getString(map, TestLinkResponseParams.NAME.toString()));
                    testSuite.setParentId(getInteger(map, TestLinkResponseParams.PARENT_ID.toString()));
                    testSuite.setOrder(getInteger(map, TestLinkResponseParams.ORDER.toString()));
                }

            }
        }
        return testSuite;
    }

    /**
     * @param map a map
     * @return Test Case.
     */
    @SuppressWarnings("unchecked")
    public static TestCase getTestCase(Map<String, Object> map) {
        TestCase testCase = null;
        if (map != null && map.size() > 0) {
            // IMPORTANT: http://mantis.testlink.org/view.php?id=4784
            // Different methods to recover test cases use different parameter
            // names for the id, some uses "id" and others "testcase_id".
            Object o = map.get(TestLinkResponseParams.TEST_CASE_ID.toString());
            if (o == null) {
                o = map.get(TestLinkResponseParams.ID.toString());
            }

            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    testCase = new TestCase();
                    testCase.setId(id);
                    testCase.setVersionId(getInteger(map, TestLinkResponseParams.TEST_CASE_VERSION_ID.toString()));
                    testCase.setVersion(getInteger(map, TestLinkResponseParams.VERSION.toString()));
                    testCase.setPreconditions(getString(map, TestLinkResponseParams.PRECONDITIONS.toString()));
                    testCase.setSummary(getString(map, TestLinkResponseParams.SUMMARY.toString()));
                    testCase.setParentId(getInteger(map, TestLinkResponseParams.PARENT_ID.toString()));
                    testCase.setOrder(getInteger(map, TestLinkResponseParams.ORDER.toString()));
                    testCase.setExecutionOrder(getInteger(map, TestLinkResponseParams.EXECUTION_ORDER.toString()));
                    // the name of the test case is not always in the same
                    // parameter
                    String testCaseName = getString(map, TestLinkResponseParams.TCASE_NAME.toString());
                    if (testCaseName == null) {
                        testCaseName = getString(map, TestLinkResponseParams.NAME.toString());
                    }
                    testCase.setName(testCaseName);

                    Platform platform = null;
                    String platformName = getString(map, TestLinkResponseParams.PLATFORM_NAME.toString());
                    Integer platformId = getInteger(map, TestLinkResponseParams.PLATFORM_ID.toString());
                    if (platformName != null || platformId != null) { // sometimes
                                                                      // TL may
                                                                      // return
                                                                      // only
                                                                      // one or
                                                                      // the
                                                                      // other
                        platform = new Platform();
                        platform.setId(platformId);
                        platform.setName(platformName);
                    }
                    testCase.setPlatform(platform);

                    testCase.setFeatureId(getInteger(map, TestLinkResponseParams.FEATURE_ID.toString()));

                    testCase.setExternalId(getInteger(map, TestLinkResponseParams.EXTERNAL_ID.toString()));
                    if (testCase.getExternalId() == null) {
                        testCase.setExternalId(getInteger(map, TestLinkResponseParams.EXTERNAL_ID2.toString()));
                    }

                    // IMPORTANT: the full external id (composed by
                    // prefix-external_id) come on
                    // different parameters depending of what methods was used.
                    //
                    // In 'getTestCase' -> 'full_tc_external_id'
                    // In 'getTestCasesForTestSuite' -> 'external_id'
                    // In 'getTestCasesForTestPlan' does not come (ToDo: add)
                    String fullExternalId = getString(map,
                            TestLinkResponseParams.FULL_TEST_CASE_EXTERNAL_ID.toString());
                    if (fullExternalId == null) {
                        fullExternalId = getString(map, TestLinkResponseParams.FULL_TEST_CASE_EXTERNAL_ID2.toString());
                        if (fullExternalId == null) {
                            fullExternalId = getString(map, TestLinkResponseParams.EXTERNAL_ID.toString());
                        }
                    }
                    testCase.setFullExternalId(fullExternalId);

                    TestCaseStatus status;
                    Integer testCaseStatusId = getInteger(map, TestLinkResponseParams.STATUS.toString());
                    if (testCaseStatusId != null) {
                        status = TestCaseStatus.values()[TestCaseStatus.values().length - testCaseStatusId];
                        testCase.setTestCaseStatus(status);
                    }

                    Integer executionTypeValue = getInteger(map, TestLinkResponseParams.EXECUTION_TYPE.toString());
                    ExecutionType execution = ExecutionType.getExecutionType(executionTypeValue);
                    testCase.setExecutionType(execution);
                    ExecutionStatus executionStatus = ExecutionStatus.NOT_RUN;
                    String executionStatusText = getString(map, TestLinkResponseParams.EXEC_STATUS.toString());
                    if (StringUtils.isNotBlank(executionStatusText)) {
                        executionStatus = ExecutionStatus.getExecutionStatus(executionStatusText.charAt(0));
                    }
                    testCase.setExecutionStatus(executionStatus);
                    testCase.setTestProjectId(getInteger(map, TestLinkParams.TEST_PROJECT_ID.toString()));
                    testCase.setTestSuiteId(getInteger(map, TestLinkParams.TEST_SUITE_ID2.toString()));
                    // inconsistent
                    // parameter
                    // name
                    // TODO: check if TL 2.0 allows it
                    // CustomField[] customFields = (CustomField[])getArray(map,
                    // TestLinkResponseParams.customFields.toString());
                    // if ( customFields != null )
                    // {
                    // for (int i = 0; i < customFields.length; i++)
                    // {
                    // CustomField customField = customFields[i];
                    // testCase.getCustomFields().add( customField );
                    // }
                    // }
                    Object[] stepsArray = getArray(map, TestLinkResponseParams.STEPS.toString());
                    if (stepsArray != null && stepsArray.length > 0) {
                        for (Object stepObject : stepsArray) {
                            Map<String, Object> stepMap = (Map<String, Object>) stepObject;
                            TestCaseStep step = Util.getTestCaseStep(stepMap);
                            testCase.getSteps().add(step);
                        }
                    }
                }

            }
        }
        return testCase;
    }

    /**
     * @param map a map
     * @param key the desired key
     * @return Array of objects.
     */
    public static Object[] getArray(Map<String, Object> map, String key) {
        Object[] array = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(key);
            array = castToArray(o);
        }
        return array;
    }

    /**
     *
     * @param object an object
     * @return Array of objects
     */
    public static Object[] castToArray(Object object) {
        Object[] array = null;

        if (object != null) {
            if (object instanceof String) {
                array = new Object[0];
            } else {
                array = (Object[]) object;
            }
        }

        return array;
    }

    /**
     *
     * @param object an object
     * @return Map of objects
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> castToMap(Object object) {
        Map<String, Object> map = null;

        if (object != null) {
            if (object instanceof String) {
                map = Collections.emptyMap();
            } else {
                map = (Map<String, Object>) object;
            }
        }

        return map;
    }

    /**
     * @param build a build
     * @return Map of Build.
     */
    public static Map<String, Object> getBuildMap(Build build) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.TEST_PLAN_ID.toString(), build.getTestPlanId());
        executionData.put(TestLinkParams.BUILD_NAME.toString(), build.getName());
        executionData.put(TestLinkParams.BUILD_NOTES.toString(), build.getNotes());
        return executionData;
    }

    /**
     * @param attachment an attachment
     * @return Map of Attachment.
     */
    public static Map<String, Object> getAttachmentMap(Attachment attachment) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.FK_ID.toString(), attachment.getFkId());
        executionData.put(TestLinkParams.FK_TABLE.toString(), attachment.getFkTable());
        executionData.put(TestLinkParams.TITLE.toString(), attachment.getTitle());
        executionData.put(TestLinkParams.DESCRIPTION.toString(), attachment.getDescription());
        executionData.put(TestLinkParams.FILE_NAME.toString(), attachment.getFileName());
        executionData.put(TestLinkParams.FILE_TYPE.toString(), attachment.getFileType());
        executionData.put(TestLinkParams.CONTENT.toString(), attachment.getContent());
        return executionData;
    }

    /**
     * @param attachment an attachment
     * @return Map of Test Case Attachment.
     */
    public static Map<String, Object> getTestCaseAttachmentMap(Attachment attachment) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.TEST_CASE_ID.toString(), attachment.getFkId());
        executionData.put(TestLinkParams.FK_TABLE.toString(), attachment.getFkTable());
        executionData.put(TestLinkParams.TITLE.toString(), attachment.getTitle());
        executionData.put(TestLinkParams.DESCRIPTION.toString(), attachment.getDescription());
        executionData.put(TestLinkParams.FILE_NAME.toString(), attachment.getFileName());
        executionData.put(TestLinkParams.FILE_TYPE.toString(), attachment.getFileType());
        executionData.put(TestLinkParams.CONTENT.toString(), attachment.getContent());
        return executionData;
    }

    /**
     * @param attachment an attachment
     * @return Map of Test Suite Attachment.
     */
    public static Map<String, Object> getTestSuiteAttachmentMap(Attachment attachment) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.TEST_SUITE_ID.toString(), attachment.getFkId());
        executionData.put(TestLinkParams.FK_TABLE.toString(), attachment.getFkTable());
        executionData.put(TestLinkParams.TITLE.toString(), attachment.getTitle());
        executionData.put(TestLinkParams.DESCRIPTION.toString(), attachment.getDescription());
        executionData.put(TestLinkParams.FILE_NAME.toString(), attachment.getFileName());
        executionData.put(TestLinkParams.FILE_TYPE.toString(), attachment.getFileType());
        executionData.put(TestLinkParams.CONTENT.toString(), attachment.getContent());
        return executionData;
    }

    /**
     * @param attachment an attachment
     * @return Map of Test Project Attachment.
     */
    public static Map<String, Object> getTestProjectAttachmentMap(Attachment attachment) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.TEST_PROJECT_ID.toString(), attachment.getFkId());
        executionData.put(TestLinkParams.FK_TABLE.toString(), attachment.getFkTable());
        executionData.put(TestLinkParams.TITLE.toString(), attachment.getTitle());
        executionData.put(TestLinkParams.DESCRIPTION.toString(), attachment.getDescription());
        executionData.put(TestLinkParams.FILE_NAME.toString(), attachment.getFileName());
        executionData.put(TestLinkParams.FILE_TYPE.toString(), attachment.getFileType());
        executionData.put(TestLinkParams.CONTENT.toString(), attachment.getContent());
        return executionData;
    }

    /**
     * @param attachment an attachment
     * @return Map of Requirement Attachment.
     */
    public static Map<String, Object> getRequirementAttachmentMap(Attachment attachment) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.REQUIREMENT_ID.toString(), attachment.getFkId());
        executionData.put(TestLinkParams.FK_TABLE.toString(), attachment.getFkTable());
        executionData.put(TestLinkParams.TITLE.toString(), attachment.getTitle());
        executionData.put(TestLinkParams.DESCRIPTION.toString(), attachment.getDescription());
        executionData.put(TestLinkParams.FILE_NAME.toString(), attachment.getFileName());
        executionData.put(TestLinkParams.FILE_TYPE.toString(), attachment.getFileType());
        executionData.put(TestLinkParams.CONTENT.toString(), attachment.getContent());
        return executionData;
    }

    /**
     * @param attachment an attachment
     * @return Map of Requirement Specification Attachment.
     */
    public static Map<String, Object> getRequirementSpecificationAttachmentMap(Attachment attachment) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.REQUIREMENT_SPECIFICATION_ID.toString(), attachment.getFkId());
        executionData.put(TestLinkParams.FK_TABLE.toString(), attachment.getFkTable());
        executionData.put(TestLinkParams.TITLE.toString(), attachment.getTitle());
        executionData.put(TestLinkParams.DESCRIPTION.toString(), attachment.getDescription());
        executionData.put(TestLinkParams.FILE_NAME.toString(), attachment.getFileName());
        executionData.put(TestLinkParams.FILE_TYPE.toString(), attachment.getFileType());
        executionData.put(TestLinkParams.CONTENT.toString(), attachment.getContent());
        return executionData;
    }

    /**
     * @param attachment an attachment
     * @return Map of Execution Attachment
     */
    public static Map<String, Object> getExecutionAttachmentMap(Attachment attachment) {
        Map<String, Object> executionData = new HashMap<>();
        executionData.put(TestLinkParams.EXECUTION_ID.toString(), attachment.getFkId());
        executionData.put(TestLinkParams.FK_TABLE.toString(), attachment.getFkTable());
        executionData.put(TestLinkParams.TITLE.toString(), attachment.getTitle());
        executionData.put(TestLinkParams.DESCRIPTION.toString(), attachment.getDescription());
        executionData.put(TestLinkParams.FILE_NAME.toString(), attachment.getFileName());
        executionData.put(TestLinkParams.FILE_TYPE.toString(), attachment.getFileType());
        executionData.put(TestLinkParams.CONTENT.toString(), attachment.getContent());
        return executionData;
    }

    /**
     * @param map a map
     * @return Attachment.
     */
    public static Attachment getAttachment(Map<String, Object> map) {
        Attachment attachment = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    attachment = new Attachment();
                    attachment.setId(id);

                    attachment.setFileName(getString(map, TestLinkResponseParams.NAME.toString()));
                    attachment.setFileType(getString(map, TestLinkResponseParams.FILE_TYPE.toString()));
                    attachment.setTitle(getString(map, TestLinkResponseParams.TITLE.toString()));
                    // TBD: put the date too...
                    attachment.setContent(getString(map, TestLinkResponseParams.CONTENT.toString()));
                    // TBD: description not being returned
                    // attachment.setDescription( getString(map,
                    // TestLinkResponseParams.description.toString() ) );
                    // TBD: returning name instead of file_name
                    // TBD: file size not being returned

                }

            }
        }
        return attachment;
    }

    /**
     * @param requirements list of requirements
     * @return List with Maps of Requirements Grouped By ReSpec.
     */
    public static List<Map<String, Object>> getRequirementsGroupedByReqSpecMap(List<Requirement> requirements) {
        List<Map<String, Object>> requirementsGroupedByReqSpecMap = new ArrayList<>();

        Map<Integer, List<Integer>> tempMap = new HashMap<>();
        for (Requirement requirement : requirements) {
            List<Integer> requirementsArray = tempMap.get(requirement.getReqSpecId());
            if (requirementsArray == null) {
                requirementsArray = new ArrayList<>();
            }
            requirementsArray.add(requirement.getId());
            tempMap.put(requirement.getReqSpecId(), requirementsArray);
        }

        Set<Entry<Integer, List<Integer>>> entrySet = tempMap.entrySet();

        for (Entry<Integer, List<Integer>> entry : entrySet) {
            Map<String, Object> finalMap = new HashMap<>();
            finalMap.put("req_spec", entry.getKey());
            finalMap.put("requirements", entry.getValue());
            requirementsGroupedByReqSpecMap.add(finalMap);
        }

        return requirementsGroupedByReqSpecMap;
    }

    /**
     * @param map a map
     * @return Execution.
     */
    public static Execution getExecution(Map<String, Object> map) {
        Execution execution = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    execution = new Execution();
                    execution.setId(id);

                    execution.setBuildId(getInteger(map, TestLinkResponseParams.BUILD_ID.toString()));
                    execution.setTesterId(getInteger(map, TestLinkResponseParams.TESTER_ID.toString()));
                    String statusText = getString(map, TestLinkResponseParams.STATUS.toString());
                    ExecutionStatus status = ExecutionStatus.getExecutionStatus(statusText.charAt(0));
                    execution.setStatus(status);
                    execution.setTestPlanId(getInteger(map, TestLinkResponseParams.TEST_PLAN_ID.toString()));
                    execution.setTestCaseVersionId(
                            getInteger(map, TestLinkResponseParams.TEST_CASE_VERSION_ID.toString()));
                    execution.setTestCaseVersionNumber(
                            getInteger(map, TestLinkResponseParams.TEST_CASE_VERSION_NUMBER.toString()));
                    Integer executionTypeText = getInteger(map, TestLinkResponseParams.EXECUTION_TYPE.toString());
                    ExecutionType executionType = ExecutionType.getExecutionType(executionTypeText);
                    execution.setExecutionType(executionType);
                    execution.setNotes(getString(map, TestLinkResponseParams.NOTES.toString()));
                    String timestamp = getString(map, TestLinkResponseParams.EXECUTION_TS.toString());
                    if (StringUtils.isNotBlank(timestamp)) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date executionTimeStamp = df.parse(timestamp);
                            execution.setExecutionTimeStamp(executionTimeStamp);
                        } catch (ParseException e) {
                            LOG.log(Level.WARNING, "Failed to parse execution_ts: " + e.getMessage(), e);
                        }
                    }
                }

            }
        }
        return execution;
    }

    /**
     * @param map a map
     * @return Build.
     */
    public static Build getBuild(Map<String, Object> map) {
        Build build = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    build = new Build();
                    build.setId(id);

                    build.setName(getString(map, TestLinkResponseParams.NAME.toString()));
                    build.setNotes(getString(map, TestLinkResponseParams.NOTES.toString()));
                    build.setTestPlanId(getInteger(map, TestLinkResponseParams.TEST_PLAN_ID.toString()));
                    build.setIsActive(getInteger(map, TestLinkResponseParams.ACTIVE.toString()));
                    build.setIsOpen(getInteger(map, TestLinkResponseParams.IS_OPEN.toString()));
                    build.setReleaseDate(getString(map, TestLinkResponseParams.RELEASE_DATE.toString()));
                    build.setClosedDate(getString(map, TestLinkResponseParams.CLOSED_DATE.toString()));
                }

            }
        }
        return build;
    }

    /**
     * @param map a map
     * @return ReportTCResultResponse object.
     */
    public static ReportTCResultResponse getReportTCResultResponse(Map<String, Object> map) {
        ReportTCResultResponse reportTCResultResponse = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.ID.toString());
            if (o != null) {
                int id = Integer.parseInt(o.toString());

                if (id > 0) {
                    reportTCResultResponse = new ReportTCResultResponse();
                    reportTCResultResponse.setExecutionId(id);

                    reportTCResultResponse.setOperation(getString(map, TestLinkResponseParams.OPERATIONS.toString()));
                    reportTCResultResponse.setOverwrite(getBoolean(map, TestLinkResponseParams.OVERWRITE.toString()));
                    reportTCResultResponse.setStatus(getBoolean(map, TestLinkResponseParams.STATUS.toString()));
                    reportTCResultResponse.setMessage(getString(map, TestLinkResponseParams.MESSAGE.toString()));
                    reportTCResultResponse
                            .setBugIDStatus(getBoolean(map, TestLinkResponseParams.BUG_ID_STATUS.toString()));
                    reportTCResultResponse.setCustomFieldStatus(
                            getBoolean(map, TestLinkResponseParams.CUSTOM_FIELD_STATUS.toString()));
                }

            }
        }
        return reportTCResultResponse;
    }

    /**
     * @param map a map
     * @return Custom Field.
     */
    public static CustomField getCustomField(Map<String, Object> map) {
        CustomField customField = null;
        if (map != null && map.size() > 0) {
            // Sometimes we are working with CFs without ID
            customField = new CustomField();
            customField.setId(getInteger(map, TestLinkResponseParams.ID.toString()));
            customField.setDefaultValue(getString(map, TestLinkResponseParams.DEFAULT_VALUE.toString()));
            customField.setDisplayOrder(getInteger(map, TestLinkResponseParams.DISPLAY_ORDER.toString()));
            customField.setEnableOnDesign(getBoolean(map, TestLinkResponseParams.ENABLE_ON_DESIGN.toString()));
            customField.setEnableOnExecution(getBoolean(map, TestLinkResponseParams.ENABLE_ON_EXECUTION.toString()));
            customField.setEnableOnTestPlanDesign(
                    getBoolean(map, TestLinkResponseParams.ENABLE_ON_TEST_PLAN_DESIGN.toString()));
            customField.setLabel(getString(map, TestLinkResponseParams.LABEL.toString()));
            customField.setLengthMax(getInteger(map, TestLinkResponseParams.LENGTH_MAX.toString()));
            customField.setLengthMin(getInteger(map, TestLinkResponseParams.LENGTH_MIN.toString()));
            customField.setLocation(getInteger(map, TestLinkResponseParams.LOCATION.toString()));
            customField.setName(getString(map, TestLinkResponseParams.NAME.toString()));
            customField.setPossibleValues(getString(map, TestLinkResponseParams.POSSIBLE_VALUES.toString()));
            customField.setShowOnDesign(getBoolean(map, TestLinkResponseParams.SHOW_ON_DESIGN.toString()));
            customField.setShowOnExecution(getBoolean(map, TestLinkResponseParams.SHOW_ON_EXECUTION.toString()));
            customField.setShowOnTestPlanDesign(
                    getBoolean(map, TestLinkResponseParams.SHOW_ON_TEST_PLAN_DESIGN.toString()));
            customField.setType(getInteger(map, TestLinkResponseParams.TYPE.toString()));
            customField.setValidRegexp(getString(map, TestLinkResponseParams.VALID_REGEXP.toString()));
            customField.setValue(getString(map, TestLinkResponseParams.VALUE.toString()));

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
    public static void putIfNotNullAndTrue(Map<String, Object> map, String key, Boolean boolValue) {
        if (Boolean.TRUE.equals(boolValue)) {
            map.put(key, 0);
        }
    }

    /**
     * Puts a value into a map if the value is not null.
     *
     * @param map Map.
     * @param key Key.
     * @param value value.
     */
    public static void putIfNotNull(Map<String, Object> map, String key, Object value) {
        if (value != null) {
            map.put(key, value);
        }
    }

    /**
     * Get the object string value, if not null.
     * @param o object
     * @return {@code null} if object is null, otherwise value of the toString method
     */
    public static String getStringValueOrNull(Object o) {
        String value = null;
        if (o != null) {
            value = o.toString();
        }
        return value;
    }

    /**
     * Get a {@link User} from a map with properties.
     * @param map map with properties
     * @return a {@link User}
     */
    @SuppressWarnings("unchecked")
    public static User getUser(Map<String, Object> map) {
        User user = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.DB_ID.toString());
            if (o != null) {
                int dbID = Integer.parseInt(o.toString());
                if (dbID > 0) {
                    user = new User(dbID);
                    user.setLogin(getString(map, TestLinkResponseParams.LOGIN.toString()));
                    user.setFirstName(getString(map, TestLinkResponseParams.FIRST_NAME.toString()));
                    user.setLastName(getString(map, TestLinkResponseParams.LAST_NAME.toString()));
                    user.setLocale(getString(map, TestLinkResponseParams.LOCALE.toString()));
                    user.setEmailAddress(getString(map, TestLinkResponseParams.EMAIL_ADDRESS.toString()));
                    user.setIsActive(getInteger(map, TestLinkResponseParams.IS_ACTIVE.toString()));
                    user.setUserApiKey(getString(map, TestLinkResponseParams.USER_API_KEY.toString()));
                    user.setLoginRegExp(getString(map, TestLinkResponseParams.LOGIN_REGEXP.toString()));
                    user.setTprojectRoles(getInteger(map, TestLinkResponseParams.TPROJECT_ROLES.toString()));
                    user.setTplanRoles(getInteger(map, TestLinkResponseParams.TPLAN_ROLES.toString()));
                    user.setGlobalRole(
                            getRole((Map<String, Object>) map.get(TestLinkResponseParams.GLOBAL_ROLE.toString())));
                    user.setGlobalRoleID(getInteger(map, TestLinkResponseParams.GLOBAL_ROLE_ID.toString()));
                    user.setDefaultTestprojectID(
                            getInteger(map, TestLinkResponseParams.DEFAULT_TESTPROJECT_ID.toString()));
                }
            }
        }
        return user;
    }

    /**
     * Get a {@link Role} from a map with properties.
     * @param map map with properties
     * @return a {@link Role}
     */
    public static Role getRole(Map<String, Object> map) {
        Role role = null;
        if (map != null && map.size() > 0) {
            Object o = map.get(TestLinkResponseParams.DB_ID.toString());
            if (o != null) {
                int dbID = Integer.parseInt(o.toString());
                if (dbID > 0) {
                    role = new Role(dbID);
                    role.setDescription(getString(map, TestLinkResponseParams.DESCRIPTION.toString()));
                    role.setName(getString(map, TestLinkResponseParams.NAME.toString()));
                    role.setRights((Object[]) map.get(TestLinkResponseParams.RIGHTS.toString()));
                }
            }
        }
        return role;
    }

}
