/**
 * 
 */
package br.eti.kinoshita.testlink4j.util;

/**
 * @author Bruno P. Kinoshita
 *
 */
public enum TestImportance {

	LOW("Low"), 
	MEDIUM("Medium"), 
	HIGH("High");
	
	private String value;
	
	TestImportance(String value)
	{
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() 
	{
		return this.value;
	}
	
}
