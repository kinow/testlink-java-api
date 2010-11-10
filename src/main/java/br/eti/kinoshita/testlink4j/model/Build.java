package br.eti.kinoshita.testlink4j.model;

import java.io.Serializable;



/**
 * @author Bruno P. Kinoshita
 *
 */
public class Build 
implements Serializable
{

	private Integer id;
	private Integer testPlanId;
	private String buildName;
	private String buildNotes;
	/**
	 * 
	 */
	public Build() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param testPlanId
	 * @param buildName
	 * @param buildNotes
	 */
	public Build(Integer id, Integer testPlanId, String buildName,
			String buildNotes) {
		super();
		this.id = id;
		this.testPlanId = testPlanId;
		this.buildName = buildName;
		this.buildNotes = buildNotes;
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
	 * @return the buildName
	 */
	public String getBuildName() {
		return buildName;
	}
	/**
	 * @param buildName the buildName to set
	 */
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	/**
	 * @return the buildNotes
	 */
	public String getBuildNotes() {
		return buildNotes;
	}
	/**
	 * @param buildNotes the buildNotes to set
	 */
	public void setBuildNotes(String buildNotes) {
		this.buildNotes = buildNotes;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Build [id=" + id + ", testPlanId=" + testPlanId
				+ ", buildName=" + buildName + ", buildNotes=" + buildNotes
				+ "]";
	}
	
}
