package com.my.clean.code.first_name;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    private Long uid;

    private String name;
}
