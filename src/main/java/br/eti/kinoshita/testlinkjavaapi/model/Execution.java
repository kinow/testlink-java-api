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
import java.util.Date;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class Execution implements Serializable {

    private static final long serialVersionUID = -8012620477419689135L;

    private Integer id;
    private Integer buildId;
    private Integer testerId;
    private Date executionTimeStamp;
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
    }

    /**
     * Constructor with args.
     *
     * @param id ID
     * @param buildId build ID
     * @param testerId tester ID
     * @param executionTimeStamp execution time stamp
     * @param status status
     * @param testPlanId test plan ID
     * @param testCaseVersionId test case version ID
     * @param testCaseVersionNumber test case version number
     * @param executionType execution type
     * @param notes notes
     */
    public Execution(Integer id, Integer buildId, Integer testerId, Date executionTimeStamp, ExecutionStatus status,
            Integer testPlanId, Integer testCaseVersionId, Integer testCaseVersionNumber, ExecutionType executionType,
            String notes) {
        super();
        this.id = id;
        this.buildId = buildId;
        this.testerId = testerId;
        this.executionTimeStamp = new Date(executionTimeStamp.getTime());
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

    /**
     * @return the executionTimeStamp
     */
    public Date getExecutionTimeStamp() {
        return new Date(executionTimeStamp.getTime());
    }

    /**
     * @param executionTimeStamp the executionTimeStamp to set
     */
    public void setExecutionTimeStamp(Date executionTimeStamp) {
        this.executionTimeStamp = new Date(executionTimeStamp.getTime());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Execution [id=" + id + ", buildId=" + buildId + ", testerId=" + testerId + ", executionTimeStamp="
                + executionTimeStamp + ", status=" + status + ", testPlanId=" + testPlanId + ", testCaseVersionId="
                + testCaseVersionId + ", testCaseVersionNumber=" + testCaseVersionNumber + ", executionType="
                + executionType + ", notes=" + notes + "]";
    }

}
