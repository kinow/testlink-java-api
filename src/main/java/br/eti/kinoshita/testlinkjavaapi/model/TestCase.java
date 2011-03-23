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
package br.eti.kinoshita.testlinkjavaapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestCase 
implements Serializable
{
	
	private static final long serialVersionUID = -1135629593821300695L;
	
	private Integer id;
	private String name;
	private Integer testSuiteId;
	private Integer testProjectId;
	private String authorLogin;
	private String summary; 
	private List<TestCaseStep> steps;
	private String preconditions;
	private TestImportance testImportance;
	private ExecutionType executionType;
	private Integer executionOrder;
	private Integer order;
	private Integer internalId;
	private Boolean checkDuplicatedName;
	private String actionOnDuplicatedName;
	private Integer versionId;
	private Integer version;
	private Integer parentId;
	private List<CustomField> customFields;
	private ExecutionStatus executionStatus;
	
	/**
	 * 
	 */
	public TestCase() {
		super();
		this.steps = new ArrayList<TestCaseStep>();
		this.customFields = new ArrayList<CustomField>();
	}
	/**
	 * @param id
	 * @param name
	 * @param testSuiteId
	 * @param testProjectId
	 * @param authorLogin
	 * @param summary
	 * @param steps
	 * @param preconditions
	 * @param testImportance
	 * @param executionType
	 * @param executionOrder
	 * @param order
	 * @param internalId
	 * @param checkDuplicatedName
	 * @param actionOnDuplicatedName
	 * @param versionId
	 * @param version
	 * @param parentId
	 * @param customFields
	 * @param executionStatus
	 */
	public TestCase(Integer id, String name, Integer testSuiteId,
			Integer testProjectId, String authorLogin, String summary,
			List<TestCaseStep> steps, String preconditions,
			TestImportance testImportance, ExecutionType executionType, 
			Integer executionOrder, 
			Integer order, Integer internalId, Boolean checkDuplicatedName,
			String actionOnDuplicatedName, Integer versionId, Integer version,
			Integer parentId, List<CustomField> customFields, 
			ExecutionStatus executionStatus) {
		super();
		this.id = id;
		this.name = name;
		this.testSuiteId = testSuiteId;
		this.testProjectId = testProjectId;
		this.authorLogin = authorLogin;
		this.summary = summary;
		this.steps = steps;
		this.preconditions = preconditions;
		this.testImportance = testImportance;
		this.executionType = executionType;
		this.executionOrder = executionOrder;
		this.order = order;
		this.internalId = internalId;
		this.checkDuplicatedName = checkDuplicatedName;
		this.actionOnDuplicatedName = actionOnDuplicatedName;
		this.versionId = versionId;
		this.version = version;
		this.parentId = parentId;
		this.customFields = customFields;
		this.executionStatus = executionStatus;
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
	public Integer getVersion()
	{
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion( Integer version )
	{
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
	public String getActionOnDuplicatedName() {
		return actionOnDuplicatedName;
	}
	/**
	 * @param actionOnDuplicatedName the actionOnDuplicatedName to set
	 */
	public void setActionOnDuplicatedName(String actionOnDuplicatedName) {
		this.actionOnDuplicatedName = actionOnDuplicatedName;
	}
	
	/**
	 * @return the customFields
	 */
	public List<CustomField> getCustomFields()
	{
		return customFields;
	}
	/**
	 * @param customFields the customFields to set
	 */
	public void setCustomFields( List<CustomField> customFields )
	{
		this.customFields = customFields;
	}
	/**
	 * @return the executionStatus
	 */
	public ExecutionStatus getExecutionStatus()
	{
		return executionStatus;
	}
	/**
	 * @param executionStatus the executionStatus to set
	 */
	public void setExecutionStatus( ExecutionStatus executionStatus )
	{
		this.executionStatus = executionStatus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TestCase [id=" + id + ", name=" + name + ", testSuiteId="
				+ testSuiteId + ", testProjectId=" + testProjectId
				+ ", authorLogin=" + authorLogin + ", summary=" + summary
				+ ", steps=" + steps + ", preconditions=" + preconditions
				+ ", testImportance=" + testImportance + ", executionType="
				+ executionType + ", order=" + order + ", internalId="
				+ internalId + ", checkDuplicatedName=" + checkDuplicatedName
				+ ", actionOnDuplicatedName=" + actionOnDuplicatedName
				+ ", versionId=" + versionId + ", version=" + version
				+ ", parentId=" + parentId + ", customFields=" + customFields
				+ ", executionStatus=" + executionStatus + "]";
	}
	
}
