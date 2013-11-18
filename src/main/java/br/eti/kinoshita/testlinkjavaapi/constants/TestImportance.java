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
    
    public static TestImportance valueOf(Integer importanceTypeValue) {
        if (importanceTypeValue != null) {
            if (importanceTypeValue == 1) {
                return LOW;
            } else if (importanceTypeValue == 2) {
                return MEDIUM;
            } else if (importanceTypeValue == 3) {
                return HIGH;
           

        }
        return null;
    }

}
