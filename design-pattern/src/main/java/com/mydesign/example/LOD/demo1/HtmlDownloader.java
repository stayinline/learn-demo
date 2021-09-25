package com.mydesign.example.LOD.demo1;


import lombok.NoArgsConstructor;

/**
 * @author hemaoling
 */
@NoArgsConstructor
public class HtmlDownloader {

    private NetWorkTransport transport;


    public HtmlDownloader(NetWorkTransport transport) {
        this.transport = transport;
    }

    public Html downloadHtml(String url) {
        byte[] rawHtml = transport.send(new HtmlRequest(url));
        System.out.println("收到URL：" + url + " 的返回数据...");
        return new Html(rawHtml);
    }

    public Html downloadHtmlV2(String url) {
        HtmlRequest htmlRequest = null;
        String address = htmlRequest.getAddress();
        byte[] data = htmlRequest.getContent().getBytes();
        byte[] rawHtml = transport.sendV2(address, data);
        return new Html(rawHtml);
    }

}
