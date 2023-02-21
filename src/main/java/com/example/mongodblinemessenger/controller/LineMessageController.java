package com.example.mongodblinemessenger.controller;

import com.example.mongodblinemessenger.dto.MessagePushRequest;
import com.example.mongodblinemessenger.service.LineMessageService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/line")
public class LineMessageController {

    @Value("${line.bot.channel-token}")
    private String channelAccessToken;

    private final LineMessageService lineMessageService;

    @Autowired
    public LineMessageController(LineMessageService lineMessageService) {
        this.lineMessageService = lineMessageService;
    }

    @GetMapping("/user-message/{userId}")
    public ResponseEntity<List<String>> getUserMessage (@PathVariable String userId) {
        return new ResponseEntity<>(lineMessageService.findMessageByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/push-message")
    public void pushMessage(@RequestBody MessagePushRequest messagePushRequest) {
        final LineMessagingClient lineMessagingClient = LineMessagingClient
                .builder(channelAccessToken).build();
        final TextMessage textMessage = new TextMessage(messagePushRequest.getMessage());
        final PushMessage pushMessage = new PushMessage(messagePushRequest.getUserId(), textMessage);

        final BotApiResponse botApiResponse;
        try {
            botApiResponse = lineMessagingClient.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(botApiResponse);
    }


}
