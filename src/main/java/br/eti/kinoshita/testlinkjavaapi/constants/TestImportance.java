/**
 * 
 */
package br.eti.kinoshita.testlinkjavaapi.constants;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum TestImportance {

    LOW(1), MEDIUM(2), HIGH(3);

    private Integer value;

    TestImportance(Integer value) {
	this.value = value;
    }

    public static TestImportance getTestImportance(Integer integer) {
    if (integer != null) {
        if (integer == 1) {
        return LOW;
        } else if (integer == 2) {
        return MEDIUM;
        } else if (integer == 3) {
        return HIGH;
        }
    }
    return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
	return Integer.toString(this.value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.eti.kinoshita.testlinkjavaapi.model.IntegerValueEnum#getValue()
     */
    public Integer getValue() {
	return this.value;
    }

}
