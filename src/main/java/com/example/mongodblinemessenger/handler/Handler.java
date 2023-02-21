package com.example.mongodblinemessenger.handler;

import com.example.mongodblinemessenger.service.LineMessageService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class Handler {

    private final LineMessageService lineMessageService;

    public Handler(LineMessageService lineMessageService) {
        this.lineMessageService = lineMessageService;
    }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        String userId = event.getSource().getUserId();
        String messageId = event.getMessage().getId();
        String message = event.getMessage().getText();
        lineMessageService.saveMessage(userId, messageId, message);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
