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
 * @author Radosław Sporny
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1842598888675399902L;

    private Integer dbID;
    private String login;
    private String firstName;
    private String lastName;
    private String locale;
    private String emailAddress;
    private Integer isActive;
    private String userApiKey;
    private String loginRegExp;
    private Integer tprojectRoles;
    private Integer tplanRoles;
    private Role globalRole;
    private Integer globalRoleID;
    private Integer defaultTestprojectID;

    /**
     * Create a new user.
     * @param dbID Database ID
     */
    public User(Integer dbID) {
        super();
        this.dbID = dbID;
    }

    /**
     * Constructor with args.
     *
     * @param dbID database ID
     * @param login login
     * @param firstName first name
     * @param lastName last name
     * @param locale locale
     * @param emailAddress email address
     * @param isActive is active flag
     * @param userApiKey user API key
     * @param loginRegExp login regular expression
     * @param tprojectRoles test project roles
     * @param tplanRoles test plan roles
     * @param globalRole global role
     * @param globalRoleID global role ID
     * @param defaultTestprojectID default test project ID
     */
    public User(Integer dbID, String login, String firstName, String lastName, String locale, String emailAddress,
            Integer isActive, String userApiKey, String loginRegExp, Integer tprojectRoles, Integer tplanRoles,
            Role globalRole, Integer globalRoleID, Integer defaultTestprojectID) {
        super();
        this.dbID = dbID;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.locale = locale;
        this.emailAddress = emailAddress;
        this.isActive = isActive;
        this.userApiKey = userApiKey;
        this.loginRegExp = loginRegExp;
        this.tprojectRoles = tprojectRoles;
        this.tplanRoles = tplanRoles;
        this.globalRole = globalRole;
        this.globalRoleID = globalRoleID;
        this.defaultTestprojectID = defaultTestprojectID;
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
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
     * @return the userApiKey
     */
    public String getUserApiKey() {
        return userApiKey;
    }

    /**
     * @param userApiKey the userApiKey to set
     */
    public void setUserApiKey(String userApiKey) {
        this.userApiKey = userApiKey;
    }

    /**
     * @return the loginRegExp
     */
    public String getLoginRegExp() {
        return loginRegExp;
    }

    /**
     * @param loginRegExp the loginRegExp to set
     */
    public void setLoginRegExp(String loginRegExp) {
        this.loginRegExp = loginRegExp;
    }

    /**
     * @return the tprojectRoles
     */
    public Integer getTprojectRoles() {
        return tprojectRoles;
    }

    /**
     * @param tprojectRoles the tprojectRoles to set
     */
    public void setTprojectRoles(Integer tprojectRoles) {
        this.tprojectRoles = tprojectRoles;
    }

    /**
     * @return the tplanRoles
     */
    public Integer getTplanRoles() {
        return tplanRoles;
    }

    /**
     * @param tplanRoles the tplanRoles to set
     */
    public void setTplanRoles(Integer tplanRoles) {
        this.tplanRoles = tplanRoles;
    }

    /**
     * @return the globalRole
     */
    public Role getGlobalRole() {
        return globalRole;
    }

    /**
     * @param globalRole the globalRole to set
     */
    public void setGlobalRole(Role globalRole) {
        this.globalRole = globalRole;
    }

    /**
     * @return the globalRoleID
     */
    public Integer getGlobalRoleID() {
        return globalRoleID;
    }

    /**
     * @param globalRoleID the globalRoleID to set
     */
    public void setGlobalRoleID(Integer globalRoleID) {
        this.globalRoleID = globalRoleID;
    }

    /**
     * @return the defaultTestprojectID
     */
    public Integer getDefaultTestprojectID() {
        return defaultTestprojectID;
    }

    /**
     * @param defaultTestprojectID the defaultTestprojectID to set
     */
    public void setDefaultTestprojectID(Integer defaultTestprojectID) {
        this.defaultTestprojectID = defaultTestprojectID;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [dbID=" + dbID + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName
                + ", locale=" + locale + ", emailAddress=" + emailAddress + ", isActive=" + isActive + ", userApiKey="
                + userApiKey + ", loginRegExp=" + loginRegExp + ", tprojectRoles=" + tprojectRoles + ", tplanRoles="
                + tplanRoles + ", globalRole=" + globalRole + ", globalRoleID=" + globalRoleID
                + ", defaultTestprojectID=" + defaultTestprojectID + "]";
    }

}
