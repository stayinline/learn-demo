package com.my.clean.code.first_name;

import lombok.Data;

import java.util.List;

@Data
public class UserResp {

    private String name;
    private Long uid;
    private List<String> userInfoDetails;
}
