package com.example.wxpush.push;

import lombok.*;

/**
 * 企业微信文本消息
 */
@Builder()
@Getter
public class TextMsg {
    @Builder.Default()
    private String touser = "@all";
    private String toparty;
    private String totag;
    @Builder.Default()
    private String msgtype = "text";
    private Integer agentid;
    private TextDTO text;
    private Integer safe;
    private Integer enableIdTrans;
    private Integer enableDuplicateCheck;
    private Integer duplicateCheckInterval;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class TextDTO {
        private String content;
    }
}
