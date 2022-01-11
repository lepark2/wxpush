package com.example.wxpush.push;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class GetTokenResp {
    private int errcode;
    private String errmsg;
    private String access_token;
    private int expires_in;
}