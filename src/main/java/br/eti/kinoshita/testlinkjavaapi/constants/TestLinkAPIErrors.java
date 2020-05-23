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
 * @author Mario Fuentes - http://www.rhiscom.com
 * @since 1.9.3-4
 */
public enum TestLinkAPIErrors {

    GENERAL_ERROR_CODE(-1, ""),
    GENERAL_SUCCESS_CODE(1, ""),
    NOT_YET_IMPLEMENTED(50, ""),
    NO_DEV_KEY(100, ""),
    NO_TEST_CASE_ID(110, ""),
    NO_TEST_CASE_EXTERNAL_ID(110, ""),
    NO_TEST_PLAN_ID(120, ""),
    NO_BUILD_ID(130, ""),
    NO_TEST_MODE(140, ""),
    NO_STATUS(150, ""),
    NO_TEST_PROJECT_ID(160, ""),
    NO_TEST_CASE_NAME(170, ""),
    NO_TEST_SUITE_ID(180, ""),
    MISSING_REQUIRED_PARAMETER(200, ""),
    PARAMETER_NOT_INTEGER(210, ""),
    NO_TEST_SUITE_NAME(220, ""),
    NODE_ID_IS_NOT_INTEGER(230, ""),
    NODE_ID_DOES_NOT_EXIST(231, ""),
    CONFIGURATION_DELETE_EXECUTION_DISABLED(232, ""),
    NO_PLATFORM_ID(233, ""),
    NODE_ID_INVALID_DATA_TYPE(234, ""),
    INVALID_AUTH(2000, ""),
    INSUFFICIENT_RIGHTS(2010, ""),
    INVALID_TEST_PLAN_ID(3000, ""),
    TEST_PLAN_ID_NOT_INTEGER(3010, ""),
    NO_BUILD_FOR_TEST_PLAN_ID(3020, ""),
    TEST_CASE_NOT_IN_TEST_PLAN_ID(3030, ""),
    TEST_PLAN_HAS_NO_BUILDS(3031, ""),
    BAD_BUILD_FOR_TEST_PLAN(3032, ""),
    TEST_PLAN_NAME_DOES_NOT_EXIST(3033, ""),
    TEST_PLAN_NAME_ALREADY_EXISTS(3034, ""),
    PLATFORM_NOT_LINKED_TO_TEST_PLAN(3040, ""),
    TEST_PLAN_HAS_NO_PLATFORMS(3041, ""),
    TEST_CASE_ID_NOT_IN_THE_TEST_PLAN_ID_FOR_PLATFORM(3042, ""),
    MISSING_PLATFORM_ID_BUT_NEEDED(3043, ""),
    PLATFORM_ID_NOT_LINKED_TEST_PLAN(3044, ""),
    LINKED_FEATURE_ALREADY_EXISTS(3045, ""),
    OTHER_VERSION_IS_ALREADY_LINKED(3046, ""),
    INVALID_BUILD_ID(4000, ""),
    BUILD_ID_NOT_INTEGER(4010, ""),
    BUILD_ID_NO_GUESS(4020, ""),
    BUILD_NAME_ALREADY_EXISTS(4030, ""),
    BUILD_NAME_DOES_NOT_EXIST(4040, ""),
    INVALID_TEST_CASE_ID(5000, ""),
    TEST_CASE_ID_NOT_INTEGER(5010, ""),
    TEST_CASE_NAME_NOT_STRING(5020, ""),
    NO_TEST_CASE_BY_THIS_NAME(5030, ""),
    INVALID_TEST_CASE_EXTERNAL_ID(5040, ""),
    INVALID_TEST_CASE_VERSION_NUMBER(5050, ""),
    TEST_CASE_VERSION_NUMBER_KO(5051, ""),
    VERSION_NOT_VALID(5052, ""),
    NO_TEST_CASE_FOUND(5053, ""),
    TEST_CASE_EMPTY_NAME(5054, ""),
    TEST_CASE_NAME_LENGTH_EXCEEDED(5055, ""),
    TEST_CASE_SIBLING_WITH_SAME_NAME_EXISTS(5056, ""),
    INVALID_STATUS(6000, ""),
    ATTACH_TEMP_FILE_CREATION_ERROR(6001, ""),
    ATTACH_DB_WRITER_ERROR(6002, ""),
    ATTACH_FEATURE_DISABLED(6003, ""),
    ATTACH_INVALID_FK(6004, ""),
    ATTACH_INVALID_ATTACHMENT(6005, ""),
    INVALID_TEST_PROJECT_ID(7000, ""),
    TEST_PROJECT_NAME_SYNTAX_ERROR(7001, ""),
    TEST_PROJECT_NAME_EXISTS(7002, ""),
    TEST_PROJECT_TEST_CASE_PREFIX_EXISTS(7003, ""),
    TEST_PROJECT_TEST_CASE_PREFIX_IS_EMPTY(7004, ""),
    TEST_PROJECT_TEST_CASE_PREFIX_IS_TOO_LONG(7005, ""),
    TEST_PLAN_TEST_PROJECT_KO(7006, ""),
    TEST_CASE_TEST_PROJECT_KO(7007, ""),
    TEST_PROJECT_IS_EMPTY(7008, ""),
    TEST_PROJECT_PREFIX_ALREADY_EXISTS(7009, ""),
    REQUIREMENT_SPECIFICATION_TEST_PROJECT_KO(7010, ""),
    TEST_PROJECT_NAME_DOES_NOT_EXIST(7011, ""),
    TEST_PROJECT_COPY_SOURCE_NAME_DOES_NOT_EXIST(7012, ""),
    INVALID_TEST_SUITE_ID(8000, ""),
    TEST_SUITE_DOES_NOT_BELONG_TO_TEST_PROJECT(8001, ""),
    TEST_SUITE_NAME_NOT_STRING(8002, ""),
    INVALID_PARENT_TEST_SUITE_ID(8003, ""),
    NO_CUSTOM_FIELD_BY_THIS_NAME(9000, ""),
    CUSTOM_FIELD_NOT_APP_FOR_NODE_TYPE(9001, ""),
    CUSTOM_FIELD_HAS_NO_DESIGN_SCOPE(9002, ""),
    CUSTOM_FIELD_NOT_ASSIGNED_TO_PROJECT(9003, ""),
    NO_USER_BY_THIS_LOGIN(10000, ""),
    REQUIREMENT_SPECIFICATION_KO(11000, ""),
    REQUIREMENT_SPECIFICATION_IS_EMPTY(11001, ""),
    REQUIMENT_REQUIREMENT_SPECIFICATION_KO(11002, ""),
    REQUIREMENT_KO(11003, "");

    private final Integer code;
    private final String message;

    TestLinkAPIErrors(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get API error code.
     * @return API error code
     */
    public Integer getCode() {
        return this.code;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.code.toString() + ": " + this.message;
    }

    /**
     * Check whether another code is equal to this code.
     * @param code another code
     * @return {@code true} iff the two values are equal
     */
    public boolean isCode(Integer code) {
        return this.code.equals(code);
    }
}
