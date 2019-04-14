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
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestProject implements Serializable {

    private static final long serialVersionUID = 3803980396536967693L;

    private Integer id;
    private String name;
    private String prefix;
    private String notes;
    private Boolean enableRequirements = Boolean.FALSE;
    private Boolean enableTestPriority = Boolean.FALSE;
    private Boolean enableAutomation = Boolean.FALSE;
    private Boolean enableInventory = Boolean.FALSE;
    private Boolean isActive = Boolean.TRUE;
    private Boolean isPublic = Boolean.TRUE;

    /**
     *
     */
    public TestProject() {
        super();
    }

    /**
     * @param id ID
     * @param name name
     * @param prefix prefix
     * @param notes notes
     * @param enableRequirements enable requirements flag
     * @param enableTestPriority enable test priority flag
     * @param enableAutomation enable automation flag
     * @param enableInventory enable inventory flag
     * @param isActive is active flag
     * @param isPublic is public flag
     */
    public TestProject(Integer id, String name, String prefix, String notes, Boolean enableRequirements,
            Boolean enableTestPriority, Boolean enableAutomation, Boolean enableInventory, Boolean isActive,
            Boolean isPublic) {
        super();
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.notes = notes;
        this.enableRequirements = enableRequirements;
        this.enableTestPriority = enableTestPriority;
        this.enableAutomation = enableAutomation;
        this.enableInventory = enableInventory;
        this.isActive = isActive;
        this.isPublic = isPublic;
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
     * @return the projectName
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the projectName to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
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
     * @return the enableRequirements
     */
    public Boolean isEnableRequirements() {
        return enableRequirements;
    }

    /**
     * @param enableRequirements the enableRequirements to set
     */
    public void setEnableRequirements(Boolean enableRequirements) {
        this.enableRequirements = enableRequirements;
    }

    /**
     * @return the enableTestPriority
     */
    public Boolean isEnableTestPriority() {
        return enableTestPriority;
    }

    /**
     * @param enableTestPriority the enableTestPriority to set
     */
    public void setEnableTestPriority(Boolean enableTestPriority) {
        this.enableTestPriority = enableTestPriority;
    }

    /**
     * @return the enableAutomation
     */
    public Boolean isEnableAutomation() {
        return enableAutomation;
    }

    /**
     * @param enableAutomation the enableAutomation to set
     */
    public void setEnableAutomation(Boolean enableAutomation) {
        this.enableAutomation = enableAutomation;
    }

    /**
     * @return the enableInventory
     */
    public Boolean isEnableInventory() {
        return enableInventory;
    }

    /**
     * @param enableInventory the enableInventory to set
     */
    public void setEnableInventory(Boolean enableInventory) {
        this.enableInventory = enableInventory;
    }

    /**
     * @return the isActive
     */
    public Boolean isActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the isPublic
     */
    public Boolean isPublic() {
        return isPublic;
    }

    /**
     * @param isPublic the isPublic to set
     */
    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * Print the test project properties.
     * @return test project properties
     */
    public String toString() {
        return "TestProject [id=" + id + ", name=" + name + ", prefix=" + prefix + ", notes=" + notes
                + ", enableRequirements=" + enableRequirements + ", enableTestPriority=" + enableTestPriority
                + ", enableAutomation=" + enableAutomation + ", enableInventory=" + enableInventory + ", isActive="
                + isActive + ", isPublic=" + isPublic + "]";
    }

}
