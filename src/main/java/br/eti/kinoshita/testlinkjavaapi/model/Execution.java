/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;

import java.io.Serializable;


/**
 * @author Bruno P. Kinoshita
 *
 */
public class Execution 
implements Serializable
{

	private Integer id;
	private Integer buildId;
	private Integer testerId;
	// TBD: include execution_ts
	private ExecutionStatus status;
	private Integer testPlanId;
	private Integer testCaseVersionId;
	private Integer testCaseVersionNumber;
	private ExecutionType executionType;
	private String notes;
	/**
	 * 
	 */
	public Execution() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param buildId
	 * @param testerId
	 * @param status
	 * @param testPlanId
	 * @param testCaseVersionId
	 * @param testCaseVersionNumber
	 * @param executionType
	 * @param notes
	 */
	public Execution(Integer id, Integer buildId, Integer testerId,
			ExecutionStatus status, Integer testPlanId,
			Integer testCaseVersionId, Integer testCaseVersionNumber,
			ExecutionType executionType, String notes) {
		super();
		this.id = id;
		this.buildId = buildId;
		this.testerId = testerId;
		this.status = status;
		this.testPlanId = testPlanId;
		this.testCaseVersionId = testCaseVersionId;
		this.testCaseVersionNumber = testCaseVersionNumber;
		this.executionType = executionType;
		this.notes = notes;
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
	 * @return the buildId
	 */
	public Integer getBuildId() {
		return buildId;
	}
	/**
	 * @param buildId the buildId to set
	 */
	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}
	/**
	 * @return the testerId
	 */
	public Integer getTesterId() {
		return testerId;
	}
	/**
	 * @param testerId the testerId to set
	 */
	public void setTesterId(Integer testerId) {
		this.testerId = testerId;
	}
	/**
	 * @return the status
	 */
	public ExecutionStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ExecutionStatus status) {
		this.status = status;
	}
	/**
	 * @return the testPlanId
	 */
	public Integer getTestPlanId() {
		return testPlanId;
	}
	/**
	 * @param testPlanId the testPlanId to set
	 */
	public void setTestPlanId(Integer testPlanId) {
		this.testPlanId = testPlanId;
	}
	/**
	 * @return the testCaseVersionId
	 */
	public Integer getTestCaseVersionId() {
		return testCaseVersionId;
	}
	/**
	 * @param testCaseVersionId the testCaseVersionId to set
	 */
	public void setTestCaseVersionId(Integer testCaseVersionId) {
		this.testCaseVersionId = testCaseVersionId;
	}
	/**
	 * @return the testCaseVersionNumber
	 */
	public Integer getTestCaseVersionNumber() {
		return testCaseVersionNumber;
	}
	/**
	 * @param testCaseVersionNumber the testCaseVersionNumber to set
	 */
	public void setTestCaseVersionNumber(Integer testCaseVersionNumber) {
		this.testCaseVersionNumber = testCaseVersionNumber;
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
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Execution [id=" + id + ", buildId=" + buildId + ", testerId="
				+ testerId + ", status=" + status + ", testPlanId="
				+ testPlanId + ", testCaseVersionId=" + testCaseVersionId
				+ ", testCaseVersionNumber=" + testCaseVersionNumber
				+ ", executionType=" + executionType + ", notes=" + notes + "]";
	}
		
}
