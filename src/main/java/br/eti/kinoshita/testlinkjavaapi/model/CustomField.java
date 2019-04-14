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
public class CustomField implements Serializable {

    private static final long serialVersionUID = 3076387765566349008L;

    private Integer id;
    private String name;
    private String label;
    private Integer type;
    private String possibleValues;
    private String defaultValue;
    private String validRegexp;
    private Integer lengthMin;
    private Integer lengthMax;
    private Boolean showOnDesign;
    private Boolean enableOnDesign;
    private Boolean showOnExecution;
    private Boolean enableOnExecution;
    private Boolean showOnTestPlanDesign;
    private Boolean enableOnTestPlanDesign;
    private Integer displayOrder; // ?
    private Integer location; // ?
    private String value;

    /**
     *
     */
    public CustomField() {
        super();
    }

    /**
     * @param id ID
     * @param name name
     * @param label label
     * @param type type
     * @param possibleValues possible values
     * @param defaultValue default value
     * @param validRegexp valid regular expression
     * @param lengthMin minimum length
     * @param lengthMax maximum length
     * @param showOnDesign show on design flag
     * @param enableOnDesign enable on design flag
     * @param showOnExecution show on execution flag
     * @param enableOnExecution enable on execution flag
     * @param showOnTestPlanDesign show on test plan design flag
     * @param enableOnTestPlanDesign enable on test plan design flag
     * @param displayOrder display order
     * @param location location
     * @param value value
     */
    public CustomField(Integer id, String name, String label, Integer type, String possibleValues, String defaultValue,
            String validRegexp, Integer lengthMin, Integer lengthMax, Boolean showOnDesign, Boolean enableOnDesign,
            Boolean showOnExecution, Boolean enableOnExecution, Boolean showOnTestPlanDesign,
            Boolean enableOnTestPlanDesign, Integer displayOrder, Integer location, String value) {
        super();
        this.id = id;
        this.name = name;
        this.label = label;
        this.type = type;
        this.possibleValues = possibleValues;
        this.defaultValue = defaultValue;
        this.validRegexp = validRegexp;
        this.lengthMin = lengthMin;
        this.lengthMax = lengthMax;
        this.showOnDesign = showOnDesign;
        this.enableOnDesign = enableOnDesign;
        this.showOnExecution = showOnExecution;
        this.enableOnExecution = enableOnExecution;
        this.showOnTestPlanDesign = showOnTestPlanDesign;
        this.enableOnTestPlanDesign = enableOnTestPlanDesign;
        this.displayOrder = displayOrder;
        this.location = location;
        this.value = value;
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
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the possibleValues
     */
    public String getPossibleValues() {
        return possibleValues;
    }

    /**
     * @param possibleValues the possibleValues to set
     */
    public void setPossibleValues(String possibleValues) {
        this.possibleValues = possibleValues;
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return the validRegexp
     */
    public String getValidRegexp() {
        return validRegexp;
    }

    /**
     * @param validRegexp the validRegexp to set
     */
    public void setValidRegexp(String validRegexp) {
        this.validRegexp = validRegexp;
    }

    /**
     * @return the lengthMin
     */
    public Integer getLengthMin() {
        return lengthMin;
    }

    /**
     * @param lengthMin the lengthMin to set
     */
    public void setLengthMin(Integer lengthMin) {
        this.lengthMin = lengthMin;
    }

    /**
     * @return the lengthMax
     */
    public Integer getLengthMax() {
        return lengthMax;
    }

    /**
     * @param lengthMax the lengthMax to set
     */
    public void setLengthMax(Integer lengthMax) {
        this.lengthMax = lengthMax;
    }

    /**
     * @return the showOnDesign
     */
    public Boolean getShowOnDesign() {
        return showOnDesign;
    }

    /**
     * @param showOnDesign the showOnDesign to set
     */
    public void setShowOnDesign(Boolean showOnDesign) {
        this.showOnDesign = showOnDesign;
    }

    /**
     * @return the enableOnDesign
     */
    public Boolean getEnableOnDesign() {
        return enableOnDesign;
    }

    /**
     * @param enableOnDesign the enableOnDesign to set
     */
    public void setEnableOnDesign(Boolean enableOnDesign) {
        this.enableOnDesign = enableOnDesign;
    }

    /**
     * @return the showOnExecution
     */
    public Boolean getShowOnExecution() {
        return showOnExecution;
    }

    /**
     * @param showOnExecution the showOnExecution to set
     */
    public void setShowOnExecution(Boolean showOnExecution) {
        this.showOnExecution = showOnExecution;
    }

    /**
     * @return the enableOnExecution
     */
    public Boolean getEnableOnExecution() {
        return enableOnExecution;
    }

    /**
     * @param enableOnExecution the enableOnExecution to set
     */
    public void setEnableOnExecution(Boolean enableOnExecution) {
        this.enableOnExecution = enableOnExecution;
    }

    /**
     * @return the showOnTestPlanDesign
     */
    public Boolean getShowOnTestPlanDesign() {
        return showOnTestPlanDesign;
    }

    /**
     * @param showOnTestPlanDesign the showOnTestPlanDesign to set
     */
    public void setShowOnTestPlanDesign(Boolean showOnTestPlanDesign) {
        this.showOnTestPlanDesign = showOnTestPlanDesign;
    }

    /**
     * @return the enableOnTestPlanDesign
     */
    public boolean isEnableOnTestPlanDesign() {
        return enableOnTestPlanDesign;
    }

    /**
     * @param enableOnTestPlanDesign the enableOnTestPlanDesign to set
     */
    public void setEnableOnTestPlanDesign(Boolean enableOnTestPlanDesign) {
        this.enableOnTestPlanDesign = enableOnTestPlanDesign;
    }

    /**
     * @return the displayOrder
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * @param displayOrder the displayOrder to set
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * @return the location
     */
    public Integer getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Integer location) {
        this.location = location;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CustomField [id=" + id + ", name=" + name + ", label=" + label + ", type=" + type + ", possibleValues="
                + possibleValues + ", defaultValue=" + defaultValue + ", validRegexp=" + validRegexp + ", lengthMin="
                + lengthMin + ", lengthMax=" + lengthMax + ", showOnDesign=" + showOnDesign + ", enableOnDesign="
                + enableOnDesign + ", showOnExecution=" + showOnExecution + ", enableOnExecution=" + enableOnExecution
                + ", showOnTestPlanDesign=" + showOnTestPlanDesign + ", enableOnTestPlanDesign="
                + enableOnTestPlanDesign + ", displayOrder=" + displayOrder + ", location=" + location + ", value="
                + value + "]";
    }

}
