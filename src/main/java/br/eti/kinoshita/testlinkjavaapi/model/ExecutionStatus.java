/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;


/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum ExecutionStatus 
{

	NOT_RUN('n'), 
	PASSED('p'),
	FAILED('f'), 
	BLOCKED('b');
	
	private char value;
	
	ExecutionStatus(char value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return Character.toString(this.value);
	}
	
	public static ExecutionStatus getExecutionStatus(char c)
	{
		switch ( c )
		{
		case 'n':
		case 'N':
			return NOT_RUN;
		case 'p':
		case 'P':
			return PASSED;
		case 'f':
		case 'F':
			return FAILED;
		case 'b':
		case 'B':
			return BLOCKED;
		}
		return null;
	}
	
}
