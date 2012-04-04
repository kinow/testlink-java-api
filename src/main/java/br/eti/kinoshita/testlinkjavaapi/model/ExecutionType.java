/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;



/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum ExecutionType implements IntegerValueEnum
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
			if ( integer == 1)
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
	
	public Integer getValue()
	{
		return value;
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
