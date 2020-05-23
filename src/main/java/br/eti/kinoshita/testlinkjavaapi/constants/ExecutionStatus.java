/*
 * The MIT License
 *
 * Copyright (c) 2010 Bruno P. Kinoshita http://www.kinoshita.eti.br
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.eti.kinoshita.testlinkjavaapi.constants;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public enum ExecutionStatus {

    NOT_RUN('n'), PASSED('p'), FAILED('f'), BLOCKED('b');

    private final char value;

    ExecutionStatus(char value) {
        this.value = value;
    }

    /**
     * Print the character.
     * @return character
     */
    public String toString() {
        return Character.toString(this.value);
    }

    /**
     * Get the ExecutionStatus for a given character.
     * @param c a given character
     * @return an ExecutionStatus
     */
    public static ExecutionStatus getExecutionStatus(char c) {
        switch (c) {
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
