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
 * @author Bruno P. Kinoshita
 * @since 1.9.0-1
 */
public enum TestLinkUser {

    NOBODY(-1), SOMEBODY(-2), NO_USER(-1), ANYBODY(0);

    private final Integer value;

    TestLinkUser(Integer value) {
        this.value = value;
    }

    /**
     * Print user.
     * @return user
     */
    public String toString() {
        return Integer.toString(this.value);
    }

    /**
     * Get user type from a given value.
     * @param value a given value
     * @return a TestLinkUser
     */
    public static TestLinkUser getTestLinkUser(Integer value) {
        switch (value) {
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
