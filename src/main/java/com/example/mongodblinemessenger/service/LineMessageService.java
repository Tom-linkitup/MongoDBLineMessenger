package com.example.mongodblinemessenger.service;

import com.example.mongodblinemessenger.model.LineMessage;
import com.example.mongodblinemessenger.repository.LineMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineMessageService {

    private final LineMessageRepository lineMessageRepository;

    @Autowired
    public LineMessageService(LineMessageRepository lineMessageRepository) {
        this.lineMessageRepository = lineMessageRepository;
    }

    public void saveMessage(String userId, String messageId, String message) {
        LineMessage lineMessage = LineMessage.builder()
                .userId(userId)
                .messageId(messageId)
                .message(message)
                .build();
        lineMessageRepository.save(lineMessage);
    }


}
