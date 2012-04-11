package br.eti.kinoshita.testlinkjavaapi.model;

public enum TestCaseExecutionType implements IntegerValueEnum {

	
	MANUAL(19), 
	AUTOMATED(2);
	
	private Integer value;
	
	TestCaseExecutionType(Integer value)
	{
		this.value = value;
	}
	
	public static TestCaseExecutionType getTestCaseExecutionType( Integer integer )
	{
		if( integer != null )
		{
			if ( integer == 19)
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
