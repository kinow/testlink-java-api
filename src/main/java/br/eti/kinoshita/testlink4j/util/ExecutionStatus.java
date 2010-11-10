/**
 * 
 */
package br.eti.kinoshita.testlink4j.util;

/**
 * @author Bruno P. Kinoshita
 *
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
			return FAILED;
		}
		return null;
	}
	
}
