package com.mydesign.example.LOD.demo1;


public class NetWorkTransport {

    /**
     * 设计缺陷1：
     * NetWorkTransport 只是需要发送一个数据即可，不仅仅只是 HtmlRequest ，也有可能是TCP数据包等等，
     * 它应该更通用，不应该将 HtmlRequest 作为参数，
     * 这不符合迪米特法则
     *
     * @param htmlRequest
     * @return
     */
    public byte[] send(HtmlRequest htmlRequest) {
        String url = htmlRequest.getUrl();
        System.out.println("向URL：" + url + " 发送请求...");
        // 模拟发送请求，并获得返回的页面，然后解析出的data
        String data = "hello baidu.com";
        return data.getBytes();
    }

    /**
     * 符合迪米特法则的重构版本：
     *
     * @param address
     * @param data
     * @return
     */
    public byte[] sendV2(String address, byte[] data) {

        return new byte[]{};
    }
}
