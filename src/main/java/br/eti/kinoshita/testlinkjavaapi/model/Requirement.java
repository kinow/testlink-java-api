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
public class Requirement implements Serializable {

    private static final long serialVersionUID = -972032974806459521L;

    private Integer id;
    private Integer reqSpecId;
    private String reqDocId;

    /**
     *
     */
    public Requirement() {
        super();
    }

    /**
     * @param id ID
     * @param reqSpecId requirement specification ID
     * @param reqDocId requirement document ID
     */
    public Requirement(Integer id, Integer reqSpecId, String reqDocId) {
        super();
        this.id = id;
        this.reqSpecId = reqSpecId;
        this.reqDocId = reqDocId;
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
     * @return the reqSpecId
     */
    public Integer getReqSpecId() {
        return reqSpecId;
    }

    /**
     * @param reqSpecId the reqSpecId to set
     */
    public void setReqSpecId(Integer reqSpecId) {
        this.reqSpecId = reqSpecId;
    }

    /**
     * @return the reqDocId
     */
    public String getReqDocId() {
        return reqDocId;
    }

    /**
     * @param reqDocId the reqDocId to set
     */
    public void setReqDocId(String reqDocId) {
        this.reqDocId = reqDocId;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Requirement [id=" + id + ", reqSpecId=" + reqSpecId + ", reqDocId=" + reqDocId + "]";
    }

}
