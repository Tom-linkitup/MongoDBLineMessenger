package com.example.mongodblinemessenger.repository;

import com.example.mongodblinemessenger.model.LineMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LineMessageRepository extends MongoRepository<LineMessage, String> {
}
