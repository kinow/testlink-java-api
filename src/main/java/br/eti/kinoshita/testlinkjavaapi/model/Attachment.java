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
public class Attachment implements Serializable {

    private static final long serialVersionUID = 4313427460619111314L;

    private Integer id;
    private Integer fkId;
    private String fkTable;
    private String title;
    private String description;
    private String fileName;
    private Long fileSize;
    private String fileType;
    private String content;

    // TODO: implement date and date handling in the java api
    // private Date dateAdded;
    /**
     *
     */
    public Attachment() {
        super();
    }

    /**
     * @param id ID
     * @param fkId FK
     * @param fkTable FK table name
     * @param title title
     * @param description description
     * @param fileName file name
     * @param fileSize file size
     * @param fileType file type
     * @param content content
     */
    public Attachment(Integer id, Integer fkId, String fkTable, String title, String description, String fileName,
            Long fileSize, String fileType, String content) {
        super();
        this.id = id;
        this.fkId = fkId;
        this.fkTable = fkTable;
        this.title = title;
        this.description = description;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.content = content;
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
     * @return the fkId
     */
    public Integer getFkId() {
        return fkId;
    }

    /**
     * @param fkId the fkId to set
     */
    public void setFkId(Integer fkId) {
        this.fkId = fkId;
    }

    /**
     * @return the fkTable
     */
    public String getFkTable() {
        return fkTable;
    }

    /**
     * @param fkTable the fkTable to set
     */
    public void setFkTable(String fkTable) {
        this.fkTable = fkTable;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileSize
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Attachment [id=" + id + ", fkId=" + fkId + ", fkTable=" + fkTable + ", title=" + title
                + ", description=" + description + ", fileName=" + fileName + ", fileSize=" + fileSize + ", fileType="
                + fileType + ", content=" + content + "]";
    }

}
