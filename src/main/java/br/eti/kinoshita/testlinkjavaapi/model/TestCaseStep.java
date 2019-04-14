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

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestCaseStep implements Serializable {

    private static final long serialVersionUID = -2964599381390137202L;

    private Integer id;
    private Integer testCaseVersionId;
    private Integer number;
    private String actions;
    private String expectedResults;
    private Boolean active;
    private ExecutionType executionType;

    /**
     *
     */
    public TestCaseStep() {
        super();
    }

    /**
     * @param id ID
     * @param testCaseVersionId test case version ID
     * @param number number
     * @param actions actions
     * @param expectedResults expected results
     * @param active active flag
     * @param executionType execution type
     */
    public TestCaseStep(Integer id, Integer testCaseVersionId, Integer number, String actions, String expectedResults,
            Boolean active, ExecutionType executionType) {
        super();
        this.id = id;
        this.testCaseVersionId = testCaseVersionId;
        this.number = number;
        this.actions = actions;
        this.expectedResults = expectedResults;
        this.active = active;
        this.executionType = executionType;
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
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the actions
     */
    public String getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(String actions) {
        this.actions = actions;
    }

    /**
     * @return the expectedResults
     */
    public String getExpectedResults() {
        return expectedResults;
    }

    /**
     * @param expectedResults the expectedResults to set
     */
    public void setExpectedResults(String expectedResults) {
        this.expectedResults = expectedResults;
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
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TestCaseStep [id=" + id + ", testCaseVersionId=" + testCaseVersionId + ", number=" + number
                + ", actions=" + actions + ", expectedResults=" + expectedResults + ", active=" + active
                + ", executionType=" + executionType + "]";
    }

}
