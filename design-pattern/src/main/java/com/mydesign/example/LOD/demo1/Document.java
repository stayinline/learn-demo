package com.mydesign.example.LOD.demo1;

/**
 * @author hemaoling
 */
public class Document {

    private Html html;
    private String url;

    public Document(String url) {
        this.url = url;
        HtmlDownloader htmlDownloader = new HtmlDownloader();
        this.html = htmlDownloader.downloadHtml(url);
    }
}
