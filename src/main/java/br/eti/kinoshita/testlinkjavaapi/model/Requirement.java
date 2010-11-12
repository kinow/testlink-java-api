/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;

import java.io.Serializable;

/**
 * @author Bruno P. Kinoshita
 *
 */
public class Requirement 
implements Serializable
{

	private Integer id;
	private Integer reqSpecId;
	private String reqDocId;
	/**
	 * 
	 */
	public Requirement() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param reqSpecId
	 * @param reqDocId
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Requirement [id=" + id + ", reqSpecId=" + reqSpecId
				+ ", reqDocId=" + reqDocId + "]";
	}
	
}
