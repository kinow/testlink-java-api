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
import java.util.Arrays;

/**
 * @author Radosław Sporny
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 3833871921749071877L;

    private Integer dbID;
    private String description;
    private String name;
    // TODO add new model 'Rights'
    private Object[] rights;

    /**
     * Constructor.
     * @param dbID database ID
     */
    public Role(Integer dbID) {
        super();
        this.dbID = dbID;
    }

    /**
     * Constructor with args.
     *
     * @param dbID database ID
     * @param description description
     * @param name name
     * @param rights rights
     */
    public Role(Integer dbID, String description, String name, Object[] rights) {
        super();
        this.dbID = dbID;
        this.description = description;
        this.name = name;
        this.rights = rights != null ? Arrays.copyOf(rights, rights.length) : null;
    }

    /**
     * @return the dbID
     */
    public Integer getDbID() {
        return dbID;
    }

    /**
     * @param dbID the dbID to set
     */
    public void setDbID(Integer dbID) {
        this.dbID = dbID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the rights
     */
    public Object[] getRights() {
        return rights != null ? Arrays.copyOf(rights, rights.length) : null;
    }

    /**
     * @param rights the rights to set
     */
    public void setRights(Object[] rights) {
        this.rights = rights != null ? Arrays.copyOf(rights, rights.length) : null;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Role [dbID=" + dbID + ", description=" + description + ", name=" + name + ", rights="
                + Arrays.toString(rights) + "]";
    }

}
