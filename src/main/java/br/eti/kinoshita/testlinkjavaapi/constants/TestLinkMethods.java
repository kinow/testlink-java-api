/*
 * The MIT License
 *
 * Copyright (c) 2010 Bruno P. Kinoshita http://www.kinoshita.eti.br
<<<<<<< HEAD
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
package br.eti.kinoshita.testlinkjavaapi.constants;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum TestLinkMethods {

    ASSIGN_TEST_CASE_EXECUTION_TASK("tl.assignTestCaseExecutionTask"),
    CREATE_TEST_PROJECT("tl.createTestProject"),
    GET_TEST_PROJECT_BY_NAME("tl.getTestProjectByName"),
    CHECK_DEV_KEY("tl.checkDevKey"),
    GET_PROJECTS("tl.getProjects"),
    CREATE_TEST_PLAN("tl.createTestPlan"),
    GET_TEST_PLAN_BY_NAME("tl.getTestPlanByName"),
    GET_PROJECT_TEST_PLANS("tl.getProjectTestPlans"),
    GET_TEST_PLAN_PLATFORMS("tl.getTestPlanPlatforms"),
    CREATE_TEST_CASE("tl.createTestCase"),
    UPDATE_TEST_CASE("tl.updateTestCase"),
    CREATE_TEST_CASE_STEPS("tl.createTestCaseSteps"),
    DELETE_TEST_CASE_STEPS("tl.deleteTestCaseSteps"),
    CREATE_TEST_SUITE("tl.createTestSuite"),
    ADD_TEST_CASE_TO_TEST_PLAN("tl.addTestCaseToTestPlan"),
    DOES_USER_EXIST("tl.doesUserExist"),
    GET_USER_BY_LOGIN("tl.getUserByLogin"),
    SAY_HELLO("tl.sayHello"),
    ABOUT("tl.about"),
    GET_TEST_SUITE_BY_ID("tl.getTestSuiteByID"),
    SET_TEST_MODE("tl.setTestMode"),
    REPEAT("tl.repeat"),
    GET_TEST_CASE("tl.getTestCase"),
    GET_TEST_CASES_FOR_TEST_SUITE("tl.getTestCasesForTestSuite"),
    CREATE_BUILD("tl.createBuild"),
    GET_TEST_CASES_FOR_TEST_PLAN("tl.getTestCasesForTestPlan"),
    GET_TEST_CASE_ID_BY_NAME("tl.getTestCaseIDByName"),
    GET_TEST_SUITES_FOR_TEST_PLAN("tl.getTestSuitesForTestPlan"),
    UPLOAD_ATTACHMENT("tl.uploadAttachment"),
    UPLOAD_TEST_CASE_ATTACHMENT("tl.uploadTestCaseAttachment"),
    UPLOAD_TEST_SUITE_ATTACHMENT("tl.uploadTestSuiteAttachment"),
    UPLOAD_TEST_PROJECT_ATTACHMENT("tl.uploadTestProjectAttachment"),
    UPLOAD_REQUIREMENT_ATTACHMENT("tl.uploadRequirementAttachment"),
    UPLOAD_REQUIREMENT_SPECIFICATION_ATTACHMENT("tl.uploadRequirementSpecificationAttachment"),
    GET_TEST_CASE_ATTACHMENTS("tl.getTestCaseAttachments"),
    GET_TEST_SUITE_ATTACHMENTS("tl.getTestSuiteAttachments"),
    UPLOAD_EXECUTION_ATTACHMENT("tl.uploadExecutionAttachment"),
    DELETE_EXECUTION("tl.deleteExecution"),
    GET_FULL_PATH("tl.getFullPath"),
    ASSIGN_REQUIREMENTS("tl.assignRequirements"),
    GET_TEST_SUITES_FOR_TEST_SUITE("tl.getTestSuitesForTestSuite"),
    GET_FIRST_LEVEL_TEST_SUITES_FOR_TEST_PROJECT("tl.getFirstLevelTestSuitesForTestProject"),
    REPORT_TC_RESULT("tl.reportTCResult"),
    GET_LAST_EXECUTION_RESULT("tl.getLastExecutionResult"),
    GET_BUILDS_FOR_TEST_PLAN("tl.getBuildsForTestPlan"),
    GET_LATEST_BUILD_FOR_TEST_PLAN("tl.getLatestBuildForTestPlan"),
    GET_TEST_CASE_KEYWORDS("tl.getTestCaseKeywords"),
    GET_TEST_CASE_CUSTOM_FIELD_DESIGN_VALUE("tl.getTestCaseCustomFieldDesignValue"),
    GET_TEST_CASE_CUSTOM_FIELD_TEST_PLAN_DESIGN_VALUE("tl.getTestCaseCustomFieldTestPlanDesignValue"),
    GET_TEST_CASE_CUSTOM_FIELD_EXECUTION_VALUE("tl.getTestCaseCustomFieldExecutionValue"),
    GET_TEST_PLAN_CUSTOM_FIELD_DESIGN_VALUE("tl.getTestPlanCustomFieldDesignValue"),
    GET_TOTALS_FOR_TEST_PLAN("tl.getTotalsForTestPlan"),
    GET_EXEC_COUNTERS_BY_BUILD("tl.getExecCountersByBuild"),
    UPDATE_TEST_CASE_CUSTOM_FIELD_VALUE("tl.updateTestCaseCustomFieldDesignValue"),
    SET_TEST_CASE_EXECUTION_TYPE("tl.setTestCaseExecutionType"),
    GET_PROJECT_PLATFORMS("tl.getProjectPlatforms"),
    REMOVE_PLATFORM_FROM_TEST_PLAN("tl.removePlatformFromTestPlan"),
    ADD_PLATFORM_TO_TEST_PLAN("tl.addPlatformToTestPlan"),
    UPDATE_BUILD_CUSTOM_FIELDS("tl.updateBuildCustomFieldsValues"),
    ADD_TEST_CASE_KEY_WORDS("tl.addTestCaseKeywords"),
    GET_TEST_CASE_BUGS("tl.getTestCaseBugs");

    private final String value;

    TestLinkMethods(String value) {
        this.value = value;
    }

    /**
     * Print the method value.
     * @return the method value
     */
    public String toString() {
        return this.value;
    }

}
