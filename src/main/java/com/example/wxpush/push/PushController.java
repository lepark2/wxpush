package com.example.wxpush.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/push")
public class PushController {
    @Autowired
    private PushService pushService;

    @GetMapping()
    public void sendByGet(@RequestParam(required = false, defaultValue = "nas") String from,
                          @RequestParam String text,
                          @RequestParam(required = false, defaultValue = "@all") String to) {
        pushService.push(from, text, to);
    }

    @PostMapping()
    public void sendByPost(@RequestParam(required = false, defaultValue = "true_nas") String from,
                           @RequestBody TrueNasReq trueNasReq,
                           @RequestParam(required = false, defaultValue = "@all") String to) {
        pushService.push(from, trueNasReq.getText(), to);
    }

}
