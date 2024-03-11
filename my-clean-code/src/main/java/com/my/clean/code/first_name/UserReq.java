package com.my.clean.code.first_name;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    private Long uid;

    private String name;

    @Override
    public String toString() {
        return "UserReq{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                '}';
    }
}
