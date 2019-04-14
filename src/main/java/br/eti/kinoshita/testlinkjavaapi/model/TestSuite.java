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

import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestSuite implements Serializable {

    private static final long serialVersionUID = 1808991369379189571L;

    private Integer id;
    private Integer testProjectId;
    private String name;
    private String details;
    private Integer parentId;
    private Integer order;
    private Boolean checkDuplicatedName;
    private ActionOnDuplicate actionOnDuplicatedName;

    /**
     *
     */
    public TestSuite() {
        super();
    }

    /**
     * @param id ID
     * @param testProjectId test project ID
     * @param name name
     * @param details details
     * @param parentId parent ID
     * @param order order
     * @param checkDuplicatedName check for duplicated name flag
     * @param actionOnDuplicatedName what action to take if a duplicated name is found
     */
    public TestSuite(Integer id, Integer testProjectId, String name, String details, Integer parentId, Integer order,
            Boolean checkDuplicatedName, ActionOnDuplicate actionOnDuplicatedName) {
        super();
        this.id = id;
        this.testProjectId = testProjectId;
        this.name = name;
        this.details = details;
        this.parentId = parentId;
        this.order = order;
        this.checkDuplicatedName = checkDuplicatedName;
        this.actionOnDuplicatedName = actionOnDuplicatedName;
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
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TestSuite [id=" + id + ", testProjectId=" + testProjectId + ", name=" + name + ", details=" + details
                + ", parentId=" + parentId + ", order=" + order + ", checkDuplicatedName=" + checkDuplicatedName
                + ", actionOnDuplicatedName=" + actionOnDuplicatedName + "]";
    }

}
