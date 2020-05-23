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
 * @author Mario Fuentes - http://www.rhiscom.com
 * @since 1.9.3-4
 */
public enum TestCaseStatus {

    FINAL(7), FUTURE(6), OBSOLETE(5), REWORK(4), REVIEW_IN_PROGRESS(3), READY_FOR_REVIEW(2), DRAFT(1);

    private final Integer value;

    TestCaseStatus(Integer value) {
        this.value = value;
    }

    /**
     * Get value of test case status.
     * @return value of test case status
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Print value of test case status.
     * @return value of test case status
     */
    public String toString() {
        return this.value.toString();
    }
}
