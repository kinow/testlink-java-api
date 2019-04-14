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
package br.eti.kinoshita.testlinkjavaapi.util;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 1.9.0-1
 */
public class TestLinkAPIException extends RuntimeException {

    private static final long serialVersionUID = -2528132734907454631L;
    /**
     * Error Code.
     */
    private Integer code;

    /**
     * Default constructor.
     */
    public TestLinkAPIException() {
    }

    /**
     * @param message the message
     */
    public TestLinkAPIException(String message) {
        super(message);
    }

    /**
     * Constructor with args.
     *
     * @param code code
     * @param message message
     */
    public TestLinkAPIException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param cause cause
     */
    public TestLinkAPIException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message message
     * @param cause cause
     */
    public TestLinkAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return Error Code
     */
    public Integer getCode() {
        return this.code;
    }

}
