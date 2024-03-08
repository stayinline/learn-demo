package com.my.clean.code.first_name;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResp {

    private String name;
    private Long uid;
    private List<String> userInfoDetails;
}
