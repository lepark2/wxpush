package com.example.wxpush.push;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Slf4j
public class PushService {
    @Value("${corpid:corpId}")
    private String corpId;
    @Value("${corpsecret:corpSecret}")
    private String corpSecret;
    @Value("${agentid:-1}")
    private int agentId;
    @Value("${showdetail:false}")
    private boolean showdetail;

    private static final String TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}";
    private static final String SEND_MSG_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={token}";
    public static final int ILLEGAL_TOKEN = 40014;
    public static final int OUT_OD_DATE_TOKEN = 40014;


    @Autowired
    private RestTemplate restTemplate;
    private LocalDateTime expireTime;
    private String token;

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    @Async
    public void push(String from, String text, String to) {
        if (checkTokenExpire()) {
            getToken();
        }
        if (token != null) {
            send(token, from, text, to, false);
        }
    }


    private boolean checkTokenExpire() {
        readLock.lock();
        try {
            return expireTime == null || LocalDateTime.now().isAfter(expireTime);
        } finally {
            readLock.unlock();
        }
    }

    private void getToken() {
        writeLock.lock();
        if (expireTime != null && LocalDateTime.now().isBefore(expireTime)) {
            return;
        }
        try {
            if (showdetail) {
                log.info("getToken corpId: {}, corpSecret: {}", corpId, corpSecret);
            }
            GetTokenResp tokenResp = restTemplate.getForObject(TOKEN_URL, GetTokenResp.class,
                    corpId, corpSecret);
            if (tokenResp == null || tokenResp.getErrcode() != 0) {
                throw new RuntimeException(tokenResp == null ? "返回null" : tokenResp.toString());
            }
            token = tokenResp.getAccess_token();
            expireTime = LocalDateTime.now().plusSeconds(tokenResp.getExpires_in());
        } catch (Exception e) {
            token = null;
            expireTime = null;
            log.error("获取token失败: " + e.getMessage());
        } finally {
            writeLock.unlock();
        }
    }


    private void send(String token, String from, String text, String to, boolean retry) {
        if (showdetail) {
            log.info("push msg from: {}, text: {}, to: {}, agentId: {}", from, text, to, agentId);
        }
        CardMsg cardMsg = buildCardMsg(from, text, to);
        PushResp pushResp = restTemplate.postForObject(SEND_MSG_URL, cardMsg, PushResp.class, token);
        if (pushResp == null) {
            log.error("push消息失败: 返回null");
            return;
        }
        if (pushResp.getErrcode() == ILLEGAL_TOKEN
                || pushResp.getErrcode() == OUT_OD_DATE_TOKEN) {
            if (!retry) {
                getToken();
                if (token != null) {
                    send(token, from, text, to, true);
                }
            }
        }
        if (pushResp.getErrcode() != 0) {
            log.error("push消息失败: " + pushResp);
        }
    }

    private TextMsg buildTextMsg(String from, String text, String to) {
        TextMsg.TextDTO textDTO = new TextMsg.TextDTO(from + "推送: " + text);
        return TextMsg.builder().touser(to).agentid(agentId).text(textDTO).build();
    }

    private CardMsg buildCardMsg(String from, String text, String to) {
        CardMsg.Textcard textcard = new CardMsg.Textcard(from + "推送", text, "https://www.baidu.com/", "详情");
        return CardMsg.builder().touser(to).agentid(agentId).textcard(textcard).build();
    }
}
