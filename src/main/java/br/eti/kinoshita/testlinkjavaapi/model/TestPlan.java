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
import java.util.List;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestPlan implements Serializable {

    private static final long serialVersionUID = -758553487485596180L;

    private Integer id;
    private String name;
    private String projectName;
    private String notes;
    private Boolean isActive;
    private Boolean isPublic;
    private List<CustomField> customFields;

    /**
     * Constructor.
     */
    public TestPlan() {
        super();
        this.customFields = new ArrayList<>();
    }

    /**
     * Constructor with parameters.
     * @param id ID
     * @param name name
     * @param projectName project name
     * @param notes notes
     * @param isActive is active flag
     * @param isPublic is public flag
     */
    public TestPlan(Integer id, String name, String projectName, String notes, Boolean isActive, Boolean isPublic) {
        this(id, name, projectName, notes, isActive, isPublic, new ArrayList<>());
    }

    /**
     * Constructor with parameters.
     * @param id ID
     * @param name name
     * @param projectName project name
     * @param notes notes
     * @param isActive is active flag
     * @param isPublic is public flag
     * @param customFields custom fields
     */
    public TestPlan(Integer id, String name, String projectName, String notes, Boolean isActive, Boolean isPublic,
            List<CustomField> customFields) {
        super();
        this.id = id;
        this.name = name;
        this.projectName = projectName;
        this.notes = notes;
        this.isActive = isActive;
        this.isPublic = isPublic;
        this.customFields = customFields;
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
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
     * @return list of custom fields
     */
    public List<CustomField> getCustomFields() {
        return customFields;
    }

    /**
     * @param customFields list of custom fields
     */
    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TestPlan [id=" + id + ", name=" + name + ", projectName=" + projectName + ", notes=" + notes
                + ", isActive=" + isActive + ", isPublic=" + isPublic + "]";
    }

}
