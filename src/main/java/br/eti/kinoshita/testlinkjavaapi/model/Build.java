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
 * <p>
 * TestLink Build object. Represents a Build in TestLink system. A build is,
 * basically, an instance of a Test Plan.
 * </p>
 *
 * <ul>
 * <li>20101129 - BUGID: 3122320 - kinow - Modify Build methods to follow
 * standard naming</li>
 * </ul>
 *
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class Build implements Serializable {

    private static final long serialVersionUID = 139468407361322252L;

    private Integer id;
    private Integer isActive;
    private Integer isOpen;
    private Integer testPlanId;
    private String buildName;
    private String buildNotes;
    private String releaseDate;
    private String closedDate;

    /**
     *
     */
    public Build() {
        super();
    }

    /**
     * @param id ID
     * @param testPlanId test plan ID
     * @param name name
     * @param notes notes
     */
    public Build(Integer id, Integer testPlanId, String name, String notes) {
        super();
        this.id = id;
        this.testPlanId = testPlanId;
        this.buildName = name;
        this.buildNotes = notes;
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
     * @return the name
     */
    public String getName() {
        return buildName;
    }

    /**
     * @param name the buildName to set
     */
    public void setName(String name) {
        this.buildName = name;
    }

    /**
     * @return the buildNotes
     */
    public String getNotes() {
        return buildNotes;
    }

    /**
     * @param buildNotes the buildNotes to set
     */
    public void setNotes(String buildNotes) {
        this.buildNotes = buildNotes;
    }

    /**
     * @return the isActive
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the isOpen
     */
    public Integer getIsOpen() {
        return isOpen;
    }

    /**
     * @param isOpen the isOpen to set
     */
    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * @return releaseDate the release date
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the release date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the closed date
     */
    public String getClosedDate() {
        return closedDate;
    }

    /**
     * @param closedDate the closed date
     */
    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }


    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Build [id=" + id + ", testPlanId=" + testPlanId + ", name=" + buildName + ", notes=" + buildNotes + ", IsActive=" + isActive + ", IsOpen=" + isOpen
                +", releaseDate="+releaseDate+", closedDate"+closedDate+ "]";
    }

}
