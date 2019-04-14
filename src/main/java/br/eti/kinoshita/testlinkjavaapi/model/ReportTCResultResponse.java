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

/**
 *
 * <ul>
 * <li>20101130 - BUGID: 3123764 - kinow - reportTCresult not returning execution data</li>
 * </ul>
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-4
 */
public class ReportTCResultResponse implements Serializable {

    private static final long serialVersionUID = -5742000858317187375L;

    private String operation;
    private Boolean overwrite;
    private Boolean status;
    private String message;
    private Integer executionId;
    private Boolean bugIDStatus;
    private Boolean customFieldStatus;

    /**
     *
     */
    public ReportTCResultResponse() {
        super();
    }

    /**
     * @param operation operation
     * @param overwrite flag to overwrite it or not
     * @param status status
     * @param message message
     * @param executionId execution ID
     * @param bugIDStatus bug ID status
     * @param customFieldStatus custom field status
     */
    public ReportTCResultResponse(String operation, Boolean overwrite, Boolean status, String message,
            Integer executionId, Boolean bugIDStatus, Boolean customFieldStatus) {
        super();
        this.operation = operation;
        this.overwrite = overwrite;
        this.status = status;
        this.message = message;
        this.executionId = executionId;
        this.bugIDStatus = bugIDStatus;
        this.customFieldStatus = customFieldStatus;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return the overwrite
     */
    public Boolean getOverwrite() {
        return overwrite;
    }

    /**
     * @param overwrite the overwrite to set
     */
    public void setOverwrite(Boolean overwrite) {
        this.overwrite = overwrite;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the executionId
     */
    public Integer getExecutionId() {
        return executionId;
    }

    /**
     * @param executionId the executionId to set
     */
    public void setExecutionId(Integer executionId) {
        this.executionId = executionId;
    }

    /**
     * @return the bugIDStatus
     */
    public Boolean getBugIDStatus() {
        return bugIDStatus;
    }

    /**
     * @param bugIDStatus the bugIDStatus to set
     */
    public void setBugIDStatus(Boolean bugIDStatus) {
        this.bugIDStatus = bugIDStatus;
    }

    /**
     * @return the customFieldStatus
     */
    public Boolean getCustomFieldStatus() {
        return customFieldStatus;
    }

    /**
     * @param customFieldStatus the customFieldStatus to set
     */
    public void setCustomFieldStatus(Boolean customFieldStatus) {
        this.customFieldStatus = customFieldStatus;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ReportTCResultResponse [operation=" + operation + ", overwrite=" + overwrite + ", status=" + status
                + ", message=" + message + ", executionId=" + executionId + ", bugIDStatus=" + bugIDStatus
                + ", customFieldStatus=" + customFieldStatus + "]";
    }

}
