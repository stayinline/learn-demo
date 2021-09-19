package com.mydesign.example.lsp;

import com.sun.tools.internal.ws.processor.model.Request;
import sun.net.www.http.HttpClient;

/**
 * @author hemaoling
 */
public class Transporter {


    private HttpClient httpClient;

    public Transporter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void sendRequest(Request request) {
        // 处理发送逻辑

    }


    public void sendRequestV2(Request request) throws Exception {
        // 处理发送逻辑

    }
}
