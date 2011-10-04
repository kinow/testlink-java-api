/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;

import java.io.Serializable;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestCaseStep implements Serializable
{

	private static final long serialVersionUID = -1835456164473694033L;

	private Integer id;
	private Integer testCaseVersionId;
	private Integer number;
	private String actions;
	private String expectedResults;
	private Boolean active;
	private ExecutionType executionType;

	/**
	 * 
	 */
	public TestCaseStep()
	{
		super();
	}

	/**
	 * @param id
	 * @param testCaseVersionId
	 * @param number
	 * @param actions
	 * @param expectedResults
	 * @param active
	 * @param executionType
	 */
	public TestCaseStep(Integer id, Integer testCaseVersionId, Integer number,
			String actions, String expectedResults, Boolean active,
			ExecutionType executionType)
	{
		super();
		this.id = id;
		this.testCaseVersionId = testCaseVersionId;
		this.number = number;
		this.actions = actions;
		this.expectedResults = expectedResults;
		this.active = active;
		this.executionType = executionType;
	}

	/**
	 * @return the testCaseVersionId
	 */
	public Integer getTestCaseVersionId()
	{
		return testCaseVersionId;
	}

	/**
	 * @param testCaseVersionId
	 *            the testCaseVersionId to set
	 */
	public void setTestCaseVersionId( Integer testCaseVersionId )
	{
		this.testCaseVersionId = testCaseVersionId;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber()
	{
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber( Integer number )
	{
		this.number = number;
	}

	/**
	 * @return the actions
	 */
	public String getActions()
	{
		return actions;
	}

	/**
	 * @param actions
	 *            the actions to set
	 */
	public void setActions( String actions )
	{
		this.actions = actions;
	}

	/**
	 * @return the expectedResults
	 */
	public String getExpectedResults()
	{
		return expectedResults;
	}

	/**
	 * @param expectedResults
	 *            the expectedResults to set
	 */
	public void setExpectedResults( String expectedResults )
	{
		this.expectedResults = expectedResults;
	}

	/**
	 * @return the executionType
	 */
	public ExecutionType getExecutionType()
	{
		return executionType;
	}

	/**
	 * @param executionType
	 *            the executionType to set
	 */
	public void setExecutionType( ExecutionType executionType )
	{
		this.executionType = executionType;
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Integer id )
	{
		this.id = id;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive()
	{
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive( Boolean active )
	{
		this.active = active;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TestCaseStep [id=" + id + ", testCaseVersionId="
				+ testCaseVersionId + ", number=" + number + ", actions="
				+ actions + ", expectedResults=" + expectedResults
				+ ", active=" + active + ", executionType=" + executionType
				+ "]";
	}

}
