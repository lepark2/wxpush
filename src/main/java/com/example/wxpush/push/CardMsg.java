package com.example.wxpush.push;

import lombok.*;

/**
 * 企业微信卡片文本消息
 */
@Builder()
@Getter
public class CardMsg {
    @Builder.Default()
    private String touser = "@all";
    private String toparty;
    private String totag;
    @Builder.Default()
    private String msgtype = "textcard";
    private Integer agentid;
    private Textcard textcard;
    private Integer enableIdTrans;
    private Integer enableDuplicateCheck;
    private Integer duplicateCheckInterval;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Textcard {
        private String title;
        private String description;
        private String url;
        private String btntxt;
    }
}
