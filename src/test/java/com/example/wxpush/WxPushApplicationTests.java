package com.example.wxpush;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
class WxPushApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {

//        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww9bab221430bf4526&corpsecret=BMka7v_XuwC4phpH0lIxP5kKKIZpNFvPyfcBFvUksNE";
//        Card.TextcardDTO textcardDTO = new Card.TextcardDTO("title", "测试消息", "", "wu ");
//        TextMsg textMsg = new TextMsg("@all", "@all", "@all", "text", 1000002, new TextMsg.TextDTO("测试消息"), 0, 0, 0, 1800);
//
//
//        CompletableFuture<PushResp> future = CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url, GetTokenResp.class))
//                .whenComplete((getTokenResp, throwable) -> {
//                    if (throwable != null) {
//                        log.info("get token error \n" + throwable);
//                    }
//                    log.info("get token success \n" + getTokenResp.toString());
//                })
//                .thenApply(GetTokenResp -> {
//                    if (GetTokenResp.getErrcode() != 0) {
//                        log.info("get token failure" + GetTokenResp.getErrmsg());
//                    }
//                    return restTemplate.
//                            postForObject("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="
//                                            + GetTokenResp.getAccess_token() + 1,
//                                    textMsg, PushResp.class);
//
//                });
//
//        try {
//            PushResp resp = future.get();
//            if (resp.getErrcode() == ErrConstant.ILLEGAL_TOKEN || resp.getErrcode() == ErrConstant.OUT_OD_DATE_TOKEN) {
//
//            }
//            if (resp.getErrcode() != 0) {
//                log.info("send failure" + resp.getErrmsg());
//            }
//            log.info("send success" + resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info("send error" + e.getMessage());
//        }
    }


}
