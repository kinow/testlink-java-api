/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;


/**
 * @author Bruno P. Kinoshita
 *
 */
public enum ExecutionType 
{

	MANUAL(1), 
	AUTOMATED(2);
	
	private Integer value;
	
	ExecutionType(Integer value)
	{
		this.value = value;
	}
	
	public static ExecutionType getExecutionType( Integer integer )
	{
		if( integer != null )
		{
			if ( integer == 1 )
			{
				return MANUAL;
			} 
			else if ( integer == 2 )
			{
				return AUTOMATED;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() 
	{
		return this.value.toString();
	}
	
}
