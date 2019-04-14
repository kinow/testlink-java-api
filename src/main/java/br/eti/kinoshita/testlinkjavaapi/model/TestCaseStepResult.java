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

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;

/**
 * @author Kai Adelmann kai.adelmann@gmail.com
 * @since 1.9.18
 */
public class TestCaseStepResult implements Serializable {

    private static final long serialVersionUID = -2964599381390137202L;

    private Integer step_number;
    private ExecutionStatus result;
    private String notes;

    /**
     *
     */
    public TestCaseStepResult() {
        super();
    }

    /**
     * @param number number of the test step
     * @param result ExecutionStatus
     * @param notes execution notes for the test step
     * @param active whether test case step is active or not
     * @param executionType test case step execution type
     */
    public TestCaseStepResult(Integer number, ExecutionStatus result, String notes,
            Boolean active, ExecutionType executionType) {
        super();
        this.step_number = number;
        this.result = result;
        this.notes = notes;
    }


    /**
     * @return the number
     */
    public Integer getNumber() {
        return step_number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.step_number = number;
    }

    /**
     * @return the result
     */
    public ExecutionStatus getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(ExecutionStatus result) {
        this.result = result;
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TestCaseStepResult [number=" + step_number
                + ", result=" + result + ", notes=" + notes + "]";
    }

}
