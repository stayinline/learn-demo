package com.mydesign.example.lsp;

import com.sun.deploy.net.HttpRequest;
import com.sun.tools.internal.ws.processor.model.Request;
import sun.net.www.http.HttpClient;

/**
 * @author hemaoling
 */
public class Demo {

    public static void doTransport(HttpClient httpClient) {
        Transporter transporter = new Transporter(httpClient);
        Request request = null;
        transporter.sendRequest(request);
    }


    public static void doTransport2(HttpClient httpClient, String appId, String appToken) {
        SecurityTransporter securityTransporter = new SecurityTransporter(httpClient, appId, appToken);
        Request request = null;
        securityTransporter.sendRequest(request);
    }

    public static void main(String[] args) {


    }
}
