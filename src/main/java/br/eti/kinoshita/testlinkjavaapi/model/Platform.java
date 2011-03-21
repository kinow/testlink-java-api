/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;

import java.io.Serializable;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class Platform 
implements Serializable 
{

	private static final long serialVersionUID = -7743797010900708283L;
	
	private Integer id;
	private String name;
	private String notes;
	
	/**
	 * 
	 */
	public Platform() {
		super();
	}
	/**
	 * @param id
	 * @param name
	 * @param notes
	 */
	public Platform(Integer id, String name, String notes) {
		super();
		this.id = id;
		this.name = name;
		this.notes = notes;
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
	 * @return the notes
	 */
	public String getNotes()
	{
		return this.notes;
	}
	
	/**
	 * @param notes the notes to set
	 */
	public void setNotes( String notes )
	{
		this.notes = notes;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Platform [id=" + id + ", name=" + name + ", notes=" + notes +"]";
	}
	
}
