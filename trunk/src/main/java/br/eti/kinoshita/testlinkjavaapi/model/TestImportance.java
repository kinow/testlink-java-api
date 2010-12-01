/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
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
