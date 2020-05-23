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
package br.eti.kinoshita.testlinkjavaapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;

/**
 * Represents a Test Case in TesLink.
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestCase implements Serializable {

    private static final long serialVersionUID = 1191213146080628314L;

    private Integer id;
    private String name;
    private Integer testSuiteId;
    private Integer testProjectId;
    private String authorLogin;
    private String summary;
    private List<TestCaseStep> steps;
    private String preconditions;
    private TestCaseStatus testCaseStatus;
    private TestImportance testImportance;
    private ExecutionType executionType;
    private Integer executionOrder;
    private Integer order;
    private Integer internalId;
    private Integer externalId;
    private String fullExternalId;
    private Boolean checkDuplicatedName;
    private ActionOnDuplicate actionOnDuplicatedName;
    private Integer versionId;
    private Integer version;
    private Integer parentId;
    private List<CustomField> customFields;
    private ExecutionStatus executionStatus;
    private Platform platform;
    private Integer featureId;
    private List<String> keywords;

    /**
     * Constructor.
     */
    public TestCase() {
        super();
        this.steps = new ArrayList<>();
        this.customFields = new ArrayList<>();
    }

    /**
     * Constructor with args.
     *
     * @param id ID
     * @param name name
     * @param testSuiteId test suite ID
     * @param testProjectId test project ID
     * @param authorLogin author login
     * @param summary summary
     * @param steps steps
     * @param preconditions preconditions
     * @param testcaseStatus test case status
     * @param testImportance test importance
     * @param executionType execution type
     * @param executionOrder execution order
     * @param order order
     * @param internalId internal ID
     * @param externalId external ID
     * @param fullExternalId full external ID
     * @param checkDuplicatedName check for duplicated name
     * @param actionOnDuplicatedName action to take when a duplicated name is found
     * @param versionId version ID
     * @param version version
     * @param parentId parent ID
     * @param customFields custom fields
     * @param executionStatus execution status
     * @param platform platform
     * @param featureId feature ID
     */
    public TestCase(Integer id, String name, Integer testSuiteId, Integer testProjectId, String authorLogin,
            String summary, List<TestCaseStep> steps, String preconditions, TestCaseStatus testcaseStatus,
            TestImportance testImportance, ExecutionType executionType, Integer executionOrder, Integer order,
            Integer internalId, Integer externalId, String fullExternalId, Boolean checkDuplicatedName,
            ActionOnDuplicate actionOnDuplicatedName, Integer versionId, Integer version, Integer parentId,
            List<CustomField> customFields, ExecutionStatus executionStatus, Platform platform, Integer featureId) {
        super();
        this.id = id;
        this.name = name;
        this.testSuiteId = testSuiteId;
        this.testProjectId = testProjectId;
        this.authorLogin = authorLogin;
        this.summary = summary;
        this.steps = steps;
        this.preconditions = preconditions;
        this.testCaseStatus = testcaseStatus;
        this.testImportance = testImportance;
        this.executionType = executionType;
        this.executionOrder = executionOrder;
        this.order = order;
        this.internalId = internalId;
        this.externalId = externalId;
        this.fullExternalId = fullExternalId;
        this.checkDuplicatedName = checkDuplicatedName;
        this.actionOnDuplicatedName = actionOnDuplicatedName;
        this.versionId = versionId;
        this.version = version;
        this.parentId = parentId;
        this.customFields = customFields;
        this.executionStatus = executionStatus;
        this.platform = platform;
        this.featureId = featureId;
    }

    /**
     * @return the parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the versionId
     */
    public Integer getVersionId() {
        return versionId;
    }

    /**
     * @param versionId the versionId to set
     */
    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the testSuiteId
     */
    public Integer getTestSuiteId() {
        return testSuiteId;
    }

    /**
     * @param testSuiteId the testSuiteId to set
     */
    public void setTestSuiteId(Integer testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    /**
     * @return the testProjectId
     */
    public Integer getTestProjectId() {
        return testProjectId;
    }

    /**
     * @param testProjectId the testProjectId to set
     */
    public void setTestProjectId(Integer testProjectId) {
        this.testProjectId = testProjectId;
    }

    /**
     * @return the authorLogin
     */
    public String getAuthorLogin() {
        return authorLogin;
    }

    /**
     * @param authorLogin the authorLogin to set
     */
    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the steps
     */
    public List<TestCaseStep> getSteps() {
        return steps;
    }

    /**
     * @param steps the steps to set
     */
    public void setSteps(List<TestCaseStep> steps) {
        this.steps = steps;
    }

    /**
     * @return the preconditions
     */
    public String getPreconditions() {
        return preconditions;
    }

    /**
     * @param preconditions the preconditions to set
     */
    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    /**
     * @return the test case status
     */
    public TestCaseStatus getTestCaseStatus() {
        return testCaseStatus;
    }

    /**
     * @param testcaseStatus the test case status
     */
    public void setTestCaseStatus(TestCaseStatus testcaseStatus) {
        this.testCaseStatus = testcaseStatus;
    }

    /**
     * @return the testImportance
     */
    public TestImportance getTestImportance() {
        return testImportance;
    }

    /**
     * @param testImportance the testImportance to set
     */
    public void setTestImportance(TestImportance testImportance) {
        this.testImportance = testImportance;
    }

    /**
     * @return the executionOrder
     */
    public Integer getExecutionOrder() {
        return executionOrder;
    }

    /**
     * @param executionOrder the executionOrder to set
     */
    public void setExecutionOrder(Integer executionOrder) {
        this.executionOrder = executionOrder;
    }

    /**
     * @return the executionType
     */
    public ExecutionType getExecutionType() {
        return executionType;
    }

    /**
     * @param executionType the executionType to set
     */
    public void setExecutionType(ExecutionType executionType) {
        this.executionType = executionType;
    }

    /**
     * @return the order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * @return the internalId
     */
    public Integer getInternalId() {
        return internalId;
    }

    /**
     * @param internalId the internalId to set
     */
    public void setInternalId(Integer internalId) {
        this.internalId = internalId;
    }

    /**
     * @return the external Id
     */
    public Integer getExternalId() {
        return externalId;
    }

    /**
     * @param externalId the externalId to set
     */
    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    /**
     *
     * @return the full external Id, composed by the prefix + externalId
     */
    public String getFullExternalId() {
        return fullExternalId;
    }

    /**
     *
     * @param fullExternalId the full externalId to set
     */
    public void setFullExternalId(String fullExternalId) {
        this.fullExternalId = fullExternalId;
    }

    /**
     * @return the checkDuplicatedName
     */
    public Boolean getCheckDuplicatedName() {
        return checkDuplicatedName;
    }

    /**
     * @param checkDuplicatedName the checkDuplicatedName to set
     */
    public void setCheckDuplicatedName(Boolean checkDuplicatedName) {
        this.checkDuplicatedName = checkDuplicatedName;
    }

    /**
     * @return the actionOnDuplicatedName
     */
    public ActionOnDuplicate getActionOnDuplicatedName() {
        return actionOnDuplicatedName;
    }

    /**
     * @param actionOnDuplicatedName the actionOnDuplicatedName to set
     */
    public void setActionOnDuplicatedName(ActionOnDuplicate actionOnDuplicatedName) {
        this.actionOnDuplicatedName = actionOnDuplicatedName;
    }

    /**
     * @return the customFields
     */
    public List<CustomField> getCustomFields() {
        return customFields;
    }

    /**
     * @param customFields the customFields to set
     */
    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }

    /**
     * @return the executionStatus
     */
    public ExecutionStatus getExecutionStatus() {
        return executionStatus;
    }

    /**
     * @param executionStatus the executionStatus to set
     */
    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * @return the featureId
     */
    public Integer getFeatureId() {
        return featureId;
    }

    /**
     * @param featureId the featureId to set
     */
    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    /**
     * @return keywords
     */
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * @param keywords keywords
     */
    public void setKeywords(List<String> keywords) {
        // Sorted keywords (for performance)
        Collections.sort(keywords);
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "TestCase [id=" + id + ", name=" + name + ", testSuiteId=" + testSuiteId + ", testProjectId="
                + testProjectId + ", authorLogin=" + authorLogin + ", summary=" + summary + ", steps=" + steps
                + ", preconditions=" + preconditions + ", testCaseStatus=" + testCaseStatus + ", testImportance="
                + testImportance + ", executionType=" + executionType + ", executionOrder=" + executionOrder
                + ", order=" + order + ", internalId=" + internalId + ", externalId=" + externalId + ", fullExternalId="
                + fullExternalId + ", checkDuplicatedName=" + checkDuplicatedName + ", actionOnDuplicatedName="
                + actionOnDuplicatedName + ", versionId=" + versionId + ", version=" + version + ", parentId="
                + parentId + ", customFields=" + customFields + ", executionStatus=" + executionStatus + ", platform="
                + platform + ", featureId=" + featureId + ", keywords=" + keywords + "]";
    }

}
