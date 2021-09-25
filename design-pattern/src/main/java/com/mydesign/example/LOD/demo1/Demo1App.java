package com.mydesign.example.LOD.demo1;


public class Demo1App {


    public static void main(String[] args) {
        String url="www.baidu.com";
        NetWorkTransport netWorkTransport = new NetWorkTransport();
        HtmlDownloader htmlDownloader = new HtmlDownloader(netWorkTransport);
        Html html = htmlDownloader.downloadHtml(url);
        Document document = new Document(url);
    }
}
