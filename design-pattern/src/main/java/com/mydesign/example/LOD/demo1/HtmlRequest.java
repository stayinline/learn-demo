package com.mydesign.example.LOD.demo1;

import lombok.Data;

@Data
public class HtmlRequest {


    private String url;

    private String content;

    private String address;

    public HtmlRequest(String url) {
        this.url = url;
    }
}
