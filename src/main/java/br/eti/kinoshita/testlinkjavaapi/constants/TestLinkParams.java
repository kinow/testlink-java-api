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
package br.eti.kinoshita.testlinkjavaapi.constants;

/**
 *
 * <p>
 * This enum contains a list of the parameters passed to TestLink.
 * </p>
 *
 * <ul>
 * <li>20101129 - BUGID: 3122360 - kinow - Wrong execution type parameter name</li>
 * </ul>
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum TestLinkParams {

    ACTION_ON_DUPLICATED_NAME("actiononduplicatedname"),
    ACTIONS("actions"),
    ACTION("action"),
    ACTIVE("active"),
    ASSIGNED_TO("assignedto"),
    AUTHOR_LOGIN("authorlogin"),
    BUG_ID("bugid"),
    BUILD_ID("buildid"),
    BUILD_NAME("buildname"),
    BUILD_NOTES("buildnotes"),
    CHECK_DUPLICATED_NAME("checkduplicatedname"),
    CONTENT("content"),
    CUSTOM_FIELD_NAME("customfieldname"),
    CUSTOM_FIELDS("customfields"),
    DEEP("deep"),
    DESCRIPTION("description"),
    DETAILS("details"),
    DEV_KEY("devKey"),
    ENABLE_AUTOMATION("automationEnabled"),
    ENABLE_INVENTORY("inventoryEnabled "),
    ENABLE_REQUIREMENTS("requirementsEnabled"),
    ENABLE_TEST_PRIORITY("testPriorityEnabled"),
    ESTIMATED_EXECUTION_DURATION("estimated_execution_duration"),
    EXECUTED("executed"),
    EXECUTE_STATUS("executestatus"),
    EXECUTION("execution"),
    EXECUTION_ID("executionid"),
    EXECUTION_DURATION("execduration"),
    EXECUTION_TYPE("executiontype"),
    STEP_EXECUTION_TYPE("execution_type"),
    EXPECTED_RESULTS("expected_results"),
    FILE_NAME("filename"),
    FILE_TYPE("filetype"),
    FK_ID("fkid"),
    FK_TABLE("fktable"),
    GET_STEP_INFO("getstepsinfo"),
    GUESS("guess"),
    IMPORTANCE("importance"),
    INTERNAL_ID("internalId"),
    KEYWORD_ID("keywordid"),
    KEYWORDS("keywords"),
    NAME("name"),
    NODE_ID("nodeid"),
    NOTES("notes"),
    OPTIONS("options"),
    ORDER("order"),
    OVERWRITE("overwrite"),
    PARENT_ID("parentid"),
    PLATFORM_ID("platformid"),
    PLATFORM_NAME("platformname"),
    PRECONDITIONS("preconditions"),
    PUBLIC("public"),
    REQUIREMENT_SPECIFICATION_ID("reqspecid"),
    REQUIREMENT_ID("requirementid"),
    REQUIREMENTS("requirements"),
    RESULT("result"),
    SUMMARY("summary"),
    // TBD: what is the difference between status and executestatus
    STATUS("status"),
    STEP_NUMBER("step_number"),
    STEPS("steps"),
    STR("str"),
    TEST_PROJECT_NAME("testprojectname"),
    TEST_CASE_EXTERNAL_ID("testcaseexternalid"),
    TEST_CASE_ID("testcaseid"),
    TEST_CASE_NAME("testcasename"),
    TEST_CASE_PATH_NAME("testcasepathname"),
    TEST_CASE_PREFIX("testcaseprefix"),
    TEST_MODE("testmode"),
    TEST_PLAN_ID("testplanid"),
    TEST_PLAN_NAME("testplanname"),
    TEST_PROJECT_ID("testprojectid"),
    TEST_SUITE_ID("testsuiteid"),
    TEST_SUITE_ID2("testsuite_id"),
    TEST_SUITE_NAME("testsuitename"),
    TIMESTAMP("timestamp"),
    TITLE("title"),
    URGENCY("urgency"),
    USER("user"),
    VERSION("version");

    private final String value;

    TestLinkParams(String value) {
        this.value = value;
    }

    /**
     * Print param value.
     * @return param value
     */
    public String toString() {
        return this.value;
    }

}
