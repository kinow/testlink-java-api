/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.model;

/**
 * @author Bruno P. Kinoshita
 * @since 1.9.0-1
 */
public enum TestLinkUser 
{

	NOBODY(-1), 
	SOMEBODY(-2), 
	NO_USER(-1), 
	ANYBODY(0);
	
	private Integer value;
	
	TestLinkUser(Integer value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return Integer.toString(this.value);
	}
	
	public static TestLinkUser getTestLinkUser( Integer value )
	{
		switch ( value )
		{
		case -1:
			return NOBODY;
		case -2:
			return SOMEBODY;
		case 0:
			return ANYBODY;
		}
		return null;
	}
	
}
