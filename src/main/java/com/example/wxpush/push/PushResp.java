package com.example.wxpush.push;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PushResp {
    private Integer errcode;
    private String errmsg;
    private String invaliduser;
    private String invalidparty;
    private String invalidtag;
    private String msgid;
    private String responseCode;
}
