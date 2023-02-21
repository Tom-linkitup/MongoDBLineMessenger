package com.example.mongodblinemessenger.repository;

import com.example.mongodblinemessenger.model.LineMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LineMessageRepository extends MongoRepository<LineMessage, String> {

    List<LineMessage> findByUserId(String userId);
}
