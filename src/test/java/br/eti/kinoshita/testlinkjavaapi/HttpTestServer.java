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
package br.eti.kinoshita.testlinkjavaapi;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConnection;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * Test HTTP Server, used as test double.
 *
 * @author Olaf Otto - http://olafsblog.sysbsb.de
 * @since 1.0
 */
public class HttpTestServer {

    private static final int DEFAULT_SERVER_PORT = 31984;

    protected final int port;
    protected Server server;
    protected String responseBody;
    protected String requestBody;
    protected String mockResposeData;

    public HttpTestServer() {
        this(DEFAULT_SERVER_PORT);
    }

    public HttpTestServer(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    protected void start() throws Exception {
        configureServer();
        startServer();
    }

    protected void configureServer() {
        this.server = new Server(this.port);
        this.server.setHandler(getMockHandler());
    }

    public Handler getMockHandler() {
        return new AbstractHandler() {
            public void handle(String target, Request request, HttpServletRequest httpRequest,
                               HttpServletResponse httpResponse) throws IOException {
                Request baseRequest = HttpConnection.getCurrentConnection().getHttpChannel().getRequest();
                setResponseBody(getMockResponseBody());
                setRequestBody(IOUtils.toString(baseRequest.getInputStream(), Charset.defaultCharset()));
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                httpResponse.setContentType("text/xml;charset=utf-8");
                httpResponse.getWriter().write(getResponseBody());
                baseRequest.setHandled(true);
            }
        };
    }

    protected void startServer() throws Exception {
        this.server.start();
    }

    public void stop() throws Exception {
        this.server.stop();
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return this.requestBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setMockResponseBody(String mockResponseBody) {
        this.mockResposeData = mockResponseBody;
    }

    public String getMockResponseBody() {
        return this.mockResposeData;
    }

    public Server getServer() {
        return this.server;
    }

    public static void main(String[] args) throws Exception {
        HttpTestServer server = new HttpTestServer();
        server.start();
    }

}
