package br.eti.kinoshita.testlinkjavaapi.model;

import java.io.Serializable;



/**
 * <p>TestLink Build object. Represents a Build in TestLink system. A build is, 
 * basically, an instance of a Test Plan.</p>
 * 
 * <p>
 * <ul>
 * <li>20101129 - BUGID: 3122320 - kinow - 
 * 		Modify Build methods to follow standard naming</li>
 * </ul> 
 * </p>
 * 
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class Build 
implements Serializable
{

	private static final long serialVersionUID = 139468407361322252L;
	
	private Integer id;
	private Integer testPlanId;
	private String buildName;
	private String buildNotes;
	/**
	 * 
	 */
	public Build() {
		super();
	}
	/**
	 * @param id
	 * @param testPlanId
	 * @param name
	 * @param notes
	 */
	public Build(Integer id, Integer testPlanId, String name,
			String notes) {
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Build [id=" + id + ", testPlanId=" + testPlanId
				+ ", name=" + buildName + ", notes=" + buildNotes
				+ "]";
	}
	
}
